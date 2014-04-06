package br.com.guedes.sistemaPodologia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.guedes.sistemaPodologia.facade.UsuarioFacade;
import br.com.guedes.sistemaPodologia.model.Usuario;
import br.com.guedes.sistemaPodologia.util.BusinessException;
import br.com.guedes.sistemaPodologia.util.Constantes;

@Controller
@Scope("request")
public class UsuarioAction extends BasicAction {

	private static final long serialVersionUID = 4637443856156725514L;
	private Usuario usuario;
	private String mensagemUsuario;

	@Autowired
	private UsuarioFacade usuarioFacade;
	
	public String exibirTelaLogin() {
		return SUCCESS;
	}
	
	public String efetuarLogin() {
		try {
			Usuario usuario = new Usuario();
			usuario.setUsuLogin(getUsuario().getUsuLogin());
			usuario.setUsuSenha(getUsuario().getUsuSenha());
			
			usuario = usuarioFacade.efetuarLogin(usuario.getUsuLogin().trim(), usuario.getUsuSenha().trim());
			
			// Abrir sessão.
			this.getRequest().getSession().setAttribute(Constantes.KEY_USUARIO_SESSION, usuario);
			
			return SUCCESS;				
		} catch (BusinessException e) {
			LOG.error(e.getMessage(), e);
			setMensagemUsuario(e.getMessage());
			return ERROR;
		} catch (Exception e) {
			LOG.fatal(e.getMessage(), e);
			setMensagemUsuario("Serviço de login indisponível.");
			return ERROR;
		}
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
