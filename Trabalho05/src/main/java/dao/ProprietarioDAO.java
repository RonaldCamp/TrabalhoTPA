package dao;

import java.util.List;

import excecao.ObjetoNaoEncontradoException;
import modelo.Proprietario;

public interface ProprietarioDAO
{	
	long inclui(Proprietario umaConta); 

	void altera(Proprietario umaConta)
		throws ObjetoNaoEncontradoException; 
	
	void exclui(long id) 
		throws ObjetoNaoEncontradoException; 
	
	Proprietario recuperaProprietario(long numero) 
		throws ObjetoNaoEncontradoException;

	List<Proprietario> recuperaProprietarios(); 

}