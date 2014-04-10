package br.com.guedes.sistemaPodologia.model;

import java.io.Serializable;

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
@Table(name="TB_DIAGNOSTICO")
@SequenceGenerator(name="SEQUENCE_DIAGNOSTICO", sequenceName = "GEN_DIAGNOSTICO", allocationSize=1)
public class Diagnostico implements Serializable {

	private static final long serialVersionUID = 8685100335759090646L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_DIAGNOSTICO")
	@Column(name="DIA_CODIGO")
	private Integer diaCodigo;
	
	@Column(name="DIA_DTDIAGNOSTICO", nullable=false)
	private String diaDtDiagnostico;	
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PRF_CODIGO", nullable=false)
	private Profissional profissional;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PAC_CODIGO", nullable=false)
	private Paciente paciente;

	public Integer getDiaCodigo() {
		return diaCodigo;
	}

	public void setDiaCodigo(Integer diaCodigo) {
		this.diaCodigo = diaCodigo;
	}

	public String getDiaDtDiagnostico() {
		return diaDtDiagnostico;
	}

	public void setDiaDtDiagnostico(String diaDtDiagnostico) {
		this.diaDtDiagnostico = diaDtDiagnostico;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}	
}
