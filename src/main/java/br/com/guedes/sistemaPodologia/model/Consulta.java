package br.com.guedes.sistemaPodologia.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TB_CONSULTA")
@SequenceGenerator(name="SEQUENCE_CONSULTA", sequenceName = "GEN_CONSULTA", allocationSize=1)
public class Consulta implements Serializable {

	private static final long serialVersionUID = 353407869921196556L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_CONSULTA")
	@Column(name="COS_CODIGO")
	private Integer cosCodigo;
	
	@Column(name="COS_DTCONSULTA", nullable=false)
	private Date cosDtConsulta;	
	
	@Column(name="COS_FLGTIPO", length=1)
	private String cosFlgTipo;
	
	@Column(name="COS_FLGPRIMEIRO", length=1, nullable=false)
	private String cosFlgPrimeiro;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PRF_CODIGO", nullable=false)
	private Profissional profissional;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="USU_CODIGO", nullable=false)
	private Usuario usuario;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PAC_CODIGO", nullable=false)
	private Paciente paciente;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="HOR_CODIGO", nullable=false)
	private Hora hora;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="TRA_CODIGO", nullable=false)
	private Tratamento tratamento;

	public Integer getCosCodigo() {
		return cosCodigo;
	}

	public void setCosCodigo(Integer cosCodigo) {
		this.cosCodigo = cosCodigo;
	}

	public Date getCosDtConsulta() {
		return cosDtConsulta;
	}

	public void setCosDtConsulta(Date cosDtConsulta) {
		this.cosDtConsulta = cosDtConsulta;
	}

	public String getCosFlgTipo() {
		return cosFlgTipo;
	}

	public void setCosFlgTipo(String cosFlgTipo) {
		this.cosFlgTipo = cosFlgTipo;
	}

	public String getCosFlgPrimeiro() {
		return cosFlgPrimeiro;
	}

	public void setCosFlgPrimeiro(String cosFlgPrimeiro) {
		this.cosFlgPrimeiro = cosFlgPrimeiro;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Hora getHora() {
		return hora;
	}

	public void setHora(Hora hora) {
		this.hora = hora;
	}

	public Tratamento getTratamento() {
		return tratamento;
	}

	public void setTratamento(Tratamento tratamento) {
		this.tratamento = tratamento;
	}
}
