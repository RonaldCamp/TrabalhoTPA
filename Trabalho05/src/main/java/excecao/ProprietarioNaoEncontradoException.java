package excecao;

public class ProprietarioNaoEncontradoException extends Exception
{	
	private final static long serialVersionUID = 1;
	
	public ProprietarioNaoEncontradoException()
	{	super();
	}

	public ProprietarioNaoEncontradoException(String msg)
	{	super(msg);
	}
}	