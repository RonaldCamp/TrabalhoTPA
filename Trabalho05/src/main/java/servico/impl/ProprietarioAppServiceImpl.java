package servico.impl;

import java.util.List;

import anotacao.Autowired;
import anotacao.RollbackFor;
import anotacao.Transactional;
import dao.ProprietarioDAO;
import excecao.ClienteNaoEncontradoException;
import excecao.ContaNaoEncontradaException;
import excecao.ObjetoNaoEncontradoException;
import excecao.ProprietarioNaoEncontradoException;
import modelo.Proprietario;
import servico.ProprietarioAppService;

public class ProprietarioAppServiceImpl implements ProprietarioAppService
{	
	@Autowired
	public static ProprietarioDAO contaDAO;

	@Transactional
	public long inclui(Proprietario proprietario) 
	{	
		System.out.println("\nDentro de ContaAppServiceImpl. Vai chamar o método inclui() de ContaDAOImpl.");
		
		long numero = contaDAO.inclui(proprietario);
		
		System.out.println("\nDentro de ContaAppServiceImpl. Chamou o método inclui() de ContaDAOImpl.");
		
		return numero;
	}

	
	@Transactional
	@RollbackFor(nomes={ContaNaoEncontradaException.class, 
			            ClienteNaoEncontradoException.class})
	public void altera(Proprietario umaConta)
		throws ContaNaoEncontradaException
	{	
		try
		{	
			System.out.println("\nVai chamar o método altera() de ContaDAOImpl.");

			contaDAO.altera(umaConta);
			
			System.out.println("\nChamou o método altera() de ContaDAOImpl.");
		} 
		catch(ObjetoNaoEncontradoException e)
		{	
			throw new ContaNaoEncontradaException("Conta não encontrada");
		}
	}
	
	@Transactional
	public void exclui(long numero) 
		throws ContaNaoEncontradaException
	{	
		try
		{	
			System.out.println("Vai chamar o método exclui() de ContaDAOImpl.");

			contaDAO.exclui(numero);

			System.out.println("Chamou o método exclui() de ContaDAOImpl.");
		} 
		catch(ObjetoNaoEncontradoException e)
		{	
		    throw new ContaNaoEncontradaException("Conta não encontrada");
		}
	}

	public Proprietario recuperaProprietario(long id) throws ProprietarioNaoEncontradoException
	{	
		// System.out.println("Vai chamar o método recuperaContas() de ContaDAOImpl.");

		Proprietario proprietario = null;
		try {
			proprietario = contaDAO.recuperaProprietario(id);
		} catch (ObjetoNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// System.out.println("Chamou o método recuperaContas() de ContaDAOImpl.");

		return proprietario;
	}
	
	public List<Proprietario> recuperaProprietarios() 
	{	
		// System.out.println("Vai chamar o método recuperaContas() de ContaDAOImpl.");

		List<Proprietario> contas = contaDAO.recuperaProprietarios();
		
		// System.out.println("Chamou o método recuperaContas() de ContaDAOImpl.");

		return contas;
	}
	
}