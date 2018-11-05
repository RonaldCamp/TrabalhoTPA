import java.util.List;

import corejava.Console;
import excecao.ContaNaoEncontradaException;
import excecao.ProprietarioNaoEncontradoException;
import modelo.Proprietario;
import servico.ProprietarioAppService;
import servico.controle.FabricaDeServico;
import util.Util;

public class Principal
{	public static void main (String[] args) 
	{	
		Integer numeroDeImoveis;
		String dataAquisicao;
		Proprietario proprietario;

		System.out.println("\nVai criar o proxy de serviço");
		
		ProprietarioAppService proprietarioAppService = FabricaDeServico.getServico(ProprietarioAppService.class);

		System.out.println("\nCriou o proxy. Classe de implementação = " + proprietarioAppService.getClass().getName());

		boolean continua = true;
		while (continua)
		{	System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um Proprietario");
			System.out.println("2. Alterar um Proprietario");
			System.out.println("3. Remover uma proprietario");
			System.out.println("4. Listar todas os proprietarios");
			System.out.println("8. Sair");
						
			int opcao = Console.readInt('\n' + 
							"Digite um número entre 1 e 6:");
					
			switch (opcao)
			{	case 1:
				{
					numeroDeImoveis = Console.readInt(
						"Informe o numero de Imoveis pertencentes a essa pessoa: ");
					dataAquisicao = Console.readLine(
						"Informe a data de cadastro do conta: ");
						
					proprietario = new Proprietario(numeroDeImoveis, Util.strToDate(dataAquisicao));

					System.out.println("\nDentro do Principal. Vai chamar contaAppService.inclui");
					
					long numero = proprietarioAppService.inclui(proprietario);
					
					System.out.println("\nDentro do Principal. Chamou contaAppService.inclui");
					
					System.out.println('\n' + "Conta número " + 
					    numero + " incluído com sucesso!");	

					break;
				}

				case 2:
				{	int resposta = Console.readInt('\n' + 
						"Digite o número do conta que você deseja alterar: ");
										
					try
					{	proprietario = proprietarioAppService.recuperaProprietario(resposta);
					}
					catch(ProprietarioNaoEncontradoException e)
					{	System.out.println('\n' + e.getMessage());
						break;
					}
										
					System.out.println('\n' + 
						"Número = " + proprietario.getId() + 
						"    Numero_De_Imoveis = " + proprietario.getNumeroDeImoveis());
												
					System.out.println('\n' + "O que você deseja alterar?");
					System.out.println('\n' + "1. Saldo");

					int opcaoAlteracao = Console.readInt('\n' + 
											"Digite o número de 1:");
					
					switch (opcaoAlteracao)
					{	case 1:
							int numero = Console.
										readInt("Digite o novo saldo: ");
							
							proprietario.setNumeroDeImoveis(numero);

							try
							{	
								System.out.println("\nDentro do Principal. Vai chamar contaAppService.altera");
								
								proprietarioAppService.altera(proprietario);
							
								System.out.println("\nDentro do Principal. Chamou contaAppService.altera");

								System.out.println('\n' + 
									"Alteração de nome efetuada com sucesso!");
							}
							catch(ContaNaoEncontradaException e)
							{	System.out.println('\n' + e.getMessage());
							}
								
							break;
					
						default:
							System.out.println('\n' + "Opção inválida!");
					}

					break;
				}

				case 3:
				{	int resposta = Console.readInt('\n' + 
						"Digite o número da conta que você deseja remover: ");
									
					try
					{	proprietario = proprietarioAppService.
										recuperaProprietario(resposta);
					}
					catch(ProprietarioNaoEncontradoException e)
					{	System.out.println('\n' + e.getMessage());
						break;
					}
										
					System.out.println('\n' + 
						"Número = " + proprietario.getId() + 
						"    Saldo = " + proprietario.getNumeroDeImoveis());
														
					String resp = Console.readLine('\n' + 
						"Confirma a remoção da conta?");

					if(resp.equals("s"))
					{	try
						{	
							//System.out.println("\nDentro do Principal. Vai chamar contaAppService.exclui");
							
							proprietarioAppService.exclui (proprietario.getId());
						
							//System.out.println("\nDentro do Principal. Chamou contaAppService.exclui");
						
							System.out.println('\n' + 
								"Proprietario removido com sucesso!");
						}
						catch(ContaNaoEncontradaException e)
						{	System.out.println('\n' + e.getMessage());
						}
					}
					else
					{	System.out.println('\n' + 
							"Proprietario não removido.");
					}
					
					break;
				}

				case 4:
				{	
					List<Proprietario> contas = proprietarioAppService.recuperaProprietarios();

					for (Proprietario conta : contas)
					{	
						System.out.println('\n' + 
							"Id = " + conta.getId() +
							"  Saldo = " + conta.getNumeroDeImoveis() +
							"  Data Cadastro = " + Util.dateToStr(conta.getDataCadastro()));
					}
					
					break;
				}

				case 8:
				{	continua = false;
					break;
				}

				default:
					System.out.println('\n' + "Opção inválida!");
			}
		}		
	}
}
