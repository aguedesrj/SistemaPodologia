package br.com.guedes.sistemaPodologia.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.guedes.sistemaPodologia.facade.UsuarioFacade;
import br.com.guedes.sistemaPodologia.model.Usuario;
import br.com.guedes.sistemaPodologia.util.BusinessException;
import br.com.guedes.sistemaPodologia.util.Constantes;
import br.com.guedes.sistemaPodologia.util.JobsTask;
import br.com.guedes.sistemaPodologia.vo.UsuarioVO;

@Controller
@Scope("request")
public class UsuarioAction extends BasicAction {

	private static final long serialVersionUID = 4637443856156725514L;
	private static final Logger LOGGER = Logger.getLogger(UsuarioAction.class);
	
	@Autowired
	private UsuarioFacade usuarioFacade;
	
	private UsuarioVO usuarioVO;
	private String mensagemUsuario;

	public String exibirTelaLogin() {
		return SUCCESS;
	}
	
	public String efetuarLogin() {
		try {
			LOGGER.info("Efetuando login.");
			Usuario usuario = usuarioFacade.efetuarLogin(getUsuarioVO().getUsuLogin().trim(), getUsuarioVO().getUsuSenha().trim());
			// Abrir sessão.
			this.getRequest().getSession().setAttribute(Constantes.KEY_USUARIO_SESSION, usuario);
			return SUCCESS;				
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			setMensagemUsuario(e.getMessage());
			return ERROR;
		} catch (Exception e) {
			LOGGER.fatal(e.getMessage(), e);
			setMensagemUsuario("Serviço de Login indisponível.");
			return ERROR;
		}
	}
	
	public String efetuarBackupEnvioEmail() {
		try {
			JobsTask jobsTask = new JobsTask();
			jobsTask.efetuarBackupEnvioEmail();
			setMensagemUsuario("Backup realizado com sucesso.");
			return SUCCESS;
		} catch (Exception e) {
			LOGGER.fatal(e.getMessage(), e);
			setMensagemUsuario("Problema ao efetuar backup.");
			return ERROR;
		}
	}
	
	public String exibirTelaHome() {
		return SUCCESS;
	}

	public String getMensagemUsuario() {
		return mensagemUsuario;
	}

	public void setMensagemUsuario(String mensagemUsuario) {
		this.mensagemUsuario = mensagemUsuario;
	}

	public UsuarioVO getUsuarioVO() {
		return usuarioVO;
	}

	public void setUsuarioVO(UsuarioVO usuarioVO) {
		this.usuarioVO = usuarioVO;
	}
}
