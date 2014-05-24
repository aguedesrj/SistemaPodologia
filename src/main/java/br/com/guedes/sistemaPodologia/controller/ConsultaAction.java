package br.com.guedes.sistemaPodologia.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.guedes.sistemaPodologia.facade.ClienteFacade;
import br.com.guedes.sistemaPodologia.facade.ProfissionalFacade;
import br.com.guedes.sistemaPodologia.facade.TratamentoFacade;
import br.com.guedes.sistemaPodologia.model.Paciente;
import br.com.guedes.sistemaPodologia.model.Pessoa;
import br.com.guedes.sistemaPodologia.model.Profissional;
import br.com.guedes.sistemaPodologia.model.Tratamento;
import br.com.guedes.sistemaPodologia.util.BusinessException;
import br.com.guedes.sistemaPodologia.vo.ClienteVO;
import br.com.guedes.sistemaPodologia.vo.ConsultaVO;
import br.com.guedes.sistemaPodologia.vo.HoraMarcarConsultaVO;
import br.com.guedes.sistemaPodologia.vo.PessoaVO;
import br.com.guedes.sistemaPodologia.vo.ProfissionalVO;
import br.com.guedes.sistemaPodologia.vo.TratamentoVO;

@Controller
@Scope("request")
public class ConsultaAction extends BasicAction {

	private static final long serialVersionUID = -7416561053425601163L;
	private static final Logger LOGGER = Logger.getLogger(ConsultaAction.class);
	private static final String SESSION_LISTA_CONTATOS = "SESSION_LISTA_CONTATOS";

	@Autowired
	private ClienteFacade clienteFacade;
	
	@Autowired
	private ProfissionalFacade profissionalFacade;
	
	@Autowired
	private TratamentoFacade tratamentoFacade;	
	
	private String mensagemUsuario;
	private ConsultaVO consultaVO;
	private List<HoraMarcarConsultaVO> listaHoraMarcarConsultaVO;
	
	public String iniciarCadastro() {
		try {
			setConsultaVO(new ConsultaVO());
    		// lista de profissionais.
    		List<Profissional> listaProfissionais = profissionalFacade.pesquisar(null);
    		for (Profissional profissional: listaProfissionais) {
    			ProfissionalVO profissionalVO = new ProfissionalVO();
    			profissionalVO.setPessoaVO(new PessoaVO());
    			profissionalVO.setPrfCodigo(profissional.getPrfCodigo());
    			profissionalVO.getPessoaVO().setPesCodigo(profissional.getPessoa().getPesCodigo());
    			profissionalVO.getPessoaVO().setPesNome(profissional.getPessoa().getPesNome());
    			getConsultaVO().getListaProfissionalVO().add(profissionalVO);
    		}
    		// lista de tratamentos.
    		List<Tratamento> listaTratamentos = tratamentoFacade.pesquisar(new Tratamento());
    		for (Tratamento tratamento: listaTratamentos) {
    			TratamentoVO tratamentoVO = new TratamentoVO();
    			tratamentoVO.setTraCodigo(tratamento.getTraCodigo());
    			tratamentoVO.setTraDescricao(tratamento.getTraDescricao());
    			getConsultaVO().getListaTratamentoVO().add(tratamentoVO);
    		}
    		// lista de clientes.
    		List<Paciente> listaClientes = clienteFacade.pesquisar(null);
    		for (Paciente paciente: listaClientes) {
    			ClienteVO clienteVO = new ClienteVO();
    			clienteVO.setPacCodigo(paciente.getPacCodigo());
    			clienteVO.getPessoaVO().setPesCodigo(paciente.getPessoa().getPesCodigo());
    			clienteVO.getPessoaVO().setPesNome(paciente.getPessoa().getPesNome());
    			getConsultaVO().getListaClienteVO().add(clienteVO);
    		}    	
    		return SUCCESS;
		} catch (Exception e) {
			setMensagemUsuario("Erro ao iniciar o cadastro da Consulta.");
			return ERROR;
		}
	}
	
	public String montarTabelaConsulta() {
		try {
			setListaHoraMarcarConsultaVO(new ArrayList<HoraMarcarConsultaVO>());
			getListaHoraMarcarConsultaVO().add(new HoraMarcarConsultaVO(1, "08:00"));
			getListaHoraMarcarConsultaVO().add(new HoraMarcarConsultaVO(2, "09:00"));
			getListaHoraMarcarConsultaVO().add(new HoraMarcarConsultaVO(3, "10:00"));
			getListaHoraMarcarConsultaVO().add(new HoraMarcarConsultaVO(4, "11:00"));
			getListaHoraMarcarConsultaVO().add(new HoraMarcarConsultaVO(5, "12:00"));
			getListaHoraMarcarConsultaVO().add(new HoraMarcarConsultaVO(6, "13:00"));
			getListaHoraMarcarConsultaVO().add(new HoraMarcarConsultaVO(7, "14:00"));
			getListaHoraMarcarConsultaVO().add(new HoraMarcarConsultaVO(8, "15:00"));
			getListaHoraMarcarConsultaVO().add(new HoraMarcarConsultaVO(9, "16:00"));
			getListaHoraMarcarConsultaVO().add(new HoraMarcarConsultaVO(10, "17:00"));
			getListaHoraMarcarConsultaVO().add(new HoraMarcarConsultaVO(11, "18:00"));
			getListaHoraMarcarConsultaVO().add(new HoraMarcarConsultaVO(12, "19:00"));
			getListaHoraMarcarConsultaVO().add(new HoraMarcarConsultaVO(13, "20:00"));
			return SUCCESS;
		} catch (Exception e) {
			LOG.fatal(e.getMessage(), e);
			if (e instanceof BusinessException) {
				setMensagemUsuario(e.getMessage());
			} else {
				setMensagemUsuario("Erro ao montar tabela consulta.");
			}
			return ERROR;
		}
	}
	
	@SuppressWarnings("unchecked")
	public String salvar() {
		return SUCCESS;
	}
	
	public String iniciarPesquisa() {
		return SUCCESS;
	}
	
	public String executarPesquisa() {
		return SUCCESS;
	}
	
	public String detalhar() {
		return SUCCESS;
	}
	
	public String iniciarAlteracao() {
		return SUCCESS;
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

	public ConsultaVO getConsultaVO() {
		return consultaVO;
	}

	public void setConsultaVO(ConsultaVO consultaVO) {
		this.consultaVO = consultaVO;
	}

	public List<HoraMarcarConsultaVO> getListaHoraMarcarConsultaVO() {
		return listaHoraMarcarConsultaVO;
	}

	public void setListaHoraMarcarConsultaVO(
			List<HoraMarcarConsultaVO> listaHoraMarcarConsultaVO) {
		this.listaHoraMarcarConsultaVO = listaHoraMarcarConsultaVO;
	}
}
