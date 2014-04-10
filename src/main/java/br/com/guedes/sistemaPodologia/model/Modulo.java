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
@Table(name="TB_MODULO")
@SequenceGenerator(name="SEQUENCE_MODULO", sequenceName = "GEN_MODULO", allocationSize=1)
public class Modulo implements Serializable {

	private static final long serialVersionUID = -7594364763220310662L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_MODULO")
	@Column(name="MOD_CODIGO")
	private Integer modCodigo;
	
	@Column(name="MOD_DESCRICAO", length=60, nullable=false)
	private String modDescricao;	
	
	@Column(name="MOD_TIPO", length=1, nullable=false)
	private String modTipo;

	public Integer getModCodigo() {
		return modCodigo;
	}

	public void setModCodigo(Integer modCodigo) {
		this.modCodigo = modCodigo;
	}

	public String getModDescricao() {
		return modDescricao;
	}

	public void setModDescricao(String modDescricao) {
		this.modDescricao = modDescricao;
	}

	public String getModTipo() {
		return modTipo;
	}

	public void setModTipo(String modTipo) {
		this.modTipo = modTipo;
	}
}
