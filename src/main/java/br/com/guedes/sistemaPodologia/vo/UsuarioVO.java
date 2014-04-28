package br.com.guedes.sistemaPodologia.vo;

import java.io.Serializable;

public class UsuarioVO implements Serializable {

	private static final long serialVersionUID = -7389096534789112556L;

	private Integer usuCodigo;
	private String usuLogin;
	private String usuSenha;
	private String usuSituacao;
	private PessoaVO pessoaVO;
	
	public UsuarioVO() {
		this.pessoaVO = new PessoaVO();
	}
	
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
	
	public PessoaVO getPessoaVO() {
		return pessoaVO;
	}
	
	public void setPessoaVO(PessoaVO pessoaVO) {
		this.pessoaVO = pessoaVO;
	}
}
