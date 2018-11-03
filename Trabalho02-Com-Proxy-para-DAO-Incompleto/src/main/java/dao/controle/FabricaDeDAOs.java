package dao.controle;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import net.sf.cglib.proxy.Enhancer;

public class FabricaDeDAOs {
	private static ResourceBundle resource;

	static {
		try {
			resource = ResourceBundle.getBundle("dao");
		} catch (MissingResourceException e) {
			System.out.println("dao.properties not found!");
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getDAO(Class<T> tipo) {
		String nomeDaClasse = null;

		try {
			nomeDaClasse = resource.getString(tipo.getSimpleName());
			Class<?> dao = Class.forName(nomeDaClasse);
			return (T) Enhancer.create(dao, new InterceptadorDeDAO());
		}
		catch (ClassNotFoundException e) {
			System.out.println("Classe " + nomeDaClasse + " não encontrada");
			throw new RuntimeException(e);
		} catch (MissingResourceException e) {
			System.out.println("Chave " + tipo + " não encontrada em dao.properties");
			throw new RuntimeException(e);
		}
	}
}
