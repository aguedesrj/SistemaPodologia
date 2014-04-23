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
import br.com.guedes.sistemaPodologia.dao.PacienteDao;
import br.com.guedes.sistemaPodologia.dao.PessoaDao;
import br.com.guedes.sistemaPodologia.model.Contato;
import br.com.guedes.sistemaPodologia.model.Estado;
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
	private PacienteDao pacienteDao;	
	
	@Autowired
	private PessoaDao pessoaDao;	
	
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
	 * @see br.com.guedes.sistemaPodologia.facade.ClienteFacade#pesquisarPorCriterios(br.com.guedes.sistemaPodologia.model.Pessoa)
	 */
	public List<Pessoa> pesquisarPorCriterios(final Pessoa pessoa) throws IntegrationException {
		return clienteDao.pesquisarPorCriterios(pessoa);
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.facade.ClienteFacade#salvar(br.com.guedes.sistemaPodologia.model.Pessoa)
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = IntegrationException.class)
	public void salvar(final Pessoa pessoa) throws IntegrationException, BusinessException {
		try {
			// verifica se jÃ¡ existe algum Cliente com o mesmo nome.
			Pessoa pessoaCond = new Pessoa();
			pessoaCond.setPesNome(pessoa.getPesNome());
			// verifica se estÃ¡ sendo alterado.
			if (pessoa.getPesCodigo() == null || pessoa.getPesCodigo() == 0) {
				List<Pessoa> lista = clienteDao.pesquisarPorCriterios(pessoaCond);
				if (lista != null && !lista.isEmpty()) {
					throw new BusinessException("Cliente já está cadastrado.");
				}
			}
			
			// salvar endereço.
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
				sessionFactory.getCurrentSession().saveOrUpdate(contato);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (Exception e) {
			LOGGER.error(e);
			if (e instanceof BusinessException) {
				throw new BusinessException(e.getMessage());
			} else {
				throw new IntegrationException("Não foi possível salvar o Cliente.");
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.facade.ClienteFacade#obterPorId(br.com.guedes.sistemaPodologia.model.Pessoa, java.util.List, br.com.guedes.sistemaPodologia.model.Paciente)
	 */
	public Pessoa obterPorId(Pessoa pessoa) throws IntegrationException {
		try {
			// dados pessoa.
			pessoa = pessoaDao.obterPorId(pessoa.getPesCodigo());
			// dados paciente.
			pessoa.setPaciente(pacienteDao.obterPorIdPessoa(pessoa.getPesCodigo()));
			// obter dados da última consulta.
			pessoa.getPaciente().setConsulta(consultaDao.obterUltimaConsulta(pessoa.getPaciente()));
			return pessoa;
		} catch (Exception e) {
			LOGGER.error(e);
			throw new IntegrationException("Não foi possível obter dados do Cliente.");
		}
	}

	public ClienteDao getClienteDao() {
		return clienteDao;
	}

	public void setClienteDao(ClienteDao clienteDao) {
		this.clienteDao = clienteDao;
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

	public PessoaDao getPessoaDao() {
		return pessoaDao;
	}

	public void setPessoaDao(PessoaDao pessoaDao) {
		this.pessoaDao = pessoaDao;
	}

	public PacienteDao getPacienteDao() {
		return pacienteDao;
	}

	public void setPacienteDao(PacienteDao pacienteDao) {
		this.pacienteDao = pacienteDao;
	}

	public ConsultaDao getConsultaDao() {
		return consultaDao;
	}

	public void setConsultaDao(ConsultaDao consultaDao) {
		this.consultaDao = consultaDao;
	}
}
