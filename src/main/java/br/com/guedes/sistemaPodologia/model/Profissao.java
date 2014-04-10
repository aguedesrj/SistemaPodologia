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
@Table(name="TB_PROFISSAO")
@SequenceGenerator(name="SEQUENCE_PROFISSAO", sequenceName = "GEN_PROFISSAO", allocationSize=1)
public class Profissao implements Serializable {

	private static final long serialVersionUID = 7587437505358058483L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_PROFISSAO")
	@Column(name="PRO_CODIGO")
	private Integer proCodigo;
	
	@Column(name="PRO_NOME", length=60, nullable=false)
	private String proNome;

	public Integer getProCodigo() {
		return proCodigo;
	}

	public void setProCodigo(Integer proCodigo) {
		this.proCodigo = proCodigo;
	}

	public String getProNome() {
		return proNome;
	}

	public void setProNome(String proNome) {
		this.proNome = proNome;
	}	
}
