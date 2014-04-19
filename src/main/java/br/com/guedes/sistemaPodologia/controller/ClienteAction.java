package br.com.guedes.sistemaPodologia.controller;

import java.util.ArrayList;
import java.util.Calendar;
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
	
	public String iniciarPesquisa() {
		setClienteVO(new ClienteVO());
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String salvar() {
		try {
			// lista de contatos da sessão
			getClienteVO().setListaContatos((List<ContatoVO>) this.getRequest().getSession().getAttribute(SESSION_LISTA_CONTATOS));
			
			Pessoa pessoa = new Pessoa();
			List<Contato> listaContatos = new ArrayList<Contato>();
			Paciente paciente = new Paciente();
			
			// de-para
			populaDeParaSalvar(pessoa, listaContatos, paciente);
			
	  		// salvar.
	  		clienteFacade.salvar(pessoa, listaContatos, paciente);
	  		
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
	
	public String executarPesquisa() {
    	try {
    		Pessoa pessoaParam = new Pessoa();
    		pessoaParam.setPesNome(getClienteVO().getPessoaVO().getPesNome());
    		List<Pessoa> listaPessoa = clienteFacade.pesquisarPorCriterios(pessoaParam);
    		if (listaPessoa == null || listaPessoa.isEmpty()) {
    			setMensagemUsuario("Cliente nÃ£o encontrado.");
    			return ERROR;
    		} else {
    			// popular lista.
    			setListaClienteVO(new ArrayList<ClienteVO>());
    			for (Pessoa pessoa: listaPessoa) {
    				ClienteVO clienteVO = new ClienteVO();
    				clienteVO.setPessoaVO(new PessoaVO());
    				
    				clienteVO.getPessoaVO().setPesCodigo(pessoa.getPesCodigo());
    				clienteVO.getPessoaVO().setPesNome(pessoa.getPesNome());
    				
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
//    	try {
//    		Produto produto = clienteFacade.obterProdutoPorId(getClienteVO().getPessoaVO().getPesCodigo());
//    		if (produto == null) {
//    			setMensagemUsuario("Cliente não encontrado.");
//    			return ERROR;
//    		} else {
//    			// seta dados do Produto.
//    			preencherProduto(produto);
//    		}
    		return SUCCESS;
//		} catch (Exception e) {
//			LOGGER.error("Erro ao detalhar Cliente.", e);
//			setMensagemUsuario("Erro ao detalhar Cliente.");
//			return ERROR;
//		}
	}
	
	/**
	 * 
	 * @param pessoa Pessoa
	 * @param listaContatos List<Contato>
	 * @param paciente Paciente
	 * @throws Exception
	 */
	private void populaDeParaSalvar(Pessoa pessoa, List<Contato> listaContatos, Paciente paciente) throws Exception {
		// pessoa
		pessoa.setPesCodigo(getClienteVO().getPessoaVO().getPesCodigo());
		pessoa.setPesNome(getClienteVO().getPessoaVO().getPesNome());
		pessoa.setPesDtCadastro(Calendar.getInstance());
		pessoa.setPesDtNascimento(Util.converterStringParaCalendar(getClienteVO().getPessoaVO().getPesDtNascimento()));
		pessoa.setPesObs(getClienteVO().getPessoaVO().getPesObs());
		pessoa.setPesSexo(getClienteVO().getPessoaVO().getPesSexo());
		// endereço
		pessoa.setEndereco(new Endereco());
		pessoa.getEndereco().setEndBairro(getClienteVO().getEnderecoVO().getEndBairro());
		pessoa.getEndereco().setEndCep(getClienteVO().getEnderecoVO().getEndCep());
		pessoa.getEndereco().setEndCidade(getClienteVO().getEnderecoVO().getEndCidade());
		pessoa.getEndereco().setEndCodigo(getClienteVO().getEnderecoVO().getEndCodigo());
		pessoa.getEndereco().setEndLogadouro(getClienteVO().getEnderecoVO().getEndLogadouro());
		pessoa.getEndereco().setEndNumero(getClienteVO().getEnderecoVO().getEndNumero());
		pessoa.getEndereco().setEstado(new Estado());
		pessoa.getEndereco().getEstado().setEstCodigo(getClienteVO().getEnderecoVO().getEstadoVO().getEstCodigo());
		// lista de contatos
		for (ContatoVO contatoVO: getClienteVO().getListaContatos()) {
			Contato contato = new Contato();
			if (contatoVO.isNovo()) {
				contato.setConCodigo(null);
			} else {
				contato.setConCodigo(contatoVO.getConCodigo());
			}
			contato.setConDescricao(contatoVO.getConDescricao());
			contato.setConResponsavel(contatoVO.getConResponsavel());
			contato.setTipoContato(new TipoContato());
			contato.getTipoContato().setTcoCodigo(contatoVO.getTipoContatoVO().getTcoCodigo());
			contato.setPessoa(pessoa);
			listaContatos.add(contato);
		}
		// paciente
		paciente.setPacAlergicoMedicamentos(getClienteVO().getPacAlergicoMedicamentos());
		paciente.setPacAlergicoQuais(getClienteVO().getPacAlergicoQuais());
		paciente.setPacAltura(getClienteVO().getPacAltura());
		paciente.setPacAndaDescalco(getClienteVO().getPacAndaDescalco());
		paciente.setPacCalcadoUtiliza(getClienteVO().getPacCalcadoUtiliza());
		paciente.setPacCirurgiaMotivo(getClienteVO().getPacCirurgiaMotivo());
		paciente.setPacCirurgiaPes(getClienteVO().getPacCirurgiaPes());
		paciente.setPacCodigo(getClienteVO().getPacCodigo());
		paciente.setPacDiabetes(getClienteVO().getPacDiabetes());
		paciente.setPacHipertensao(getClienteVO().getPacHipertensao());
		paciente.setPacLabora(getClienteVO().getPacLabora());
		paciente.setPacNumeroCalcado(getClienteVO().getPacNumeroCalcado());
		paciente.setPacPeso(getClienteVO().getPacPeso());
		paciente.setPacTabagismo(getClienteVO().getPacTabagismo());
		paciente.setPacUnhaEngravada(getClienteVO().getPacUnhaEngravada());
		paciente.setPacVisitaPedicuro(getClienteVO().getPacVisitaPedicuro());
		paciente.setPessoa(pessoa);
	}
	
	private void populaDePara(Pessoa pessoa, List<Contato> listaContatos, Paciente paciente) throws Exception {
		// pessoa
		getClienteVO().getPessoaVO().setPesCodigo(pessoa.getPesCodigo());
		getClienteVO().getPessoaVO().setPesNome(pessoa.getPesNome());
		getClienteVO().getPessoaVO().setPesDtNascimento(Util.converterCalendarParaString(pessoa.getPesDtNascimento(), Util.SIMPLE_DATE_FORMAT_DATA));
		getClienteVO().getPessoaVO().setPesObs(pessoa.getPesObs());
		getClienteVO().getPessoaVO().setPesSexo(pessoa.getPesSexo());
		// endereço
		getClienteVO().getEnderecoVO().setEndBairro(pessoa.getEndereco().getEndBairro());
		getClienteVO().getEnderecoVO().setEndCep(pessoa.getEndereco().getEndCep());
		getClienteVO().getEnderecoVO().setEndCidade(pessoa.getEndereco().getEndCidade());
		getClienteVO().getEnderecoVO().setEndCodigo(pessoa.getEndereco().getEndCodigo());
		getClienteVO().getEnderecoVO().setEndLogadouro(pessoa.getEndereco().getEndLogadouro());
		getClienteVO().getEnderecoVO().setEndNumero(pessoa.getEndereco().getEndNumero());
		getClienteVO().getEnderecoVO().getEstadoVO().setEstCodigo(pessoa.getEndereco().getEstado().getEstCodigo());
		// lista de contatos
		getClienteVO().setListaContatos(new ArrayList<ContatoVO>());
		for (Contato contato: listaContatos) {
			ContatoVO contatoVO = new ContatoVO();
			contatoVO.setNovo(false);
			contatoVO.setConCodigo(contato.getConCodigo());
			contatoVO.setConDescricao(contato.getConDescricao());
			contatoVO.setConResponsavel(contato.getConResponsavel());
			contatoVO.getTipoContatoVO().setTcoCodigo(contato.getTipoContato().getTcoCodigo());
			getClienteVO().getListaContatos().add(contatoVO);
		}
		// paciente
		getClienteVO().setPacAlergicoMedicamentos(paciente.getPacAlergicoMedicamentos());
		getClienteVO().setPacAlergicoQuais(paciente.getPacAlergicoQuais());
		getClienteVO().setPacAltura(paciente.getPacAltura());
		getClienteVO().setPacAndaDescalco(paciente.getPacAndaDescalco());
		getClienteVO().setPacCalcadoUtiliza(paciente.getPacCalcadoUtiliza());
		getClienteVO().setPacCirurgiaMotivo(paciente.getPacCirurgiaMotivo());
		getClienteVO().setPacCirurgiaPes(paciente.getPacCirurgiaPes());
		getClienteVO().setPacCodigo(paciente.getPacCodigo());
		getClienteVO().setPacDiabetes(paciente.getPacDiabetes());
		getClienteVO().setPacHipertensao(paciente.getPacHipertensao());
		getClienteVO().setPacLabora(paciente.getPacLabora());
		getClienteVO().setPacNumeroCalcado(paciente.getPacNumeroCalcado());
		getClienteVO().setPacPeso(paciente.getPacPeso());
		getClienteVO().setPacTabagismo(paciente.getPacTabagismo());
		getClienteVO().setPacUnhaEngravada(paciente.getPacUnhaEngravada());
		getClienteVO().setPacVisitaPedicuro(paciente.getPacVisitaPedicuro());
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
