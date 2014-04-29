package br.com.guedes.sistemaPodologia.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="TB_PROFISSIONAL")
@SequenceGenerator(name="SEQUENCE_PROFISSIONAL", sequenceName = "GEN_PROFISSIONAL", allocationSize=1)
public class Profissional implements Serializable {

	private static final long serialVersionUID = -8253033202980505054L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_PROFISSIONAL")
	@Column(name="PRF_CODIGO")
	private Integer prfCodigo;
	
	@Column(name="PRF_DTFORMACAO")
	private Date prfDtFormacao;
	
	@Column(name="PRF_DESCRICAOFORMACAO", length=255)
	private String prfDescricaoFormacao;	
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PES_CODIGO")
	private Pessoa pessoa;

	public Integer getPrfCodigo() {
		return prfCodigo;
	}

	public void setPrfCodigo(Integer prfCodigo) {
		this.prfCodigo = prfCodigo;
	}

	public Date getPrfDtFormacao() {
		return prfDtFormacao;
	}

	public void setPrfDtFormacao(Date prfDtFormacao) {
		this.prfDtFormacao = prfDtFormacao;
	}

	public String getPrfDescricaoFormacao() {
		return prfDescricaoFormacao;
	}

	public void setPrfDescricaoFormacao(String prfDescricaoFormacao) {
		this.prfDescricaoFormacao = prfDescricaoFormacao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
