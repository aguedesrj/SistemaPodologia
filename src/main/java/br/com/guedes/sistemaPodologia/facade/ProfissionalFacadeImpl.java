package br.com.guedes.sistemaPodologia.facade;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.guedes.sistemaPodologia.dao.ContatoDao;
import br.com.guedes.sistemaPodologia.dao.EnderecoDao;
import br.com.guedes.sistemaPodologia.dao.PessoaDao;
import br.com.guedes.sistemaPodologia.dao.ProfissionalDao;
import br.com.guedes.sistemaPodologia.model.Contato;
import br.com.guedes.sistemaPodologia.model.Estado;
import br.com.guedes.sistemaPodologia.model.Pessoa;
import br.com.guedes.sistemaPodologia.model.Profissional;
import br.com.guedes.sistemaPodologia.model.TipoContato;
import br.com.guedes.sistemaPodologia.util.BusinessException;
import br.com.guedes.sistemaPodologia.util.IntegrationException;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
public class ProfissionalFacadeImpl implements ProfissionalFacade {
	
	private static final Logger LOGGER = Logger.getLogger(ProfissionalFacadeImpl.class);
	
	@Autowired
	private ProfissionalDao profissionalDao;
	
	@Autowired
	private ContatoDao contatoDao;
	
	@Autowired
	private PessoaDao pessoaDao;
	
	@Autowired
	private EnderecoDao enderecoDao;	
	
	@Autowired  
    private SessionFactory sessionFactory;	

	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.facade.ProfissionalFacade#listaTipoContatos()
	 */
	public List<TipoContato> listaTipoContatos() throws IntegrationException {
		LOGGER.info("Todos do tipo de contatos.");
		return contatoDao.listaTipoContatos();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.facade.ProfissionalFacade#listaEstados()
	 */
	public List<Estado> listaEstados() throws IntegrationException {
		LOGGER.info("Todos os Estados.");
		return enderecoDao.listaEstados();
	}	
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.facade.ProfissionalFacade#obterPorId(br.com.guedes.sistemaPodologia.model.Profissional)
	 */
	public Profissional obterPorId(final Profissional profissional) throws IntegrationException {
		return profissionalDao.pesquisarPorCriterios(profissional).get(0);
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.facade.ProfissionalFacade#pesquisarPorCriterios(br.com.guedes.sistemaPodologia.model.Profissional)
	 */
	public List<Profissional> pesquisarPorCriterios(final Profissional profissional) throws IntegrationException {
		return profissionalDao.pesquisarPorCriterios(profissional);
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.facade.ProfissionalFacade#salvar(br.com.guedes.sistemaPodologia.model.Pessoa)
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = IntegrationException.class)
	public void salvar(final Profissional profissional) throws IntegrationException, BusinessException {
		try {
			// verifica se já existe algum Profissional com o mesmo nome.
			Profissional profissionalCond = new Profissional();
			profissionalCond.setPessoa(new Pessoa());
			profissionalCond.getPessoa().setPesNome(profissional.getPessoa().getPesNome());
			// verifica se est?? sendo alterado.
			if (profissional.getPrfCodigo() == null || profissional.getPrfCodigo() == 0) {
				List<Profissional> lista = profissionalDao.pesquisarPorCriterios(profissionalCond);
				if (lista != null && !lista.isEmpty()) {
					throw new BusinessException("Profissional já está cadastrado.");
				}
			}
			
			// salvar endereço.
			sessionFactory.getCurrentSession().saveOrUpdate(profissional.getPessoa().getEndereco());
			sessionFactory.getCurrentSession().flush();
			// salvar pessoa.
			sessionFactory.getCurrentSession().saveOrUpdate(profissional.getPessoa());
			sessionFactory.getCurrentSession().flush();
			// salvar profissional.
			sessionFactory.getCurrentSession().saveOrUpdate(profissional);
			sessionFactory.getCurrentSession().flush();
			// deletar contatos.
			List<Contato> listaContatosDelete = contatoDao.listaContatosPorPessoa(profissional.getPessoa());
			for (Contato contatoDelete: listaContatosDelete) {
				sessionFactory.getCurrentSession().delete(contatoDelete);
			}
			sessionFactory.getCurrentSession().flush();
			// salvar contatos.
			for (Contato contato: profissional.getPessoa().getListaContato()) {
				sessionFactory.getCurrentSession().merge(contato);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (Exception e) {
			LOGGER.error(e);
			if (e instanceof BusinessException) {
				throw new BusinessException(e.getMessage());
			} else {
				throw new IntegrationException("Não foi possível salvar o Profissional.");
			}
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

	public ProfissionalDao getProfissionalDao() {
		return profissionalDao;
	}

	public void setProfissionalDao(ProfissionalDao profissionalDao) {
		this.profissionalDao = profissionalDao;
	}

	public PessoaDao getPessoaDao() {
		return pessoaDao;
	}

	public void setPessoaDao(PessoaDao pessoaDao) {
		this.pessoaDao = pessoaDao;
	}
}
