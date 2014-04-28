package br.com.guedes.sistemaPodologia.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.com.guedes.sistemaPodologia.model.Tratamento;
import br.com.guedes.sistemaPodologia.util.IntegrationException;

/**
 * 
 * @author Guedes
 *
 */
public class TratamentoDaoImpl extends HibernateDaoSupport implements TratamentoDao {

	private static final Logger LOGGER = Logger.getLogger(TratamentoDaoImpl.class);
	
	@Autowired  
    private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Tratamento> pesquisarPorCriterios(final Tratamento tratamento) throws IntegrationException {
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("from Tratamento ");
			hql.append(" where traCodigo > 0 ");
			if (tratamento.getTraDescricao() != null) {
				hql.append(" and upper(traDescricao) like upper('%" + tratamento.getTraDescricao().trim() + "%') ");
			}
			if (tratamento.getTraCodigo() != null && tratamento.getTraCodigo() > 0) {
				hql.append("and traCodigo = " + tratamento.getTraCodigo());
			}
			return (ArrayList<Tratamento>) getHibernateTemplate().find(hql.toString());
		} catch (Exception e) {
			LOGGER.error("Erro ao pesquisar Tratamento.", e);
			throw new IntegrationException("Erro ao pesquisar Tratamento.", e);
		}
	}
}
