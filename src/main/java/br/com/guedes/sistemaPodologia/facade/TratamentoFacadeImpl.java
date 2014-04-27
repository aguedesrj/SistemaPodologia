package br.com.guedes.sistemaPodologia.facade;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.guedes.sistemaPodologia.dao.TratamentoDao;
import br.com.guedes.sistemaPodologia.model.Tratamento;
import br.com.guedes.sistemaPodologia.util.BusinessException;
import br.com.guedes.sistemaPodologia.util.IntegrationException;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
public class TratamentoFacadeImpl implements TratamentoFacade {
	
	private static final Logger LOGGER = Logger.getLogger(TratamentoFacadeImpl.class);
	
	@Autowired
	private TratamentoDao tratamentoDao;
	
	@Autowired  
    private SessionFactory sessionFactory;	

	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.facade.TratamentoFacade#pesquisarPorCriterios(br.com.guedes.sistemaPodologia.model.Tratamento)
	 */
	public List<Tratamento> pesquisarPorCriterios(final Tratamento tratamento) throws IntegrationException {
		return tratamentoDao.pesquisarPorCriterios(tratamento);
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.facade.TratamentoFacade#salvar(br.com.guedes.sistemaPodologia.model.Tratamento)
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = IntegrationException.class)
	public void salvar(final Tratamento tratamento) throws IntegrationException, BusinessException {
		try {
			// salvar tratamento.
			sessionFactory.getCurrentSession().saveOrUpdate(tratamento);
			sessionFactory.getCurrentSession().flush();
		} catch (Exception e) {
			LOGGER.error(e);
			if (e instanceof BusinessException) {
				throw new BusinessException(e.getMessage());
			} else {
				throw new IntegrationException("Não foi possível salvar o Tratamento.");
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.facade.TratamentoFacade#obterPorId(br.com.guedes.sistemaPodologia.model.Tratamento)
	 */
	public Tratamento obterPorId(Tratamento tratamento) throws IntegrationException {
		try {
			// dados tratamento.
			return tratamentoDao.pesquisarPorCriterios(tratamento).get(0);
		} catch (Exception e) {
			LOGGER.error(e);
			throw new IntegrationException("Não foi possível obter dados do Cliente.");
		}
	}

	public TratamentoDao getTratamentoDao() {
		return tratamentoDao;
	}

	public void setTratamentoDao(TratamentoDao tratamentoDao) {
		this.tratamentoDao = tratamentoDao;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
