package br.com.guedes.sistemaPodologia.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.com.guedes.sistemaPodologia.model.Pessoa;
import br.com.guedes.sistemaPodologia.util.IntegrationException;

/**
 * 
 * @author Guedes
 *
 */
public class PessoaDaoImpl extends HibernateDaoSupport implements PessoaDao {

	private static final Logger LOGGER = Logger.getLogger(PessoaDaoImpl.class);
	
	@Autowired  
    private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public Pessoa obterPorId(final Integer pesCodigo) throws IntegrationException {
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("from Pessoa ");
			hql.append(" where pesCodigo = " + pesCodigo);
			
			ArrayList<Pessoa> lista = (ArrayList<Pessoa>) getHibernateTemplate().find(hql.toString());
			return lista.get(0);
		} catch (Exception e) {
			LOGGER.error(e);
			throw new IntegrationException("Erro ao obter dados da Pessoa.");
		}
	}
}
