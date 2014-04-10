package br.com.guedes.sistemaPodologia.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="VW_USUARIO")
public class VW_Usuario implements Serializable {

	private static final long serialVersionUID = -6169024580077090707L;

	@Id
	@Column(name="CODIGOPESSOA")
	private Integer codigoPessoa;
	
	@Column(name="NOME", length=80)
	private String nome;	
	
	@Column(name="DATACADASTRO")
	private Date dataCadastro;
	
	@Column(name="CODIGOUSUARIO")
	private Integer codigousuario;
	
	@Column(name="LOGIN", length=10)
	private String login;
	
	@Column(name="SENHA", length=10)
	private String senha;
	
	@Column(name="SITUACAO", length=1)
	private String situacao;

	public Integer getCodigoPessoa() {
		return codigoPessoa;
	}

	public void setCodigoPessoa(Integer codigoPessoa) {
		this.codigoPessoa = codigoPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Integer getCodigousuario() {
		return codigousuario;
	}

	public void setCodigousuario(Integer codigousuario) {
		this.codigousuario = codigousuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
}
