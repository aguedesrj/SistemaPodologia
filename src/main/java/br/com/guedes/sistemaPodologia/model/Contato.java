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
@Table(name="TB_PESSOA_CONTATO")
@SequenceGenerator(name="SEQUENCE_PESSOA_CONTATO", sequenceName = "GEN_PESSOA_CONTATO", allocationSize=1)
public class Contato implements Serializable {

	private static final long serialVersionUID = 5580124672982648045L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_PESSOA_CONTATO")
	@Column(name="CON_CODIGO")
	private Integer conCodigo;
	
	@Column(name="CON_DESCRICAO", length=60)
	private String conDescricao;	
	
	@Column(name="CON_RESPONSAVEL", length=80)
	private String conResponsavel;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PES_CODIGO", nullable=false)
	private Pessoa pessoa;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="TCO_CODIGO", nullable=false)
	private TipoContato tipoContato;

	public Integer getConCodigo() {
		return conCodigo;
	}

	public void setConCodigo(Integer conCodigo) {
		this.conCodigo = conCodigo;
	}

	public String getConDescricao() {
		return conDescricao;
	}

	public void setConDescricao(String conDescricao) {
		this.conDescricao = conDescricao;
	}

	public String getConResponsavel() {
		return conResponsavel;
	}

	public void setConResponsavel(String conResponsavel) {
		this.conResponsavel = conResponsavel;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public TipoContato getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(TipoContato tipoContato) {
		this.tipoContato = tipoContato;
	}	
}
