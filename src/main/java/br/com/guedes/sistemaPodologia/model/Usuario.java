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
@Table(name="TB_USUARIO")
@SequenceGenerator(name="SEQUENCE_USUARIO", sequenceName = "GEN_USUARIO", allocationSize=1)
public class Usuario implements Serializable {

	private static final long serialVersionUID = 3482950945370983014L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_USUARIO")
	@Column(name="USU_CODIGO")
	private Integer usuCodigo;
	
	@Column(name="USU_LOGIN", length=10, nullable=false)
	private String usuLogin;	
	
	@Column(name="USU_SENHA", length=10, nullable=false)
	private String usuSenha;
	
	@Column(name="USU_SITUACAO", length=1, nullable=false)
	private String usuSituacao;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PES_CODIGO")
	private Pessoa pessoa;

	public Integer getUsuCodigo() {
		return usuCodigo;
	}

	public void setUsuCodigo(Integer usuCodigo) {
		this.usuCodigo = usuCodigo;
	}

	public String getUsuLogin() {
		return usuLogin;
	}

	public void setUsuLogin(String usuLogin) {
		this.usuLogin = usuLogin;
	}

	public String getUsuSenha() {
		return usuSenha;
	}

	public void setUsuSenha(String usuSenha) {
		this.usuSenha = usuSenha;
	}

	public String getUsuSituacao() {
		return usuSituacao;
	}

	public void setUsuSituacao(String usuSituacao) {
		this.usuSituacao = usuSituacao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}	
}
