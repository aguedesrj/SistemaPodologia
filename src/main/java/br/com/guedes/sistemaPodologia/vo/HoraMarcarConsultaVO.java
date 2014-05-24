package br.com.guedes.sistemaPodologia.vo;

import java.io.Serializable;

public class HoraMarcarConsultaVO implements Serializable {

	private static final long serialVersionUID = -5961856535342870728L;

	private Integer hmcCodigo;
	private String hmlDescricao;
	
	public HoraMarcarConsultaVO() {}
	
	public HoraMarcarConsultaVO(Integer hmcCodigo, String hmlDescricao) {
		this.hmcCodigo = hmcCodigo;
		this.hmlDescricao = hmlDescricao;
	}
	
	public Integer getHmcCodigo() {
		return hmcCodigo;
	}
	
	public void setHmcCodigo(Integer hmcCodigo) {
		this.hmcCodigo = hmcCodigo;
	}
	
	public String getHmlDescricao() {
		return hmlDescricao;
	}

	public void setHmlDescricao(String hmlDescricao) {
		this.hmlDescricao = hmlDescricao;
	}
}
