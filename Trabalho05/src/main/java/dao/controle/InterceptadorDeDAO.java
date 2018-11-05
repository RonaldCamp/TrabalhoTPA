package dao.controle;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.persistence.EntityManager;

import dao.impl.ProprietarioDAOImpl;
import excecao.InfraestruturaException;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import servico.controle.JPAUtil;

public class InterceptadorDeDAO implements MethodInterceptor {
	/*
	 * Parametros:
	 * 
	 * objeto - "this", o objeto "enhanced", isto é, o proxy.
	 * 
	 * metodo - o método interceptado, isto é, um método da interface ContaDAO,
	 * LanceDAO, etc.
	 * 
	 * args - um array de args; tipos primitivos são empacotados. Contém os
	 * argumentos que o método interceptado recebeu.
	 * 
	 * metodoProxy - utilizado para executar um método super. Veja o comentário
	 * abaixo.
	 * 
	 * MethodProxy - Classes geradas pela classe Enhancer passam este objeto para o
	 * objeto MethodInterceptor registrado quando um método interceptado é
	 * executado. Ele pode ser utilizado para invocar o método original, ou chamar o
	 * mesmo método sobre um objeto diferente do mesmo tipo.
	 * 
	 */

	public Object intercept(Object objeto, Method metodo, Object[] args, MethodProxy metodoDoProxy) throws Throwable {
		try {
			Field[] fields = objeto.getClass().getSuperclass().getFields();
			for (int i =0;i<fields.length;i++){
				if (fields[i].isAnnotationPresent(anotacao.PersistenceContext.class))
					fields[i].set(objeto, JPAUtil.getEntityManager());
			}
			return  metodoDoProxy.invokeSuper(objeto, args);
			
		} catch (RuntimeException e) {
			throw new InfraestruturaException(e);
		}

	}
}
