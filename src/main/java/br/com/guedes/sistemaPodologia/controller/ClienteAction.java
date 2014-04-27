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

import br.com.guedes.sistemaPodologia.facade.ClienteFacade;
import br.com.guedes.sistemaPodologia.model.Contato;
import br.com.guedes.sistemaPodologia.model.Endereco;
import br.com.guedes.sistemaPodologia.model.Estado;
import br.com.guedes.sistemaPodologia.model.Paciente;
import br.com.guedes.sistemaPodologia.model.Pessoa;
import br.com.guedes.sistemaPodologia.model.TipoContato;
import br.com.guedes.sistemaPodologia.util.BusinessException;
import br.com.guedes.sistemaPodologia.util.Util;
import br.com.guedes.sistemaPodologia.vo.ClienteVO;
import br.com.guedes.sistemaPodologia.vo.ContatoVO;
import br.com.guedes.sistemaPodologia.vo.EstadoVO;
import br.com.guedes.sistemaPodologia.vo.PessoaVO;
import br.com.guedes.sistemaPodologia.vo.TipoContatoVO;

@Controller
@Scope("request")
public class ClienteAction extends BasicAction {

	private static final long serialVersionUID = -7416561053425601163L;
	private static final Logger LOGGER = Logger.getLogger(ClienteAction.class);
	private static final String SESSION_LISTA_CONTATOS = "SESSION_LISTA_CONTATOS";

	@Autowired
	private ClienteFacade clienteFacade;
	
	private String mensagemUsuario;
	private ClienteVO clienteVO;
	private List<ClienteVO> listaClienteVO;
	private List<PessoaVO> listaPessoaVO;

	public String iniciarCadastro() {
		try {
			setClienteVO(new ClienteVO());
    		// remove da session a lista de contatos.
    		this.getRequest().getSession().removeAttribute(SESSION_LISTA_CONTATOS);
    		// lista de tipos de contatos.
    		List<TipoContato> listaTipoContatos = clienteFacade.listaTipoContatos();
    		for (TipoContato tipoContato: listaTipoContatos) {
    			TipoContatoVO tipoContatoVO = new TipoContatoVO();
    			tipoContatoVO.setTcoCodigo(tipoContato.getTcoCodigo());
    			tipoContatoVO.setTcoDescricao(tipoContato.getTcoDescricao());
    			getClienteVO().getContatoVO().getListaTipoContatosVO().add(tipoContatoVO);
    		}
    		// lista de estados.
    		List<Estado> listaEstados = clienteFacade.listaEstados();
    		for (Estado estado: listaEstados) {
    			EstadoVO estadoVO = new EstadoVO();
    			estadoVO.setEstCodigo(estado.getEstCodigo());
    			estadoVO.setEstNome(estado.getEstNome());
    			estadoVO.setEstSigla(estado.getEstSigla());
    			getClienteVO().getEnderecoVO().getListaEstadosVO().add(estadoVO);
    		}
    		return SUCCESS;
		} catch (Exception e) {
			setMensagemUsuario("Erro ao iniciar o cadastro do Cliente.");
			return ERROR;
		}
	}
	
	@SuppressWarnings("unchecked")
	public String salvar() {
		try {
			Pessoa pessoa = new Pessoa();
			pessoa.setPesCodigo(getClienteVO().getPessoaVO().getPesCodigo());
			if (pessoa.getPesCodigo() != null && pessoa.getPesCodigo() > 0) {
				pessoa = clienteFacade.obterPorId(pessoa);
			} else {
				pessoa.setPesDtCadastro(Calendar.getInstance());
			}
			// lista de contatos da sessão
			getClienteVO().setListaContatos((List<ContatoVO>) this.getRequest().getSession().getAttribute(SESSION_LISTA_CONTATOS));
			// de-para
			populaDeParaSalvar(pessoa);
	  		// salvar.
	  		clienteFacade.salvar(pessoa);
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
	  		getClienteVO().setListaContatos((List<ContatoVO>) this.getRequest().getSession().getAttribute(SESSION_LISTA_CONTATOS));
	  		if (getClienteVO().getListaContatos() == null) {
	  			getClienteVO().setListaContatos(new ArrayList<ContatoVO>());
	  		}
	  		// verifica se o contato é novo
	  		if (getClienteVO().getContatoVO().getConCodigo() == null && !getClienteVO().getContatoVO().isNovo()) {
	  			getClienteVO().getContatoVO().setNovo(true);
	  			getClienteVO().getContatoVO().setConCodigo(new Random().nextInt());
	  			getClienteVO().getListaContatos().add(getClienteVO().getContatoVO());
	  		} else {
	  			// está alterando.
	  			for (ContatoVO contatoVO: getClienteVO().getListaContatos()) {
	  				if (contatoVO.getConCodigo().intValue() == getClienteVO().getContatoVO().getConCodigo().intValue()) {
	  					contatoVO.getTipoContatoVO().setTcoCodigo(getClienteVO().getContatoVO().getTipoContatoVO().getTcoCodigo());
	  					contatoVO.getTipoContatoVO().setTcoDescricao(getClienteVO().getContatoVO().getTipoContatoVO().getTcoDescricao());
	  					contatoVO.setConDescricao(getClienteVO().getContatoVO().getConDescricao());
	  					contatoVO.setConResponsavel(getClienteVO().getContatoVO().getConResponsavel());
	  					break;
	  				}
	  			}
	  		}
	  		this.getRequest().getSession().setAttribute(SESSION_LISTA_CONTATOS, getClienteVO().getListaContatos());
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
	  		getClienteVO().setListaContatos((List<ContatoVO>) this.getRequest().getSession().getAttribute(SESSION_LISTA_CONTATOS));
	  		for (ContatoVO contatoVO: getClienteVO().getListaContatos()) {
	  			if (contatoVO.getConCodigo().intValue() == getClienteVO().getContatoVO().getConCodigo().intValue()) {
	  				getClienteVO().setContatoVO(contatoVO);
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
	  		getClienteVO().setListaContatos((List<ContatoVO>) this.getRequest().getSession().getAttribute(SESSION_LISTA_CONTATOS));
	  		for (ContatoVO contatoVO: getClienteVO().getListaContatos()) {
	  			if (contatoVO.getConCodigo().intValue() == getClienteVO().getContatoVO().getConCodigo().intValue()) {
	  				getClienteVO().getListaContatos().remove(contatoVO);
	  				break;
	  			}
	  		}
	  		this.getRequest().getSession().setAttribute(SESSION_LISTA_CONTATOS, getClienteVO().getListaContatos());
	  		return SUCCESS;
		} catch (Exception e) {
			LOGGER.error("Erro ao excluir contato.", e);
			setMensagemUsuario("Erro ao excluir contato.");
			return ERROR;
		}		
	}
	
	public String iniciarPesquisa() {
		setClienteVO(new ClienteVO());
		return SUCCESS;
	}
	
	public String executarPesquisa() {
    	try {
    		Pessoa pessoaParam = new Pessoa();
    		pessoaParam.setPesNome(getClienteVO().getPessoaVO().getPesNome());
    		List<Pessoa> listaPessoa = clienteFacade.pesquisarPorCriterios(pessoaParam);
    		if (listaPessoa == null || listaPessoa.isEmpty()) {
    			setMensagemUsuario("Cliente não encontrado.");
    			return ERROR;
    		} else {
    			setListaPessoaVO(new ArrayList<PessoaVO>());
    			for (Pessoa pessoa: listaPessoa) {
    				PessoaVO pessoaVO = new PessoaVO();
    				pessoaVO.setPesCodigo(pessoa.getPesCodigo());
    				pessoaVO.setPesNome(pessoa.getPesNome());
    				getListaPessoaVO().add(pessoaVO);
    			}
    		}
    		return SUCCESS;
		} catch (Exception e) {
			LOGGER.error("Erro ao pesquisar Clientes.", e);
			setMensagemUsuario("Erro ao pesquisar Clientes.");
			return ERROR;
		}
	}
	
	public String detalhar() {
    	try {
			Pessoa pessoa = new Pessoa();
			pessoa.setPesCodigo(getClienteVO().getPessoaVO().getPesCodigo());
    		pessoa = clienteFacade.obterPorId(pessoa);
    		if (pessoa.getPesNome() == null) {
    			setMensagemUsuario("Cliente não encontrado.");
    			return ERROR;
    		} else {
    			// seta dados do Produto.
    			populaDePara(pessoa);
    		}
    		return SUCCESS;
		} catch (Exception e) {
			LOGGER.error("Erro ao detalhar Cliente.", e);
			setMensagemUsuario("Erro ao detalhar Cliente.");
			return ERROR;
		}
	}
	
	public String iniciarAlteracao() {
    	try {
    		// lista de tipos de contatos.
    		List<TipoContato> listaTipoContatos = clienteFacade.listaTipoContatos();
    		for (TipoContato tipoContato: listaTipoContatos) {
    			TipoContatoVO tipoContatoVO = new TipoContatoVO();
    			tipoContatoVO.setTcoCodigo(tipoContato.getTcoCodigo());
    			tipoContatoVO.setTcoDescricao(tipoContato.getTcoDescricao());
    			getClienteVO().getContatoVO().getListaTipoContatosVO().add(tipoContatoVO);
    		}
    		// lista de estados.
    		List<Estado> listaEstados = clienteFacade.listaEstados();
    		for (Estado estado: listaEstados) {
    			EstadoVO estadoVO = new EstadoVO();
    			estadoVO.setEstCodigo(estado.getEstCodigo());
    			estadoVO.setEstNome(estado.getEstNome());
    			estadoVO.setEstSigla(estado.getEstSigla());
    			getClienteVO().getEnderecoVO().getListaEstadosVO().add(estadoVO);
    		}    		
    		// remove da session a lista.
    		this.getRequest().getSession().removeAttribute(SESSION_LISTA_CONTATOS);
    		Pessoa pessoa = new Pessoa();
    		pessoa.setPesCodigo(getClienteVO().getPessoaVO().getPesCodigo());
    		pessoa = clienteFacade.obterPorId(pessoa);
    		if (pessoa == null) {
    			addActionError("Cliente não encontrado.");
    			return ERROR;
    		}
    		populaDePara(pessoa);
    		this.getRequest().getSession().setAttribute(SESSION_LISTA_CONTATOS, getClienteVO().getListaContatos());
    		return SUCCESS;
		} catch (Exception e) {
			addActionError("Erro ao iniciar alteração do Cliente.");
			return ERROR;
		}
	}
	
	@SuppressWarnings("unchecked")
	public String buscarContatos() {
    	try {
    		setClienteVO(new ClienteVO());
    		getClienteVO().setListaContatos((List<ContatoVO>) this.getRequest().getSession().getAttribute(SESSION_LISTA_CONTATOS));
    		return SUCCESS;
		} catch (Exception e) {
			addActionError("Erro ao iniciar alteração do Cliente.");
			return ERROR;
		}		
	}
	
	/**
	 * 
	 * @param pessoa Pessoa
	 * @throws Exception
	 */
	private void populaDeParaSalvar(Pessoa pessoa) throws Exception {
		// pessoa
		pessoa.setPesNome(getClienteVO().getPessoaVO().getPesNome());
		try {
			pessoa.setPesDtNascimento(Util.converterStringParaCalendar(getClienteVO().getPessoaVO().getPesDtNascimento(), Util.SIMPLE_DATE_FORMAT_DATA));
		} catch (Exception e) {
			throw new BusinessException("Data de nascimento inválida.");
		}
		pessoa.setPesObs(getClienteVO().getPessoaVO().getPesObs());
		pessoa.setPesSexo(getClienteVO().getPessoaVO().getPesSexo());
		pessoa.setPesCPF(getClienteVO().getPessoaVO().getPesCpf());
		// endereço
		if (pessoa.getEndereco() == null) {
			pessoa.setEndereco(new Endereco());
		}
		pessoa.getEndereco().setEndBairro(getClienteVO().getEnderecoVO().getEndBairro());
		pessoa.getEndereco().setEndCep(getClienteVO().getEnderecoVO().getEndCep());
		pessoa.getEndereco().setEndCidade(getClienteVO().getEnderecoVO().getEndCidade());
		pessoa.getEndereco().setEndCodigo(getClienteVO().getEnderecoVO().getEndCodigo());
		pessoa.getEndereco().setEndLogadouro(getClienteVO().getEnderecoVO().getEndLogadouro());
		pessoa.getEndereco().setEndNumero(getClienteVO().getEnderecoVO().getEndNumero());
		if (getClienteVO().getEnderecoVO().getEstadoVO().getEstCodigo() != -1) {
			pessoa.getEndereco().setEstado(new Estado());
			pessoa.getEndereco().getEstado().setEstCodigo(getClienteVO().getEnderecoVO().getEstadoVO().getEstCodigo());			
		}
		// lista de contatos
		if (getClienteVO().getListaContatos() != null) {
			pessoa.setListaContato(new HashSet<Contato>());
			// insere novos contatos.
			for (ContatoVO contatoVO: getClienteVO().getListaContatos()) {
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
				contato.setPessoa(pessoa);
				pessoa.getListaContato().add(contato);
			}
		}
		// paciente
		if (pessoa.getPaciente() == null) {
			pessoa.setPaciente(new Paciente());
		}
		pessoa.getPaciente().setPacAlergicoMedicamentos("N");
		if (getClienteVO().isPacAlergicoMedicamentos()) {
			pessoa.getPaciente().setPacAlergicoMedicamentos("S");
		}
		pessoa.getPaciente().setPacAlergicoQuais(getClienteVO().getPacAlergicoQuais());
		pessoa.getPaciente().setPacAltura(getClienteVO().getPacAltura());
		pessoa.getPaciente().setPacAndaDescalco("N");
		if (getClienteVO().isPacAndaDescalco()) {
			pessoa.getPaciente().setPacAndaDescalco("S");
		}
		pessoa.getPaciente().setPacCalcadoUtiliza(getClienteVO().getPacCalcadoUtiliza());
		pessoa.getPaciente().setPacCirurgiaMotivo(getClienteVO().getPacCirurgiaMotivo());
		pessoa.getPaciente().setPacCirurgiaPes("N");
		if (getClienteVO().isPacCirurgiaPes()) {
			pessoa.getPaciente().setPacCirurgiaPes("S");
		}
		pessoa.getPaciente().setPacCodigo(getClienteVO().getPacCodigo());
		pessoa.getPaciente().setPacDiabetes("N");
		if (getClienteVO().isPacDiabetes()) {
			pessoa.getPaciente().setPacDiabetes("S");
		}
		pessoa.getPaciente().setPacHipertensao("N");
		if (getClienteVO().isPacHipertensao()) {
			pessoa.getPaciente().setPacHipertensao("S");
		}
		pessoa.getPaciente().setPacLabora("N");
		if (getClienteVO().isPacLabora()) {
			pessoa.getPaciente().setPacLabora("S");
		}
		pessoa.getPaciente().setPacNumeroCalcado(getClienteVO().getPacNumeroCalcado());
		pessoa.getPaciente().setPacPeso(getClienteVO().getPacPeso());
		pessoa.getPaciente().setPacTabagismo("N");
		if (getClienteVO().isPacTabagismo()) {
			pessoa.getPaciente().setPacTabagismo("S");
		}
		pessoa.getPaciente().setPacUnhaEngravada("N");
		if (getClienteVO().isPacUnhaEngravada()) {
			pessoa.getPaciente().setPacUnhaEngravada("S");
		}
		pessoa.getPaciente().setPacVisitaPedicuro("N");
		if (getClienteVO().isPacVisitaPedicuro()) {
			pessoa.getPaciente().setPacVisitaPedicuro("S");
		}
		pessoa.getPaciente().setPessoa(pessoa);
	}
	
	private void populaDePara(Pessoa pessoa) throws Exception {
		// pessoa
		getClienteVO().getPessoaVO().setPesCodigo(pessoa.getPesCodigo());
		getClienteVO().getPessoaVO().setPesNome(pessoa.getPesNome());
		getClienteVO().getPessoaVO().setPesDtNascimento(Util.converterCalendarParaString(pessoa.getPesDtNascimento(), Util.SIMPLE_DATE_FORMAT_DATA));
		getClienteVO().getPessoaVO().setPesObs(pessoa.getPesObs());
		getClienteVO().getPessoaVO().setPesSexo(pessoa.getPesSexo());
		getClienteVO().getPessoaVO().setPesCpf(pessoa.getPesCPF());
		if (pessoa.getPaciente().getConsulta() != null) {
			getClienteVO().setCliDataUltimaConsulta(Util.converterDateParaString(pessoa.getPaciente().getConsulta().getCosDtConsulta(), Util.SIMPLE_DATE_FORMAT_DATA));
			if (pessoa.getPaciente().getConsulta().getTratamento() != null) {
				getClienteVO().setCliUltimoTratamento(pessoa.getPaciente().getConsulta().getTratamento().getTraDescricao());
			}
		}
		// endereço
		getClienteVO().getEnderecoVO().setEndBairro(pessoa.getEndereco().getEndBairro());
		getClienteVO().getEnderecoVO().setEndCep(pessoa.getEndereco().getEndCep());
		getClienteVO().getEnderecoVO().setEndCidade(pessoa.getEndereco().getEndCidade());
		getClienteVO().getEnderecoVO().setEndCodigo(pessoa.getEndereco().getEndCodigo());
		getClienteVO().getEnderecoVO().setEndLogadouro(pessoa.getEndereco().getEndLogadouro());
		getClienteVO().getEnderecoVO().setEndNumero(pessoa.getEndereco().getEndNumero());
		if (pessoa.getEndereco().getEstado() != null) {
			getClienteVO().getEnderecoVO().getEstadoVO().setEstCodigo(pessoa.getEndereco().getEstado().getEstCodigo());
			getClienteVO().getEnderecoVO().getEstadoVO().setEstNome(pessoa.getEndereco().getEstado().getEstNome());
		}
		// lista de contatos
		getClienteVO().setListaContatos(new ArrayList<ContatoVO>());
		for (Contato contato: pessoa.getListaContato()) {
			ContatoVO contatoVO = new ContatoVO();
			contatoVO.setNovo(false);
			contatoVO.setConCodigo(contato.getConCodigo());
			contatoVO.setConDescricao(contato.getConDescricao());
			contatoVO.setConResponsavel(contato.getConResponsavel());
			contatoVO.getTipoContatoVO().setTcoCodigo(contato.getTipoContato().getTcoCodigo());
			contatoVO.getTipoContatoVO().setTcoDescricao(contato.getTipoContato().getTcoDescricao());
			getClienteVO().getListaContatos().add(contatoVO);
		}
		// paciente
		if (pessoa.getPaciente().getPacAlergicoMedicamentos() != null && pessoa.getPaciente().getPacAlergicoMedicamentos().equals("S")) {
			getClienteVO().setPacAlergicoMedicamentos(true);
		}
		getClienteVO().setPacAlergicoQuais(pessoa.getPaciente().getPacAlergicoQuais());
		getClienteVO().setPacAltura(pessoa.getPaciente().getPacAltura());
		if (pessoa.getPaciente().getPacAndaDescalco() != null && pessoa.getPaciente().getPacAndaDescalco().equals("S")) {
			getClienteVO().setPacAndaDescalco(true);
		}
		getClienteVO().setPacCalcadoUtiliza(pessoa.getPaciente().getPacCalcadoUtiliza());
		getClienteVO().setPacCirurgiaMotivo(pessoa.getPaciente().getPacCirurgiaMotivo());
		if (pessoa.getPaciente().getPacCirurgiaPes() != null && pessoa.getPaciente().getPacCirurgiaPes().equals("S")) {
			getClienteVO().setPacCirurgiaPes(true);
		}
		getClienteVO().setPacCodigo(pessoa.getPaciente().getPacCodigo());
		if (pessoa.getPaciente().getPacDiabetes() != null && pessoa.getPaciente().getPacDiabetes().equals("S")) {
			getClienteVO().setPacDiabetes(true);
		}
		if (pessoa.getPaciente().getPacHipertensao() != null && pessoa.getPaciente().getPacHipertensao().equals("S")) {
			getClienteVO().setPacHipertensao(true);
		}
		if (pessoa.getPaciente().getPacLabora() != null && pessoa.getPaciente().getPacLabora().equals("S")) {
			getClienteVO().setPacLabora(true);
		}
		getClienteVO().setPacNumeroCalcado(pessoa.getPaciente().getPacNumeroCalcado());
		getClienteVO().setPacPeso(pessoa.getPaciente().getPacPeso());
		if (pessoa.getPaciente().getPacTabagismo() != null && pessoa.getPaciente().getPacTabagismo().equals("S")) {
			getClienteVO().setPacTabagismo(true);
		}
		if (pessoa.getPaciente().getPacUnhaEngravada() != null && pessoa.getPaciente().getPacUnhaEngravada().equals("S")) {
			getClienteVO().setPacUnhaEngravada(true);
		}
		if (pessoa.getPaciente().getPacVisitaPedicuro() != null && pessoa.getPaciente().getPacVisitaPedicuro().equals("S")) {
			getClienteVO().setPacVisitaPedicuro(true);
		}
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

	public List<ClienteVO> getListaClienteVO() {
		return listaClienteVO;
	}

	public void setListaClienteVO(List<ClienteVO> listaClienteVO) {
		this.listaClienteVO = listaClienteVO;
	}

	public List<PessoaVO> getListaPessoaVO() {
		return listaPessoaVO;
	}

	public void setListaPessoaVO(List<PessoaVO> listaPessoaVO) {
		this.listaPessoaVO = listaPessoaVO;
	}
}
