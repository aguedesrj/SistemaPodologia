package br.com.guedes.sistemaPodologia.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.guedes.sistemaPodologia.facade.TratamentoFacade;
import br.com.guedes.sistemaPodologia.model.Tratamento;
import br.com.guedes.sistemaPodologia.util.BusinessException;
import br.com.guedes.sistemaPodologia.util.Util;
import br.com.guedes.sistemaPodologia.vo.TratamentoVO;

@Controller
@Scope("request")
public class TratamentoAction extends BasicAction {

	private static final long serialVersionUID = 4554693973392023096L;
	private static final Logger LOGGER = Logger.getLogger(TratamentoAction.class);
	
	@Autowired
	private TratamentoFacade tratamentoFacade;
	
	private String mensagemUsuario;
	private TratamentoVO tratamentoVO;
	private List<TratamentoVO> listaTratamentoVO;
	
	public String iniciarCadastro() {
		try {
			setTratamentoVO(new TratamentoVO());
    		return SUCCESS;
		} catch (Exception e) {
			setMensagemUsuario("Erro ao iniciar o cadastro do Tratamento.");
			return ERROR;
		}
	}
	
	public String salvar() {
		try {
			Tratamento tratamento = new Tratamento();
			tratamento.setTraCodigo(getTratamentoVO().getTraCodigo());
			if (tratamento.getTraCodigo() != null && tratamento.getTraCodigo() > 0) {
				tratamento = tratamentoFacade.obterPorId(tratamento);
			}
			// de-para
			populaDeParaSalvar(tratamento);
	  		// salvar.
			tratamentoFacade.salvar(tratamento);
	  		return SUCCESS;
		} catch (Exception e) {
			LOG.fatal(e.getMessage(), e);
			if (e instanceof BusinessException) {
				setMensagemUsuario(e.getMessage());
			} else {
				setMensagemUsuario("Erro ao gravar o Tratamento.");
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
    		Tratamento tratamentoParam = new Tratamento();
    		tratamentoParam.setTraDescricao(getTratamentoVO().getTraDescricao());
    		List<Tratamento> listaTratamento = tratamentoFacade.pesquisarPorCriterios(tratamentoParam);
    		if (listaTratamento == null || listaTratamento.isEmpty()) {
    			setMensagemUsuario("Tratamento não encontrado.");
    			return ERROR;
    		} else {
    			setListaTratamentoVO(new ArrayList<TratamentoVO>());
    			for (Tratamento tratamento: listaTratamento) {
    				TratamentoVO tratamentoVO = new TratamentoVO();
    				tratamentoVO.setTraCodigo(tratamento.getTraCodigo());
    				tratamentoVO.setTraDescricao(tratamento.getTraDescricao());
    				getListaTratamentoVO().add(tratamentoVO);
    			}
    		}
    		return SUCCESS;
		} catch (Exception e) {
			LOGGER.error("Erro ao pesquisar Tratamentos.", e);
			setMensagemUsuario("Erro ao pesquisar Tratamentos.");
			return ERROR;
		}
	}
	
	public String detalhar() {
    	try {
    		Tratamento tratamento = new Tratamento();
    		tratamento.setTraCodigo(getTratamentoVO().getTraCodigo());
    		tratamento.setTraDescricao(null);
    		tratamento = tratamentoFacade.obterPorId(tratamento);
    		if (tratamento.getTraDescricao() == null) {
    			setMensagemUsuario("Tratamento não encontrado.");
    			return ERROR;
    		}
			// seta dados do Tratamento.
			populaDePara(tratamento);
    		return SUCCESS;
		} catch (Exception e) {
			LOGGER.error("Erro ao detalhar Tratamento.", e);
			setMensagemUsuario("Erro ao detalhar Tratamento.");
			return ERROR;
		}
	}
	
	public String iniciarAlteracao() {
    	try {
    		Tratamento tratamento = new Tratamento();
    		tratamento.setTraCodigo(getTratamentoVO().getTraCodigo());
    		tratamento.setTraDescricao(null);
    		tratamento = tratamentoFacade.obterPorId(tratamento);
    		if (tratamento == null) {
    			addActionError("Tratamento não encontrado.");
    			return ERROR;
    		}
			// seta dados do Tratamento.
			populaDePara(tratamento);
    		return SUCCESS;
		} catch (Exception e) {
			LOGGER.error("Erro ao alterar Tratamento.", e);
			addActionError("Erro ao iniciar alteração do Tratamento.");
			return ERROR;
		}
	}	
	
	/**
	 * 
	 * @param tratamento Tratamento
	 * @throws Exception
	 */
	private void populaDeParaSalvar(Tratamento tratamento) throws Exception {
		tratamento.setTraDescricao(getTratamentoVO().getTraDescricao());
		tratamento.setTraFlag("N");
		if (getTratamentoVO().isTraFlag()) {
			tratamento.setTraFlag("S");
		}
		tratamento.setTraObs(getTratamentoVO().getTraObs());
		tratamento.setTraPreco(Util.converterDecimalStringParaBigDecimal(getTratamentoVO().getTraPreco()));
		tratamento.setTraTempo(getTratamentoVO().getTraTempo());
	}
	
	/**
	 * 
	 * @param tratamento Tratamento
	 * @throws Exception
	 */
	private void populaDePara(Tratamento tratamento) throws Exception {
		getTratamentoVO().setTraDescricao(tratamento.getTraDescricao());
		if (tratamento.getTraFlag() != null && tratamento.getTraFlag().equals("S")) {
			getTratamentoVO().setTraFlag(true);
		}
		getTratamentoVO().setTraObs(tratamento.getTraObs());
		getTratamentoVO().setTraPreco(Util.converterBigDecimalParaStringDecimal(tratamento.getTraPreco()));
		getTratamentoVO().setTraTempo(tratamento.getTraTempo());
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
