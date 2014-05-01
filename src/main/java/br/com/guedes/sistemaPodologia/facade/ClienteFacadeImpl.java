package br.com.guedes.sistemaPodologia.facade;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.guedes.sistemaPodologia.dao.ClienteDao;
import br.com.guedes.sistemaPodologia.dao.ConsultaDao;
import br.com.guedes.sistemaPodologia.dao.ContatoDao;
import br.com.guedes.sistemaPodologia.dao.EnderecoDao;
import br.com.guedes.sistemaPodologia.model.Contato;
import br.com.guedes.sistemaPodologia.model.Estado;
import br.com.guedes.sistemaPodologia.model.Paciente;
import br.com.guedes.sistemaPodologia.model.Pessoa;
import br.com.guedes.sistemaPodologia.model.TipoContato;
import br.com.guedes.sistemaPodologia.util.BusinessException;
import br.com.guedes.sistemaPodologia.util.IntegrationException;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
public class ClienteFacadeImpl implements ClienteFacade {
	
	private static final Logger LOGGER = Logger.getLogger(ClienteFacadeImpl.class);
	
	@Autowired
	private ClienteDao clienteDao;	
	
	@Autowired
	private ContatoDao contatoDao;
	
	@Autowired
	private EnderecoDao enderecoDao;	
	
	@Autowired
	private ConsultaDao consultaDao;
	
	@Autowired  
    private SessionFactory sessionFactory;	

	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.facade.ClienteFacade#listaTipoContatos()
	 */
	public List<TipoContato> listaTipoContatos() throws IntegrationException {
		LOGGER.info("Todos do tipo de contatos.");
		return contatoDao.listaTipoContatos();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.facade.ClienteFacade#listaEstados()
	 */
	public List<Estado> listaEstados() throws IntegrationException {
		LOGGER.info("Todos os Estados.");
		return enderecoDao.listaEstados();
	}	
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.facade.ClienteFacade#pesquisar(br.com.guedes.sistemaPodologia.model.Paciente)
	 */
	public List<Paciente> pesquisar(final Paciente paciente) throws IntegrationException {
		return clienteDao.pesquisar(paciente);
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.facade.ClienteFacade#salvar(br.com.guedes.sistemaPodologia.model.Pessoa)
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = IntegrationException.class)
	public void salvar(final Pessoa pessoa) throws IntegrationException, BusinessException {
		try {
			// verifica se já existe algum Cliente com o mesmo nome.
			Paciente pacienteCond = new Paciente();
			pacienteCond.setPessoa(new Pessoa());
			pacienteCond.getPessoa().setPesNome(pessoa.getPesNome());
			// verifica se está sendo alterado.
			if (pessoa.getPesCodigo() == null || pessoa.getPesCodigo() == 0) {
				List<Paciente> lista = clienteDao.pesquisar(pacienteCond);
				if (lista != null && !lista.isEmpty()) {
					throw new BusinessException("Cliente já está cadastrado.");
				}
			}
			
			// salvar endere�o.
			sessionFactory.getCurrentSession().saveOrUpdate(pessoa.getEndereco());
			sessionFactory.getCurrentSession().flush();
			// salvar pessoa.
			sessionFactory.getCurrentSession().saveOrUpdate(pessoa);
			sessionFactory.getCurrentSession().flush();
			// salvar dados Paciente.
			sessionFactory.getCurrentSession().saveOrUpdate(pessoa.getPaciente());
			sessionFactory.getCurrentSession().flush();
			// deletar contatos.
			List<Contato> listaContatosDelete = contatoDao.listaContatosPorPessoa(pessoa);
			for (Contato contatoDelete: listaContatosDelete) {
				sessionFactory.getCurrentSession().delete(contatoDelete);
			}
			sessionFactory.getCurrentSession().flush();
			// salvar contatos.
			for (Contato contato: pessoa.getListaContato()) {
				sessionFactory.getCurrentSession().merge(contato);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (Exception e) {
			LOGGER.error(e);
			if (e instanceof BusinessException) {
				throw new BusinessException(e.getMessage());
			} else {
				throw new IntegrationException("N�o foi poss�vel salvar o Cliente.");
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.facade.ClienteFacade#obterPorId(br.com.guedes.sistemaPodologia.model.Paciente)
	 */
	public Paciente obterPorId(Paciente paciente) throws IntegrationException {
		try {
			paciente = clienteDao.obterPorId(paciente.getPessoa().getPesCodigo());
			// obter dados da última consulta.
			paciente.setConsulta(consultaDao.obterUltimaConsulta(paciente));
			return paciente;
		} catch (Exception e) {
			LOGGER.error(e);
			throw new IntegrationException("Não foi possível obter dados do Cliente.");
		}
	}

	public ContatoDao getContatoDao() {
		return contatoDao;
	}

	public void setContatoDao(ContatoDao contatoDao) {
		this.contatoDao = contatoDao;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public EnderecoDao getEnderecoDao() {
		return enderecoDao;
	}

	public void setEnderecoDao(EnderecoDao enderecoDao) {
		this.enderecoDao = enderecoDao;
	}

	public ConsultaDao getConsultaDao() {
		return consultaDao;
	}

	public void setConsultaDao(ConsultaDao consultaDao) {
		this.consultaDao = consultaDao;
	}

	public ClienteDao getClienteDao() {
		return clienteDao;
	}

	public void setClienteDao(ClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}
}
