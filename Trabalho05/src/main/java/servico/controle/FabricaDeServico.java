package servico.controle;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import net.sf.cglib.proxy.Enhancer;

public class FabricaDeServico
{
	private static ResourceBundle resource;

	static
	{	try
		{	resource = ResourceBundle.getBundle("servico");
		}
		catch(MissingResourceException e)
		{	System.out.println("Aquivo servico.properties n�o encontrado.");
			throw new RuntimeException(e);
		}
	}

	// Esse m�todo pode ser executado de 2 formas:
	// 1. contaAppService = FabricaDeServico.<ContaAppService>getServico(ContaAppService.class);
	// 2. contaAppService = FabricaDeServico.getServico(ContaAppService.class);
    
	@SuppressWarnings("unchecked")
	public static <T> T getServico(Class<T> tipo)
	{			
		String nomeDaClasse = null; 
	
		try
		{	nomeDaClasse = resource.getString(tipo.getSimpleName());

			Class<?> classeDoServico = Class.forName(nomeDaClasse);
			
			return (T)Enhancer.create (classeDoServico, new InterceptadorDeServico());
	
	        // O  proxy  deve  estender a  classe (ContaAppService por exemplo).
	        // O proxy deve ainda chamar o m�todo intercept() da classe interceptadora, 
			// isto �, da classe InterceptadorDeServico (classe callback).
	        
	        // Enhancer enhancer = new Enhancer();
	        // enhancer.setSuperclass(classeDoServico);             // Superclasse do Servico  
	        // enhancer.setCallback(new InterceptadorDeServico());  // Interceptador do Servico
	
	        // return (T) enhancer.create();
		} 
		catch (ClassNotFoundException e)
		{	System.out.println("Classe " + nomeDaClasse + " n�o encontrada");
			throw new RuntimeException(e);
		}
		catch(MissingResourceException e)
		{	System.out.println("Chave " + tipo + " n�o encontrada em dao.properties");
			throw new RuntimeException(e);
		}
    }
}