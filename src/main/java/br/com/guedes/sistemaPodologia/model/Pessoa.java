package br.com.guedes.sistemaPodologia.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TB_PESSOA")
@SequenceGenerator(name="SEQUENCE_PESSOA", sequenceName = "GEN_PESSOA", allocationSize=1)
public class Pessoa implements Serializable {

	private static final long serialVersionUID = -5697865027593323582L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_PESSOA")
	@Column(name="PES_CODIGO")
	private Integer pesCodigo;
	
	@Column(name="PES_NOME", length=80, nullable=false)
	private String pesNome;	
	
	@Column(name="PES_SEXO")
	private String pesSexo;		

	@Column(name="PES_DTNASCIMENTO")
	private Calendar pesDtNascimento;
	
	@Column(name="PES_OBS", length=2048)
	private String pesObs;	
	
	@Column(name="PES_DTCADASTRO")
	private Calendar pesDtCadastro;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="END_CODIGO")
	private Endereco endereco;
	
	@Transient
	private Paciente paciente;	
	
	@OneToMany(mappedBy = "pessoa", fetch=FetchType.EAGER)
	private Set<Contato> listaContato = new HashSet<Contato>();
	
	public Integer getPesCodigo() {
		return pesCodigo;
	}

	public void setPesCodigo(Integer pesCodigo) {
		this.pesCodigo = pesCodigo;
	}

	public String getPesNome() {
		return pesNome;
	}

	public void setPesNome(String pesNome) {
		this.pesNome = pesNome;
	}

	public String getPesSexo() {
		return pesSexo;
	}

	public void setPesSexo(String pesSexo) {
		this.pesSexo = pesSexo;
	}

	public Calendar getPesDtNascimento() {
		return pesDtNascimento;
	}

	public void setPesDtNascimento(Calendar pesDtNascimento) {
		this.pesDtNascimento = pesDtNascimento;
	}

	public String getPesObs() {
		return pesObs;
	}

	public void setPesObs(String pesObs) {
		this.pesObs = pesObs;
	}

	public Calendar getPesDtCadastro() {
		return pesDtCadastro;
	}

	public void setPesDtCadastro(Calendar pesDtCadastro) {
		this.pesDtCadastro = pesDtCadastro;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Set<Contato> getListaContato() {
		return listaContato;
	}

	public void setListaContato(Set<Contato> listaContato) {
		this.listaContato = listaContato;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
}
