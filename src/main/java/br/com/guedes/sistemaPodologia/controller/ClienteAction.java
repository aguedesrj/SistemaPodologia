package br.com.guedes.sistemaPodologia.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.guedes.sistemaPodologia.facade.ClienteFacade;
import br.com.guedes.sistemaPodologia.model.TipoContato;
import br.com.guedes.sistemaPodologia.vo.ClienteVO;
import br.com.guedes.sistemaPodologia.vo.ContatoVO;

@Controller
@Scope("request")
public class ClienteAction extends BasicAction {

	private static final long serialVersionUID = -7416561053425601163L;

	private String mensagemUsuario;
	private ClienteVO clienteVO;

	@Autowired
	private ClienteFacade clienteFacade;	
	
	public String iniciarCadastro() {
		try {
			setClienteVO(new ClienteVO());
			getClienteVO().setListaContatos(new ArrayList<ContatoVO>());
			// obter todos os tipos de contatos.
			List<TipoContato> listaTipoContatos = clienteFacade.listaTipoContatos();
			for (TipoContato tipoContato: listaTipoContatos) {
				ContatoVO contatoVO = new ContatoVO();
				contatoVO.setTcoCodigo(tipoContato.getTcoCodigo());
				contatoVO.setTcoDescricao(tipoContato.getTcoDescricao());
				getClienteVO().getListaContatos().add(contatoVO);
			}
			
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	public String iniciarPesquisa() {
		return SUCCESS;
	}	

	public String getMensagemUsuario() {
		return mensagemUsuario;
	}

	public void setMensagemUsuario(String mensagemUsuario) {
		this.mensagemUsuario = mensagemUsuario;
	}

	public ClienteVO getClienteVO() {
		return clienteVO;
	}

	public void setClienteVO(ClienteVO clienteVO) {
		this.clienteVO = clienteVO;
	}
}
