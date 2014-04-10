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
@Table(name="TB_PERFIL")
@SequenceGenerator(name="SEQUENCE_PERFIL", sequenceName = "GEN_PERFIL", allocationSize=1)
public class Perfil implements Serializable {

	private static final long serialVersionUID = 8514012318453159831L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_PERFIL")
	@Column(name="PER_CODIGO")
	private Integer perCodigo;
	
	@Column(name="PER_DESCRICAO", length=80, nullable=false)
	private String perDescricao;	
	
	@Column(name="PER_SIGLA", length=3, nullable=false)
	private String perSigla;

	public Integer getPerCodigo() {
		return perCodigo;
	}

	public void setPerCodigo(Integer perCodigo) {
		this.perCodigo = perCodigo;
	}

	public String getPerDescricao() {
		return perDescricao;
	}

	public void setPerDescricao(String perDescricao) {
		this.perDescricao = perDescricao;
	}

	public String getPerSigla() {
		return perSigla;
	}

	public void setPerSigla(String perSigla) {
		this.perSigla = perSigla;
	}
}
