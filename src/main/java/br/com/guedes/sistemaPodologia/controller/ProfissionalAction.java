package br.com.guedes.sistemaPodologia.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.guedes.sistemaPodologia.facade.ProfissionalFacade;
import br.com.guedes.sistemaPodologia.model.Contato;
import br.com.guedes.sistemaPodologia.model.Endereco;
import br.com.guedes.sistemaPodologia.model.Estado;
import br.com.guedes.sistemaPodologia.model.Pessoa;
import br.com.guedes.sistemaPodologia.model.Profissional;
import br.com.guedes.sistemaPodologia.model.TipoContato;
import br.com.guedes.sistemaPodologia.util.BusinessException;
import br.com.guedes.sistemaPodologia.util.Util;
import br.com.guedes.sistemaPodologia.vo.ContatoVO;
import br.com.guedes.sistemaPodologia.vo.EstadoVO;
import br.com.guedes.sistemaPodologia.vo.ProfissionalVO;
import br.com.guedes.sistemaPodologia.vo.TipoContatoVO;

@Controller
@Scope("request")
public class ProfissionalAction extends BasicAction {

	private static final long serialVersionUID = -2569049796065036014L;
	private static final Logger LOGGER = Logger.getLogger(ProfissionalAction.class);
	private static final String SESSION_LISTA_CONTATOS = "SESSION_LISTA_CONTATOS";

	@Autowired
	private ProfissionalFacade profissionalFacade;
	
	private String mensagemUsuario;
	private ProfissionalVO profissionalVO;
	private List<ProfissionalVO> listaProfissionalVO;

	public String iniciarCadastro() {
		try {
			setProfissionalVO(new ProfissionalVO());
    		// remove da session a lista de contatos.
    		this.getRequest().getSession().removeAttribute(SESSION_LISTA_CONTATOS);
    		// lista de tipos de contatos.
    		List<TipoContato> listaTipoContatos = profissionalFacade.listaTipoContatos();
    		for (TipoContato tipoContato: listaTipoContatos) {
    			TipoContatoVO tipoContatoVO = new TipoContatoVO();
    			tipoContatoVO.setTcoCodigo(tipoContato.getTcoCodigo());
    			tipoContatoVO.setTcoDescricao(tipoContato.getTcoDescricao());
    			getProfissionalVO().getContatoVO().getListaTipoContatosVO().add(tipoContatoVO);
    		}
    		// lista de estados.
    		List<Estado> listaEstados = profissionalFacade.listaEstados();
    		for (Estado estado: listaEstados) {
    			EstadoVO estadoVO = new EstadoVO();
    			estadoVO.setEstCodigo(estado.getEstCodigo());
    			estadoVO.setEstNome(estado.getEstNome());
    			estadoVO.setEstSigla(estado.getEstSigla());
    			getProfissionalVO().getEnderecoVO().getListaEstadosVO().add(estadoVO);
    		}
    		return SUCCESS;
		} catch (Exception e) {
			setMensagemUsuario("Erro ao iniciar o cadastro do Profissional.");
			return ERROR;
		}
	}
	
	@SuppressWarnings("unchecked")
	public String salvar() {
		try {
			Profissional profissional = new Profissional();
			profissional.setPrfCodigo(getProfissionalVO().getPrfCodigo());
			if (profissional.getPrfCodigo() != null && profissional.getPrfCodigo() > 0) {
				profissional = profissionalFacade.obterPorId(profissional);
			} else {
				profissional.setPessoa(new Pessoa());
				profissional.getPessoa().setPesDtCadastro(Calendar.getInstance());
			}
			// lista de contatos da sessão
			getProfissionalVO().setListaContatos((List<ContatoVO>) this.getRequest().getSession().getAttribute(SESSION_LISTA_CONTATOS));
			// de-para
			populaDeParaSalvar(profissional);
	  		// salvar.
			profissionalFacade.salvar(profissional);
	  		// remove da session a lista.
	  		this.getRequest().getSession().removeAttribute(SESSION_LISTA_CONTATOS);
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
	
	@SuppressWarnings("unchecked")
	public String inserirContato() {
		try {
			getProfissionalVO().setListaContatos((List<ContatoVO>) this.getRequest().getSession().getAttribute(SESSION_LISTA_CONTATOS));
	  		if (getProfissionalVO().getListaContatos() == null) {
	  			getProfissionalVO().setListaContatos(new ArrayList<ContatoVO>());
	  		}
	  		// verifica se o contato ? novo
	  		if (getProfissionalVO().getContatoVO().getConCodigo() == null && !getProfissionalVO().getContatoVO().isNovo()) {
	  			getProfissionalVO().getContatoVO().setNovo(true);
	  			getProfissionalVO().getContatoVO().setConCodigo(new Random().nextInt());
	  			getProfissionalVO().getListaContatos().add(getProfissionalVO().getContatoVO());
	  		} else {
	  			// est? alterando.
	  			for (ContatoVO contatoVO: getProfissionalVO().getListaContatos()) {
	  				if (contatoVO.getConCodigo().intValue() == getProfissionalVO().getContatoVO().getConCodigo().intValue()) {
	  					contatoVO.getTipoContatoVO().setTcoCodigo(getProfissionalVO().getContatoVO().getTipoContatoVO().getTcoCodigo());
	  					contatoVO.getTipoContatoVO().setTcoDescricao(getProfissionalVO().getContatoVO().getTipoContatoVO().getTcoDescricao());
	  					contatoVO.setConDescricao(getProfissionalVO().getContatoVO().getConDescricao());
	  					contatoVO.setConResponsavel(getProfissionalVO().getContatoVO().getConResponsavel());
	  					break;
	  				}
	  			}
	  		}
	  		this.getRequest().getSession().setAttribute(SESSION_LISTA_CONTATOS, getProfissionalVO().getListaContatos());
	  		return SUCCESS;
		} catch (Exception e) {
			LOGGER.error("Erro ao salvar contato.", e);
			setMensagemUsuario("Erro ao salvar contato.");
			return ERROR;
		}
	}
	
	@SuppressWarnings("unchecked")
	public String alterarContato() {
		try {
	  		getProfissionalVO().setListaContatos((List<ContatoVO>) this.getRequest().getSession().getAttribute(SESSION_LISTA_CONTATOS));
	  		for (ContatoVO contatoVO: getProfissionalVO().getListaContatos()) {
	  			if (contatoVO.getConCodigo().intValue() == getProfissionalVO().getContatoVO().getConCodigo().intValue()) {
	  				getProfissionalVO().setContatoVO(contatoVO);
	  				break;
	  			}
	  		}
	  		return SUCCESS;
		} catch (Exception e) {
			LOGGER.error("Erro ao alterar contato.", e);
			setMensagemUsuario("Erro ao alterar contato.");
			return ERROR;
		}		
	}
	
	@SuppressWarnings("unchecked")
	public String excluirContato() {
		try {
	  		getProfissionalVO().setListaContatos((List<ContatoVO>) this.getRequest().getSession().getAttribute(SESSION_LISTA_CONTATOS));
	  		for (ContatoVO contatoVO: getProfissionalVO().getListaContatos()) {
	  			if (contatoVO.getConCodigo().intValue() == getProfissionalVO().getContatoVO().getConCodigo().intValue()) {
	  				getProfissionalVO().getListaContatos().remove(contatoVO);
	  				break;
	  			}
	  		}
	  		this.getRequest().getSession().setAttribute(SESSION_LISTA_CONTATOS, getProfissionalVO().getListaContatos());
	  		return SUCCESS;
		} catch (Exception e) {
			LOGGER.error("Erro ao excluir contato.", e);
			setMensagemUsuario("Erro ao excluir contato.");
			return ERROR;
		}		
	}
	
	public String iniciarPesquisa() {
		setProfissionalVO(new ProfissionalVO());
		return SUCCESS;
	}
	
	public String executarPesquisa() {
    	try {
    		List<Profissional> listaProfissional = profissionalFacade.pesquisar(new Profissional());
    		if (listaProfissional == null || listaProfissional.isEmpty()) {
    			setMensagemUsuario("Nenhum Profissional encontrado.");
    			return ERROR;
    		} else {
    			setListaProfissionalVO(new ArrayList<ProfissionalVO>());
    			for (Profissional profissional: listaProfissional) {
    				ProfissionalVO profissionalVO = new ProfissionalVO();
    				profissionalVO.setPrfCodigo(profissional.getPrfCodigo());
    				profissionalVO.getPessoaVO().setPesCodigo(profissional.getPessoa().getPesCodigo());
    				profissionalVO.getPessoaVO().setPesNome(profissional.getPessoa().getPesNome());
    				getListaProfissionalVO().add(profissionalVO);
    			}
    		}
    		return SUCCESS;
		} catch (Exception e) {
			LOGGER.error("Erro ao pesquisar Profissionais.", e);
			setMensagemUsuario("Erro ao pesquisar Profissionais.");
			return ERROR;
		}
	}
	
	public String detalhar() {
    	try {
    		Profissional profissional = new Profissional();
    		profissional.setPrfCodigo(getProfissionalVO().getPrfCodigo());
    		profissional = profissionalFacade.obterPorId(profissional);
    		if (profissional.getPessoa().getPesNome() == null) {
    			setMensagemUsuario("Profissional não encontrado.");
    			return ERROR;
    		} else {
    			// seta dados do Profissional.
    			populaDePara(profissional);
    		}
    		return SUCCESS;
		} catch (Exception e) {
			LOGGER.error("Erro ao detalhar Profissional.", e);
			setMensagemUsuario("Erro ao detalhar Profissional.");
			return ERROR;
		}
	}
	
	public String iniciarAlteracao() {
    	try {
    		// lista de tipos de contatos.
    		List<TipoContato> listaTipoContatos = profissionalFacade.listaTipoContatos();
    		for (TipoContato tipoContato: listaTipoContatos) {
    			TipoContatoVO tipoContatoVO = new TipoContatoVO();
    			tipoContatoVO.setTcoCodigo(tipoContato.getTcoCodigo());
    			tipoContatoVO.setTcoDescricao(tipoContato.getTcoDescricao());
    			getProfissionalVO().getContatoVO().getListaTipoContatosVO().add(tipoContatoVO);
    		}
    		// lista de estados.
    		List<Estado> listaEstados = profissionalFacade.listaEstados();
    		for (Estado estado: listaEstados) {
    			EstadoVO estadoVO = new EstadoVO();
    			estadoVO.setEstCodigo(estado.getEstCodigo());
    			estadoVO.setEstNome(estado.getEstNome());
    			estadoVO.setEstSigla(estado.getEstSigla());
    			getProfissionalVO().getEnderecoVO().getListaEstadosVO().add(estadoVO);
    		}    		
    		// remove da session a lista.
    		this.getRequest().getSession().removeAttribute(SESSION_LISTA_CONTATOS);
    		Profissional profissional = new Profissional();
    		profissional.setPessoa(new Pessoa());
    		profissional.getPessoa().setPesCodigo(getProfissionalVO().getPessoaVO().getPesCodigo());
    		profissional = profissionalFacade.obterPorId(profissional);
    		if (profissional == null) {
    			addActionError("Profissional não encontrado.");
    			return ERROR;
    		}
    		populaDePara(profissional);
    		this.getRequest().getSession().setAttribute(SESSION_LISTA_CONTATOS, getProfissionalVO().getListaContatos());
    		return SUCCESS;
		} catch (Exception e) {
			addActionError("Erro ao iniciar alteração do Profissional.");
			return ERROR;
		}
	}
	
	@SuppressWarnings("unchecked")
	public String buscarContatos() {
    	try {
    		setProfissionalVO(new ProfissionalVO());
    		getProfissionalVO().setListaContatos((List<ContatoVO>) this.getRequest().getSession().getAttribute(SESSION_LISTA_CONTATOS));
    		return SUCCESS;
		} catch (Exception e) {
			addActionError("Erro ao buscar contatos do Profissional.");
			return ERROR;
		}		
	}
	
	/**
	 * 
	 * @param pessoa Pessoa
	 * @throws Exception
	 */
	private void populaDeParaSalvar(Profissional profissional) throws Exception {
		// profissional
		profissional.setPrfDescricaoFormacao(getProfissionalVO().getPrfDescricaoFormacao());
		try {
			profissional.setPrfDtFormacao(Util.converterStringParaDate(getProfissionalVO().getPrfDtFormacao()));
		} catch (Exception e) {
			LOGGER.fatal("Data de formação inválida.", e);
			throw new BusinessException("Data de formação inválida.");
		}
		// pessoa
		profissional.setPessoa(new Pessoa());
		profissional.getPessoa().setPesNome(getProfissionalVO().getPessoaVO().getPesNome());
		try {
			profissional.getPessoa().setPesDtNascimento(Util.converterStringParaCalendar(getProfissionalVO().getPessoaVO().getPesDtNascimento(), Util.SIMPLE_DATE_FORMAT_DATA));
		} catch (Exception e) {
			LOGGER.fatal("Data de nascimento inválida.", e);
			throw new BusinessException("Data de nascimento inválida.");
		}
		profissional.getPessoa().setPesObs(getProfissionalVO().getPessoaVO().getPesObs());
		profissional.getPessoa().setPesSexo(getProfissionalVO().getPessoaVO().getPesSexo());
		profissional.getPessoa().setPesCPF(getProfissionalVO().getPessoaVO().getPesCpf());
		// endere?o
		if (profissional.getPessoa().getEndereco() == null) {
			profissional.getPessoa().setEndereco(new Endereco());
		}
		profissional.getPessoa().getEndereco().setEndBairro(getProfissionalVO().getEnderecoVO().getEndBairro());
		profissional.getPessoa().getEndereco().setEndCep(getProfissionalVO().getEnderecoVO().getEndCep());
		profissional.getPessoa().getEndereco().setEndCidade(getProfissionalVO().getEnderecoVO().getEndCidade());
		profissional.getPessoa().getEndereco().setEndCodigo(getProfissionalVO().getEnderecoVO().getEndCodigo());
		profissional.getPessoa().getEndereco().setEndLogadouro(getProfissionalVO().getEnderecoVO().getEndLogadouro());
		profissional.getPessoa().getEndereco().setEndNumero(getProfissionalVO().getEnderecoVO().getEndNumero());
		if (getProfissionalVO().getEnderecoVO().getEstadoVO().getEstCodigo() != -1) {
			profissional.getPessoa().getEndereco().setEstado(new Estado());
			profissional.getPessoa().getEndereco().getEstado().setEstCodigo(getProfissionalVO().getEnderecoVO().getEstadoVO().getEstCodigo());			
		}
		// lista de contatos
		if (getProfissionalVO().getListaContatos() != null) {
			profissional.getPessoa().setListaContato(new HashSet<Contato>());
			// insere novos contatos.
			for (ContatoVO contatoVO: getProfissionalVO().getListaContatos()) {
				Contato contato = new Contato();
				if (contatoVO.isNovo()) {
					contato.setConCodigo(null);
				} else {
					contato.setConCodigo(contatoVO.getConCodigo());
				}
				contato.setConDescricao(contatoVO.getConDescricao());
				contato.setConResponsavel(contatoVO.getConResponsavel());
				if (contatoVO.getTipoContatoVO().getTcoCodigo() == -1) {
					throw new BusinessException("Tipo de contato deve ser informado.");
				}
				contato.setTipoContato(new TipoContato());
				contato.getTipoContato().setTcoCodigo(contatoVO.getTipoContatoVO().getTcoCodigo());
				contato.setPessoa(profissional.getPessoa());
				profissional.getPessoa().getListaContato().add(contato);
			}
		}
	}
	
	private void populaDePara(Profissional profissional) throws Exception {
		// profissional
		getProfissionalVO().setPrfDescricaoFormacao(profissional.getPrfDescricaoFormacao());
		getProfissionalVO().setPrfDtFormacao(Util.converterDateParaString(profissional.getPrfDtFormacao()));
		// pessoa
		getProfissionalVO().getPessoaVO().setPesCodigo(profissional.getPessoa().getPesCodigo());
		getProfissionalVO().getPessoaVO().setPesNome(profissional.getPessoa().getPesNome());
		getProfissionalVO().getPessoaVO().setPesDtNascimento(Util.converterCalendarParaString(profissional.getPessoa().getPesDtNascimento(), Util.SIMPLE_DATE_FORMAT_DATA));
		getProfissionalVO().getPessoaVO().setPesObs(profissional.getPessoa().getPesObs());
		getProfissionalVO().getPessoaVO().setPesSexo(profissional.getPessoa().getPesSexo());
		getProfissionalVO().getPessoaVO().setPesCpf(profissional.getPessoa().getPesCPF());
		// endereço
		getProfissionalVO().getEnderecoVO().setEndBairro(profissional.getPessoa().getEndereco().getEndBairro());
		getProfissionalVO().getEnderecoVO().setEndCep(profissional.getPessoa().getEndereco().getEndCep());
		getProfissionalVO().getEnderecoVO().setEndCidade(profissional.getPessoa().getEndereco().getEndCidade());
		getProfissionalVO().getEnderecoVO().setEndCodigo(profissional.getPessoa().getEndereco().getEndCodigo());
		getProfissionalVO().getEnderecoVO().setEndLogadouro(profissional.getPessoa().getEndereco().getEndLogadouro());
		getProfissionalVO().getEnderecoVO().setEndNumero(profissional.getPessoa().getEndereco().getEndNumero());
		if (profissional.getPessoa().getEndereco().getEstado() != null) {
			getProfissionalVO().getEnderecoVO().getEstadoVO().setEstCodigo(profissional.getPessoa().getEndereco().getEstado().getEstCodigo());
			getProfissionalVO().getEnderecoVO().getEstadoVO().setEstNome(profissional.getPessoa().getEndereco().getEstado().getEstNome());
		}
		// lista de contatos
		getProfissionalVO().setListaContatos(new ArrayList<ContatoVO>());
		for (Contato contato: profissional.getPessoa().getListaContato()) {
			ContatoVO contatoVO = new ContatoVO();
			contatoVO.setNovo(false);
			contatoVO.setConCodigo(contato.getConCodigo());
			contatoVO.setConDescricao(contato.getConDescricao());
			contatoVO.setConResponsavel(contato.getConResponsavel());
			contatoVO.getTipoContatoVO().setTcoCodigo(contato.getTipoContato().getTcoCodigo());
			contatoVO.getTipoContatoVO().setTcoDescricao(contato.getTipoContato().getTcoDescricao());
			getProfissionalVO().getListaContatos().add(contatoVO);
		}
	}	

	public String getMensagemUsuario() {
		return mensagemUsuario;
	}

	public void setMensagemUsuario(String mensagemUsuario) {
		this.mensagemUsuario = mensagemUsuario;
	}

	public ProfissionalVO getProfissionalVO() {
		return profissionalVO;
	}

	public void setProfissionalVO(ProfissionalVO profissionalVO) {
		this.profissionalVO = profissionalVO;
	}

	public List<ProfissionalVO> getListaProfissionalVO() {
		return listaProfissionalVO;
	}

	public void setListaProfissionalVO(List<ProfissionalVO> listaProfissionalVO) {
		this.listaProfissionalVO = listaProfissionalVO;
	}
}
