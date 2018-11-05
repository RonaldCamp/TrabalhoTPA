package modelo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="proprietario")
//@SequenceGenerator(name="SEQUENCIA", 
//		           sequenceName="SEQ_PRODUTO",
//		           allocationSize=1)

public class Proprietario
{	
	private Long id;
	private Integer numeroDeImoveis;
	private Date dataCadastro;

	// ********* Construtores *********
	

	public Proprietario()
	{
	}

	public Proprietario(Integer numeroDeImoveis, Date dataAquisicao)
	{	
		this.numeroDeImoveis = numeroDeImoveis;
		this.dataCadastro = dataAquisicao;	
	}

	// ********* Métodos do Tipo Get *********
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Long getId()
	{	return id;
	}
	
	public Integer getNumeroDeImoveis() {
		return numeroDeImoveis;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	// ********* Métodos do Tipo Set *********


	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@SuppressWarnings("unused")
	private void setId(Long id)
	{	this.id = id;
	}
	
	public void setNumeroDeImoveis(Integer numeroDeImoveis) {
		this.numeroDeImoveis = numeroDeImoveis;
	}

}

