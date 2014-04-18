package br.com.guedes.sistemaPodologia.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.com.guedes.sistemaPodologia.model.Pessoa;
import br.com.guedes.sistemaPodologia.util.IntegrationException;

/**
 * 
 * @author Guedes
 *
 */
public class ClienteDaoImpl extends HibernateDaoSupport implements ClienteDao {

	private static final Logger LOGGER = Logger.getLogger(ClienteDaoImpl.class);
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.dao.ClienteDao#pesquisarPorCriterios(br.com.guedes.sistemaPodologia.model.Pessoa)
	 */
	@SuppressWarnings("unchecked")
	public List<Pessoa> pesquisarPorCriterios(final Pessoa pessoa) throws IntegrationException {
		try {
			LOGGER.info("Pesquisando Pessoa...");
			StringBuilder hql = new StringBuilder();
			
			hql.append("from Pessoa ");
			
			// Nome
			if (pessoa.getPesNome() != null) {
				hql.append(" where upper(pesNome) like upper('%" + pessoa.getPesNome().trim() + "%')");
			}
			
			return getHibernateTemplate().findByValueBean(hql.toString(), pessoa);
		} catch (Exception e) {
			LOGGER.error("Erro ao pesquisar Cliente.", e);
			throw new IntegrationException("Erro ao pesquisar Cliente.", e);
		}
	}
}
