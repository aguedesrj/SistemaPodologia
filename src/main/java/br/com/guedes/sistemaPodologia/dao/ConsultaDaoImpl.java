package br.com.guedes.sistemaPodologia.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.com.guedes.sistemaPodologia.model.Consulta;
import br.com.guedes.sistemaPodologia.model.Paciente;
import br.com.guedes.sistemaPodologia.util.IntegrationException;

/**
 * 
 * @author Guedes
 *
 */
public class ConsultaDaoImpl extends HibernateDaoSupport implements ConsultaDao {

	private static final Logger LOGGER = Logger.getLogger(ConsultaDaoImpl.class);
	
	@Autowired  
    private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.dao.ConsultaDao#obterUltimaConsulta(br.com.guedes.sistemaPodologia.model.Paciente)
	 */
	@SuppressWarnings("unchecked")
	public Consulta obterUltimaConsulta(final Paciente paciente) throws IntegrationException {
		try {
			LOGGER.debug("Obtendo dados na última consulta do paciente...");
			
			StringBuilder hql = new StringBuilder();
			hql.append("from Consulta ");
			hql.append("where paciente.pacCodigo = " + paciente.getPacCodigo());
			hql.append("order by cosDtConsulta desc");
			
			ArrayList<Consulta> lista = (ArrayList<Consulta>) getHibernateTemplate().find(hql.toString());
			if(lista != null && !lista.isEmpty()) {
				return lista.get(0);
			}
			return null;
		} catch (Exception e) {
			throw new IntegrationException("Não foi possível obter a última consulta do Paciente.");
		}
	}
}
