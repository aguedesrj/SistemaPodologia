package br.com.guedes.sistemaPodologia.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.guedes.sistemaPodologia.model.Estado;
import br.com.guedes.sistemaPodologia.model.Pessoa;
import br.com.guedes.sistemaPodologia.model.TipoContato;
import br.com.guedes.sistemaPodologia.util.BusinessException;
import br.com.guedes.sistemaPodologia.vo.ClienteVO;
import br.com.guedes.sistemaPodologia.vo.ContatoVO;
import br.com.guedes.sistemaPodologia.vo.EstadoVO;
import br.com.guedes.sistemaPodologia.vo.PessoaVO;
import br.com.guedes.sistemaPodologia.vo.TipoContatoVO;
import br.com.guedes.sistemaPodologia.vo.TratamentoVO;

@Controller
@Scope("request")
public class TratamentoAction extends BasicAction {

	private static final long serialVersionUID = 4554693973392023096L;
	private static final Logger LOGGER = Logger.getLogger(TratamentoAction.class);
	
	private String mensagemUsuario;
	private TratamentoVO tratamentoVO;
	private List<TratamentoVO> listaTratamentoVO;
	
	public String iniciarCadastro() {
		try {

    		return SUCCESS;
		} catch (Exception e) {
			setMensagemUsuario("Erro ao iniciar o cadastro do Tratamento.");
			return ERROR;
		}
	}
	
	public String salvar() {
		try {

	  		return SUCCESS;
		} catch (Exception e) {
			LOG.fatal(e.getMessage(), e);
			if (e instanceof BusinessException) {
				setMensagemUsuario(e.getMessage());
			} else {
				setMensagemUsuario("Erro ao gravar o Cliente.");
			}
			return ERROR;
		}
	}
	
	public String iniciarPesquisa() {
		setTratamentoVO(new TratamentoVO());
		return SUCCESS;
	}
	
	public String executarPesquisa() {
    	try {

    		return SUCCESS;
		} catch (Exception e) {
			LOGGER.error("Erro ao pesquisar Tratamentos.", e);
			setMensagemUsuario("Erro ao pesquisar Tratamentos.");
			return ERROR;
		}
	}
	
	public String detalhar() {
    	try {

    		return SUCCESS;
		} catch (Exception e) {
			LOGGER.error("Erro ao detalhar Tratamento.", e);
			setMensagemUsuario("Erro ao detalhar Tratamento.");
			return ERROR;
		}
	}
	
	public String iniciarAlteracao() {
    	try {

    		return SUCCESS;
		} catch (Exception e) {
			addActionError("Erro ao iniciar alteração do Tratamento.");
			return ERROR;
		}
	}	
	
	@SuppressWarnings("unchecked")
	public String buscarContatos() {
    	try {

    		return SUCCESS;
		} catch (Exception e) {
			addActionError("Erro ao iniciar alteração do Tratamento.");
			return ERROR;
		}		
	}	
	
	private void populaDeParaSalvar(Pessoa pessoa) throws Exception {
		
	}
	
	private void populaDePara(Pessoa pessoa) throws Exception {
		
	}
	
	public String getMensagemUsuario() {
		return mensagemUsuario;
	}
	
	public void setMensagemUsuario(String mensagemUsuario) {
		this.mensagemUsuario = mensagemUsuario;
	}
	
	public TratamentoVO getTratamentoVO() {
		return tratamentoVO;
	}
	
	public void setTratamentoVO(TratamentoVO tratamentoVO) {
		this.tratamentoVO = tratamentoVO;
	}
	
	public List<TratamentoVO> getListaTratamentoVO() {
		return listaTratamentoVO;
	}
	
	public void setListaTratamentoVO(List<TratamentoVO> listaTratamentoVO) {
		this.listaTratamentoVO = listaTratamentoVO;
	}
}
