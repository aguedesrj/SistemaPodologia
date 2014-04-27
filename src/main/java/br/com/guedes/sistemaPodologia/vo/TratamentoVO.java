package br.com.guedes.sistemaPodologia.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class TratamentoVO implements Serializable {

	private static final long serialVersionUID = -8852410956205447224L;

	private Integer traCodigo;
	private String traDescricao;
	private Integer traTempo;
	private BigDecimal traPreco;
	private String traObs;
	private String traFlag;
	
	public Integer getTraCodigo() {
		return traCodigo;
	}
	
	public void setTraCodigo(Integer traCodigo) {
		this.traCodigo = traCodigo;
	}
	
	public String getTraDescricao() {
		return traDescricao;
	}
	
	public void setTraDescricao(String traDescricao) {
		this.traDescricao = traDescricao;
	}
	
	public Integer getTraTempo() {
		return traTempo;
	}
	
	public void setTraTempo(Integer traTempo) {
		this.traTempo = traTempo;
	}
	
	public BigDecimal getTraPreco() {
		return traPreco;
	}
	
	public void setTraPreco(BigDecimal traPreco) {
		this.traPreco = traPreco;
	}
	
	public String getTraObs() {
		return traObs;
	}
	
	public void setTraObs(String traObs) {
		this.traObs = traObs;
	}
	
	public String getTraFlag() {
		return traFlag;
	}
	
	public void setTraFlag(String traFlag) {
		this.traFlag = traFlag;
	}
}
