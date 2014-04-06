package br.com.guedes.sistemaPodologia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TB_ENDERECO")
@SequenceGenerator(name="SEQUENCE_ENDERECO", sequenceName = "GEN_ENDERECO", allocationSize=1)
public class Endereco implements Serializable {

	private static final long serialVersionUID = -7376599617743165402L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_ENDERECO")
	@Column(name="END_CODIGO")
	private Integer endCodigo;
	
	@Column(name="END_LOGADOURO", length=100)
	private String endLogadouro;
	
	@Column(name="END_NUMERO", length=8)
	private String endNumero;
	
	@Column(name="END_BAIRRO", length=60)
	private String endBairro;

	@Column(name="END_CIDADE", length=60)
	private String endCidade;
	
	@Column(name="END_CEP", length=12)
	private String endCep;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="EST_CODIGO")
	private Estado estado;

	public Integer getEndCodigo() {
		return endCodigo;
	}

	public void setEndCodigo(Integer endCodigo) {
		this.endCodigo = endCodigo;
	}

	public String getEndLogadouro() {
		return endLogadouro;
	}

	public void setEndLogadouro(String endLogadouro) {
		this.endLogadouro = endLogadouro;
	}

	public String getEndNumero() {
		return endNumero;
	}

	public void setEndNumero(String endNumero) {
		this.endNumero = endNumero;
	}

	public String getEndBairro() {
		return endBairro;
	}

	public void setEndBairro(String endBairro) {
		this.endBairro = endBairro;
	}

	public String getEndCidade() {
		return endCidade;
	}

	public void setEndCidade(String endCidade) {
		this.endCidade = endCidade;
	}

	public String getEndCep() {
		return endCep;
	}

	public void setEndCep(String endCep) {
		this.endCep = endCep;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
