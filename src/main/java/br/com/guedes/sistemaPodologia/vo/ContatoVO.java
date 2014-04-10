package br.com.guedes.sistemaPodologia.vo;

import java.io.Serializable;

public class ContatoVO implements Serializable {

	private static final long serialVersionUID = -1074993420183706486L;

	private Integer conCodigo;
	private Integer tcoCodigo;
	private String conDescricao;
	private String conResponsavel;
	private String tcoDescricao;
	
	public Integer getConCodigo() {
		return conCodigo;
	}
	
	public void setConCodigo(Integer conCodigo) {
		this.conCodigo = conCodigo;
	}
	
	public Integer getTcoCodigo() {
		return tcoCodigo;
	}
	
	public void setTcoCodigo(Integer tcoCodigo) {
		this.tcoCodigo = tcoCodigo;
	}
	
	public String getConDescricao() {
		return conDescricao;
	}
	
	public void setConDescricao(String conDescricao) {
		this.conDescricao = conDescricao;
	}
	
	public String getConResponsavel() {
		return conResponsavel;
	}
	
	public void setConResponsavel(String conResponsavel) {
		this.conResponsavel = conResponsavel;
	}
	
	public String getTcoDescricao() {
		return tcoDescricao;
	}
	
	public void setTcoDescricao(String tcoDescricao) {
		this.tcoDescricao = tcoDescricao;
	}
}
