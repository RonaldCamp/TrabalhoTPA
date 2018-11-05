package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import anotacao.PersistenceContext;
import dao.ProprietarioDAO;
import excecao.InfraestruturaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Proprietario;
import servico.controle.JPAUtil;

public class ProprietarioDAOImpl implements ProprietarioDAO 
{	
	@PersistenceContext
	public EntityManager em;
	
	public long inclui(Proprietario proprietario) 
	{	
		em.persist(proprietario);
		
		return proprietario.getId();
	}

	public void altera(Proprietario proprietario) 
		throws ObjetoNaoEncontradoException 
	{	
		Proprietario prop = em.find(Proprietario.class, proprietario.getId(), LockModeType.PESSIMISTIC_WRITE);
		
		if(prop == null)
		{	throw new ObjetoNaoEncontradoException();
		}
	
		em.merge(proprietario);
	}

	public void exclui(long id) 
		throws ObjetoNaoEncontradoException 
	{	
		Proprietario proprietario = em.find(Proprietario.class, id, LockModeType.PESSIMISTIC_WRITE);
		
		if(proprietario == null)
		{	throw new ObjetoNaoEncontradoException();
		}

		em.remove(proprietario);
	}

	public Proprietario recuperaProprietario(long id) 
		throws ObjetoNaoEncontradoException 
	{	
		Proprietario proprietario = (Proprietario)em
			.find(Proprietario.class, new Long(id));
		
		if (proprietario == null)
		{	throw new ObjetoNaoEncontradoException();
		}

		return proprietario;
	}

	@Override
	public List<Proprietario> recuperaProprietarios() {
		try {
			EntityManager em = JPAUtil.getEntityManager();

			@SuppressWarnings("unchecked")
			List<Proprietario> proprietarios = em.createQuery("select p from Proprietario p " + "order by p.id asc").getResultList();

			return proprietarios;
		} catch (RuntimeException e) {
			throw new InfraestruturaException(e);
		}
	}

}