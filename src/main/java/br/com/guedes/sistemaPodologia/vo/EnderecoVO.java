package br.com.guedes.sistemaPodologia.vo;

import java.io.Serializable;

public class EnderecoVO implements Serializable {

	private static final long serialVersionUID = -8863516968047497337L;

	private Integer endCodigo;
	private String endLogadouro;
	private String endNumero;
	private String endBairro;
	private String endCidade;
	private String endCep;
	private Integer estCodigo;
	private String estNome;
	
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
	
	public Integer getEstCodigo() {
		return estCodigo;
	}
	
	public void setEstCodigo(Integer estCodigo) {
		this.estCodigo = estCodigo;
	}
	
	public String getEstNome() {
		return estNome;
	}
	
	public void setEstNome(String estNome) {
		this.estNome = estNome;
	}
}
