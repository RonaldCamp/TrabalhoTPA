package dao.controle;

import org.reflections.Reflections;

import excecao.DuasImplementacoesException;
import net.sf.cglib.proxy.Enhancer;

public class FabricaDeDAOs {
	private static Reflections reflec = new Reflections("dao.impl");

	@SuppressWarnings("unchecked")
	public static <T> T getDAO(Class<T> tipo) throws DuasImplementacoesException {
		if (reflec.getSubTypesOf(tipo).isEmpty()) {
			System.out.println("Não foi encontrado uma classe que implemente: " + tipo.getSimpleName());
			throw new RuntimeException();
		}
		if (reflec.getSubTypesOf(tipo).size() > 1) 
			throw new DuasImplementacoesException("Existem duas implementcoes: " + tipo.getSimpleName());
		Class<?> dao = null;
		for (Class<?> classe : reflec.getSubTypesOf(tipo)) {
			dao = classe;
		}
		return (T) Enhancer.create(dao, new InterceptadorDeDAO());
		// catch (InstantiationException e)
		// { System.out.println("Não foi possível criar um objeto do tipo " +
		// nomeDaClasse);
		// throw new RuntimeException(e);
		// }
		// catch (IllegalAccessException e)
		// { System.out.println("Não foi possível criar um objeto do tipo " +
		// nomeDaClasse);
		// throw new RuntimeException(e);
		// }
	}
}
