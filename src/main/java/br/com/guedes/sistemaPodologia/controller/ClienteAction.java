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
				//pessoa = clienteFacade.obterPorId(new Paciente());
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
    		Paciente pacienteParam = new Paciente();
    		pacienteParam.setPessoa(new Pessoa());
    		pacienteParam.getPessoa().setPesNome(getClienteVO().getPessoaVO().getPesNome());
    		List<Paciente> listaPaciente = clienteFacade.pesquisar(pacienteParam);
    		if (listaPaciente == null || listaPaciente.isEmpty()) {
    			setMensagemUsuario("Cliente não encontrado.");
    			return ERROR;
    		} else {
    			setListaClienteVO(new ArrayList<ClienteVO>());
    			for (Paciente paciente: listaPaciente) {
    				ClienteVO clienteVO = new ClienteVO();
    				clienteVO.setPacCodigo(paciente.getPacCodigo());
    				clienteVO.getPessoaVO().setPesCodigo(paciente.getPessoa().getPesCodigo());
    				clienteVO.getPessoaVO().setPesNome(paciente.getPessoa().getPesNome());
    				getListaClienteVO().add(clienteVO);
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
			Paciente paciente = new Paciente();
			paciente.setPessoa(new Pessoa());
			paciente.getPessoa().setPesCodigo(getClienteVO().getPessoaVO().getPesCodigo());
			paciente = clienteFacade.obterPorId(paciente);
    		if (paciente.getPessoa().getPesNome() == null) {
    			setMensagemUsuario("Cliente não encontrado.");
    			return ERROR;
    		} else {
    			// seta dados do Cliente.
    			populaDePara(paciente);
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
    		Paciente paciente = new Paciente();
    		paciente.setPessoa(new Pessoa());
    		paciente.getPessoa().setPesCodigo(getClienteVO().getPessoaVO().getPesCodigo());
    		paciente = clienteFacade.obterPorId(paciente);
    		if (paciente == null) {
    			addActionError("Cliente não encontrado.");
    			return ERROR;
    		}
    		populaDePara(paciente);
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
	
	private void populaDePara(Paciente paciente) throws Exception {
		// pessoa
		getClienteVO().getPessoaVO().setPesCodigo(paciente.getPessoa().getPesCodigo());
		getClienteVO().getPessoaVO().setPesNome(paciente.getPessoa().getPesNome());
		getClienteVO().getPessoaVO().setPesDtNascimento(Util.converterCalendarParaString(paciente.getPessoa().getPesDtNascimento(), Util.SIMPLE_DATE_FORMAT_DATA));
		getClienteVO().getPessoaVO().setPesObs(paciente.getPessoa().getPesObs());
		getClienteVO().getPessoaVO().setPesSexo(paciente.getPessoa().getPesSexo());
		getClienteVO().getPessoaVO().setPesCpf(paciente.getPessoa().getPesCPF());
		if (paciente.getConsulta() != null) {
			getClienteVO().setCliDataUltimaConsulta(Util.converterDateParaString(paciente.getConsulta().getCosDtConsulta()));
			if (paciente.getConsulta().getTratamento() != null) {
				getClienteVO().setCliUltimoTratamento(paciente.getConsulta().getTratamento().getTraDescricao());
			}
		}
		// endereço
		getClienteVO().getEnderecoVO().setEndBairro(paciente.getPessoa().getEndereco().getEndBairro());
		getClienteVO().getEnderecoVO().setEndCep(paciente.getPessoa().getEndereco().getEndCep());
		getClienteVO().getEnderecoVO().setEndCidade(paciente.getPessoa().getEndereco().getEndCidade());
		getClienteVO().getEnderecoVO().setEndCodigo(paciente.getPessoa().getEndereco().getEndCodigo());
		getClienteVO().getEnderecoVO().setEndLogadouro(paciente.getPessoa().getEndereco().getEndLogadouro());
		getClienteVO().getEnderecoVO().setEndNumero(paciente.getPessoa().getEndereco().getEndNumero());
		if (paciente.getPessoa().getEndereco().getEstado() != null) {
			getClienteVO().getEnderecoVO().getEstadoVO().setEstCodigo(paciente.getPessoa().getEndereco().getEstado().getEstCodigo());
			getClienteVO().getEnderecoVO().getEstadoVO().setEstNome(paciente.getPessoa().getEndereco().getEstado().getEstNome());
		}
		// lista de contatos
		getClienteVO().setListaContatos(new ArrayList<ContatoVO>());
		for (Contato contato: paciente.getPessoa().getListaContato()) {
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
		if (paciente.getPacAlergicoMedicamentos() != null && paciente.getPacAlergicoMedicamentos().equals("S")) {
			getClienteVO().setPacAlergicoMedicamentos(true);
		}
		getClienteVO().setPacAlergicoQuais(paciente.getPacAlergicoQuais());
		getClienteVO().setPacAltura(paciente.getPacAltura());
		if (paciente.getPacAndaDescalco() != null && paciente.getPacAndaDescalco().equals("S")) {
			getClienteVO().setPacAndaDescalco(true);
		}
		getClienteVO().setPacCalcadoUtiliza(paciente.getPacCalcadoUtiliza());
		getClienteVO().setPacCirurgiaMotivo(paciente.getPacCirurgiaMotivo());
		if (paciente.getPacCirurgiaPes() != null && paciente.getPacCirurgiaPes().equals("S")) {
			getClienteVO().setPacCirurgiaPes(true);
		}
		getClienteVO().setPacCodigo(paciente.getPacCodigo());
		if (paciente.getPacDiabetes() != null && paciente.getPacDiabetes().equals("S")) {
			getClienteVO().setPacDiabetes(true);
		}
		if (paciente.getPacHipertensao() != null && paciente.getPacHipertensao().equals("S")) {
			getClienteVO().setPacHipertensao(true);
		}
		if (paciente.getPacLabora() != null && paciente.getPacLabora().equals("S")) {
			getClienteVO().setPacLabora(true);
		}
		getClienteVO().setPacNumeroCalcado(paciente.getPacNumeroCalcado());
		getClienteVO().setPacPeso(paciente.getPacPeso());
		if (paciente.getPacTabagismo() != null && paciente.getPacTabagismo().equals("S")) {
			getClienteVO().setPacTabagismo(true);
		}
		if (paciente.getPacUnhaEngravada() != null && paciente.getPacUnhaEngravada().equals("S")) {
			getClienteVO().setPacUnhaEngravada(true);
		}
		if (paciente.getPacVisitaPedicuro() != null && paciente.getPacVisitaPedicuro().equals("S")) {
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
}
