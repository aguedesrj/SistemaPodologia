package br.com.guedes.sistemaPodologia.vo;

import java.io.Serializable;

public class TipoContatoVO implements Serializable {

	private static final long serialVersionUID = -8043540787848665741L;

	private Integer tcoCodigo;
	private String tcoDescricao;
	
	public Integer getTcoCodigo() {
		return tcoCodigo;
	}
	
	public void setTcoCodigo(Integer tcoCodigo) {
		this.tcoCodigo = tcoCodigo;
	}
	
	public String getTcoDescricao() {
		if (this.tcoDescricao == null) {
			return "";
		}
		return tcoDescricao;
	}
	
	public void setTcoDescricao(String tcoDescricao) {
		this.tcoDescricao = tcoDescricao;
	}
}
