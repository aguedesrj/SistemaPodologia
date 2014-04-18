package br.com.guedes.sistemaPodologia.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.guedes.sistemaPodologia.model.Usuario;

@Controller
@Scope("request")
public class UsuarioAction extends BasicAction {

	private static final long serialVersionUID = 4637443856156725514L;
	private Usuario usuario;
	private String mensagemUsuario;

	public String exibirTelaLogin() {
		return SUCCESS;
	}
	
	public String efetuarLogin() {
		return SUCCESS;
	}	
	
	public String exibirTelaHome() {
		return SUCCESS;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getMensagemUsuario() {
		return mensagemUsuario;
	}

	public void setMensagemUsuario(String mensagemUsuario) {
		this.mensagemUsuario = mensagemUsuario;
	}
}
