package br.com.guedes.sistemaPodologia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TB_ESTADO")
public class Estado implements Serializable {

	private static final long serialVersionUID = -7629676618290162665L;

	@Id
	@Column(name="EST_CODIGO")
	private Integer lojCodigo;
	
	@Column(name="EST_NOME", length=60, nullable=false)
	private String estNome;
	
	@Column(name="EST_SIGLA", length=2, nullable=false)
	private String estSigla;

	public Integer getLojCodigo() {
		return lojCodigo;
	}

	public void setLojCodigo(Integer lojCodigo) {
		this.lojCodigo = lojCodigo;
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
