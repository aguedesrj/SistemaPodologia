package br.com.guedes.sistemaPodologia.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TB_TRATAMENTO")
@SequenceGenerator(name="SEQUENCE_TRATAMENTO", sequenceName = "GEN_TRATAMENTO", allocationSize=1)
public class Tratamento implements Serializable {

	private static final long serialVersionUID = 922290021080616435L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_TRATAMENTO")
	@Column(name="TRA_CODIGO")
	private Integer traCodigo;
	
	@Column(name="TRA_DESCRICAO", length=80, nullable=false)
	private String traDescricao;
	
	@Column(name="TRA_TEMPO", nullable=false)
	private Integer traTempo;
	
	@Column(name="TRA_PRECO", nullable=false)
	private BigDecimal traPreco;	

	@Column(name="TRA_OBS", length=255)
	private String traObs;
	
	@Column(name="TRA_FLAG", length=1)
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
