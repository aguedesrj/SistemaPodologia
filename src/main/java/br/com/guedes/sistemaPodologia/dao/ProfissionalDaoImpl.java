package br.com.guedes.sistemaPodologia.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.com.guedes.sistemaPodologia.model.Profissional;
import br.com.guedes.sistemaPodologia.util.IntegrationException;

/**
 * 
 * @author Guedes
 *
 */
public class ProfissionalDaoImpl extends HibernateDaoSupport implements ProfissionalDao {

	private static final Logger LOGGER = Logger.getLogger(ProfissionalDaoImpl.class);
	
	@Autowired  
    private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Profissional> pesquisarPorCriterios(final Profissional profissional) throws IntegrationException {
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("from Profissional ");
			hql.append(" where prfCodigo > 0 ");
			if (profissional.getPessoa() != null && profissional.getPessoa().getPesNome() != null) {
				hql.append(" and upper(pessoa.pesNome) like upper('%" + profissional.getPessoa().getPesNome().trim() + "%') ");
			}
			if (profissional.getPrfCodigo() != null && profissional.getPrfCodigo() > 0) {
				hql.append("and prfCodigo = " + profissional.getPrfCodigo());
			}
			return (ArrayList<Profissional>) getHibernateTemplate().find(hql.toString());
		} catch (Exception e) {
			LOGGER.error("Erro ao pesquisar Profissional.", e);
			throw new IntegrationException("Erro ao pesquisar Profissional.", e);
		}
	}
}
