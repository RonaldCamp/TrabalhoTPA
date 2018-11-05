package servico;

import java.util.List;

import excecao.ContaNaoEncontradaException;
import excecao.ProprietarioNaoEncontradoException;
import modelo.Proprietario;

public interface ProprietarioAppService
{	
	long inclui(Proprietario umaConta);
	
	void altera(Proprietario umaConta) throws ContaNaoEncontradaException;
	
	void exclui(long numero) throws ContaNaoEncontradaException;
	
	Proprietario recuperaProprietario(long numero) throws ProprietarioNaoEncontradoException;
	
	List<Proprietario> recuperaProprietarios();
}