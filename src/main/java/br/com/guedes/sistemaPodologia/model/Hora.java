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
@Table(name="TB_HORA")
@SequenceGenerator(name="SEQUENCE_HORA", sequenceName = "GEN_HORA", allocationSize=1)
public class Hora implements Serializable {

	private static final long serialVersionUID = -5030239405616965237L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_HORA")
	@Column(name="HOR_CODIGO")
	private Integer horCodigo;
	
	@Column(name="HOR_INICIO", length=5, nullable=false)
	private String horInicio;	
	
	@Column(name="HOR_FIM", length=5)
	private String horFim;
	
	@Column(name="HOR_TIPO", length=1, nullable=false)
	private String horTipo;

	public Integer getHorCodigo() {
		return horCodigo;
	}

	public void setHorCodigo(Integer horCodigo) {
		this.horCodigo = horCodigo;
	}

	public String getHorInicio() {
		return horInicio;
	}

	public void setHorInicio(String horInicio) {
		this.horInicio = horInicio;
	}

	public String getHorFim() {
		return horFim;
	}

	public void setHorFim(String horFim) {
		this.horFim = horFim;
	}

	public String getHorTipo() {
		return horTipo;
	}

	public void setHorTipo(String horTipo) {
		this.horTipo = horTipo;
	}
}
