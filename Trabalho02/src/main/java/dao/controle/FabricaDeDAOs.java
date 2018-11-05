package dao.controle;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import net.sf.cglib.proxy.Enhancer;

public class FabricaDeDAOs {
	private static ResourceBundle prop;

	static {
		try {
			prop = ResourceBundle.getBundle("dao");
		} catch (MissingResourceException e) {
			System.out.println("Aquivo dao.properties n�o encontrado.");
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getDAO(Class<T> tipo) {
		// T dao = null;
		String nomeDaClasse = null;

		try {
			nomeDaClasse = prop.getString(tipo.getSimpleName());
			Class<?> dao = Class.forName(nomeDaClasse);
			return (T) Enhancer.create(dao, new InterceptadorDeDAO());
		}
		// catch (InstantiationException e)
		// { System.out.println("N�o foi poss�vel criar um objeto do tipo " +
		// nomeDaClasse);
		// throw new RuntimeException(e);
		// }
		// catch (IllegalAccessException e)
		// { System.out.println("N�o foi poss�vel criar um objeto do tipo " +
		// nomeDaClasse);
		// throw new RuntimeException(e);
		// }
		catch (ClassNotFoundException e) {
			System.out.println("Classe " + nomeDaClasse + " n�o encontrada");
			throw new RuntimeException(e);
		} catch (MissingResourceException e) {
			System.out.println("Chave " + tipo + " n�o encontrada em dao.properties");
			throw new RuntimeException(e);
		}
	}
}
