package br.com.guedes.sistemaPodologia.model;

import java.io.Serializable;

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
@Table(name="TB_DOENCA")
@SequenceGenerator(name="SEQUENCE_DOENCA", sequenceName = "GEN_DOENCA", allocationSize=1)
public class Doenca implements Serializable {

	private static final long serialVersionUID = 2518551715314883555L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_DOENCA")
	@Column(name="DOE_CODIGO")
	private Integer doeCodigo;
	
	@Column(name="DOE_NOME", length=60, nullable=false)
	private String doeNome;	
	
	@Column(name="DOE_CAUSA", length=1028)
	private String doeCausa;
	
	@Column(name="DOE_PRECAUCAO", length=1028)
	private String doePrecaucao;

	public Integer getDoeCodigo() {
		return doeCodigo;
	}

	public void setDoeCodigo(Integer doeCodigo) {
		this.doeCodigo = doeCodigo;
	}

	public String getDoeNome() {
		return doeNome;
	}

	public void setDoeNome(String doeNome) {
		this.doeNome = doeNome;
	}

	public String getDoeCausa() {
		return doeCausa;
	}

	public void setDoeCausa(String doeCausa) {
		this.doeCausa = doeCausa;
	}

	public String getDoePrecaucao() {
		return doePrecaucao;
	}

	public void setDoePrecaucao(String doePrecaucao) {
		this.doePrecaucao = doePrecaucao;
	}
}
