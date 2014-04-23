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
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TB_PACIENTE")
@SequenceGenerator(name="SEQUENCE_PACIENTE", sequenceName = "GEN_PACIENTE", allocationSize=1)
public class Paciente implements Serializable {

	private static final long serialVersionUID = 4438770472658995203L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_PACIENTE")
	@Column(name="PAC_CODIGO")
	private Integer pacCodigo;
	
	@Column(name="PAC_LABORA", length=1)
	private String pacLabora;
	
	@Column(name="PAC_ANDADESCALCO", length=1)
	private String pacAndaDescalco;	
	
	@Column(name="PAC_UNHAENGRAVADA", length=1)
	private String pacUnhaEngravada;	
	
	@Column(name="PAC_CIRURGIAPES", length=1)
	private String pacCirurgiaPes;	
	
	@Column(name="PAC_CIRURGIAMOTIVO", length=255)
	private String pacCirurgiaMotivo;
	
	@Column(name="PAC_CALCADOUTILIZA", length=100)
	private String pacCalcadoUtiliza;

	@Column(name="PAC_VISITAPEDICURO", length=1)
	private String pacVisitaPedicuro;
	
	@Column(name="PAC_NUMEROCALCADO", length=2)
	private String pacNumeroCalcado;
	
	@Column(name="PAC_ALERGICOMED", length=1)
	private String pacAlergicoMedicamentos;
	
	@Column(name="PAC_ALERGICOQUAIS", length=255)
	private String pacAlergicoQuais;
	
	@Column(name="PAC_DIABETES", length=1)
	private String pacDiabetes;
	
	@Column(name="PAC_HIPERTENSAO", length=1)
	private String pacHipertensao;
	
	@Column(name="PAC_TABAGISMO", length=1)
	private String pacTabagismo;
	
	@Column(name="PAC_PESO")
	private Integer pacPeso;
	
	@Column(name="PAC_ALTURA", length=4)
	private String pacAltura;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PES_CODIGO")
	private Pessoa pessoa;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PRO_CODIGO")
	private Profissao profissao;
	
	@Transient
	private Consulta consulta;

	public Integer getPacCodigo() {
		return pacCodigo;
	}

	public void setPacCodigo(Integer pacCodigo) {
		this.pacCodigo = pacCodigo;
	}

	public String getPacLabora() {
		return pacLabora;
	}

	public void setPacLabora(String pacLabora) {
		this.pacLabora = pacLabora;
	}

	public String getPacAndaDescalco() {
		return pacAndaDescalco;
	}

	public void setPacAndaDescalco(String pacAndaDescalco) {
		this.pacAndaDescalco = pacAndaDescalco;
	}

	public String getPacUnhaEngravada() {
		return pacUnhaEngravada;
	}

	public void setPacUnhaEngravada(String pacUnhaEngravada) {
		this.pacUnhaEngravada = pacUnhaEngravada;
	}

	public String getPacCirurgiaPes() {
		return pacCirurgiaPes;
	}

	public void setPacCirurgiaPes(String pacCirurgiaPes) {
		this.pacCirurgiaPes = pacCirurgiaPes;
	}

	public String getPacCirurgiaMotivo() {
		return pacCirurgiaMotivo;
	}

	public void setPacCirurgiaMotivo(String pacCirurgiaMotivo) {
		this.pacCirurgiaMotivo = pacCirurgiaMotivo;
	}

	public String getPacCalcadoUtiliza() {
		return pacCalcadoUtiliza;
	}

	public void setPacCalcadoUtiliza(String pacCalcadoUtiliza) {
		this.pacCalcadoUtiliza = pacCalcadoUtiliza;
	}

	public String getPacVisitaPedicuro() {
		return pacVisitaPedicuro;
	}

	public void setPacVisitaPedicuro(String pacVisitaPedicuro) {
		this.pacVisitaPedicuro = pacVisitaPedicuro;
	}

	public String getPacNumeroCalcado() {
		return pacNumeroCalcado;
	}

	public void setPacNumeroCalcado(String pacNumeroCalcado) {
		this.pacNumeroCalcado = pacNumeroCalcado;
	}

	public String getPacAlergicoMedicamentos() {
		return pacAlergicoMedicamentos;
	}

	public void setPacAlergicoMedicamentos(String pacAlergicoMedicamentos) {
		this.pacAlergicoMedicamentos = pacAlergicoMedicamentos;
	}

	public String getPacAlergicoQuais() {
		return pacAlergicoQuais;
	}

	public void setPacAlergicoQuais(String pacAlergicoQuais) {
		this.pacAlergicoQuais = pacAlergicoQuais;
	}

	public String getPacDiabetes() {
		return pacDiabetes;
	}

	public void setPacDiabetes(String pacDiabetes) {
		this.pacDiabetes = pacDiabetes;
	}

	public String getPacHipertensao() {
		return pacHipertensao;
	}

	public void setPacHipertensao(String pacHipertensao) {
		this.pacHipertensao = pacHipertensao;
	}

	public String getPacTabagismo() {
		return pacTabagismo;
	}

	public void setPacTabagismo(String pacTabagismo) {
		this.pacTabagismo = pacTabagismo;
	}

	public Integer getPacPeso() {
		return pacPeso;
	}

	public void setPacPeso(Integer pacPeso) {
		this.pacPeso = pacPeso;
	}

	public String getPacAltura() {
		return pacAltura;
	}

	public void setPacAltura(String pacAltura) {
		this.pacAltura = pacAltura;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Profissao getProfissao() {
		return profissao;
	}

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}	
}
