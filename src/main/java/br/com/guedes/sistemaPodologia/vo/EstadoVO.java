package br.com.guedes.sistemaPodologia.vo;

import java.io.Serializable;

public class EstadoVO implements Serializable {

	private static final long serialVersionUID = 4721999398341259262L;

	private Integer estCodigo;
	private String estNome;
	private String estSigla;
	
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
	
	public String getEstSigla() {
		return estSigla;
	}

	public void setEstSigla(String estSigla) {
		this.estSigla = estSigla;
	}
}
