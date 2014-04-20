package br.com.guedes.sistemaPodologia.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.com.guedes.sistemaPodologia.model.Paciente;
import br.com.guedes.sistemaPodologia.util.IntegrationException;

public class PacienteDaoImpl extends HibernateDaoSupport implements PacienteDao {

	private static final Logger LOGGER = Logger.getLogger(PacienteDaoImpl.class);
	
	@Autowired  
    private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public Paciente obterPorIdPessoa(final Integer pesCodigo) throws IntegrationException {
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("from Paciente ");
			hql.append(" where pessoa.pesCodigo = " + pesCodigo);
			
			ArrayList<Paciente> lista = (ArrayList<Paciente>) getHibernateTemplate().find(hql.toString());
			return lista.get(0);
		} catch (Exception e) {
			LOGGER.error(e);
			throw new IntegrationException("Erro ao obter dados da Paciente.");
		}
	}
}
