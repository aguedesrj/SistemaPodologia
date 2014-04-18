package br.com.guedes.sistemaPodologia.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import br.com.guedes.sistemaPodologia.model.Estado;
import br.com.guedes.sistemaPodologia.util.IntegrationException;

@Repository
public class EnderecoDaoImpl extends HibernateDaoSupport implements EnderecoDao {

	private static final Logger LOGGER = Logger.getLogger(EnderecoDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	public List<Estado> listaEstados() throws IntegrationException {
		try {
			return getHibernateTemplate().findByValueBean(new StringBuilder("from Estado ").toString(), Estado.class);
		} catch (Exception e) {
			LOGGER.fatal("Erro ao buscar lista de Estados.", e);
			e.printStackTrace();
			throw new IntegrationException("Erro ao buscar lista de Estados.");
		}		
	}
}
