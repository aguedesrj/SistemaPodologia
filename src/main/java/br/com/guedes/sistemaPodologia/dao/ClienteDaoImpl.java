package br.com.guedes.sistemaPodologia.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.com.guedes.sistemaPodologia.model.Paciente;
import br.com.guedes.sistemaPodologia.model.Pessoa;
import br.com.guedes.sistemaPodologia.util.IntegrationException;

/**
 * 
 * @author Guedes
 *
 */
public class ClienteDaoImpl extends HibernateDaoSupport implements ClienteDao {

	private static final Logger LOGGER = Logger.getLogger(ClienteDaoImpl.class);
	
	@Autowired  
    private SessionFactory sessionFactory;
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.dao.ClienteDao#obterPorId(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public Paciente obterPorId(final Integer pesCodigo) throws IntegrationException {
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("from Paciente ");
			hql.append(" where pessoa.pesCodigo = " + pesCodigo);
			
			ArrayList<Paciente> lista = (ArrayList<Paciente>) getHibernateTemplate().find(hql.toString());
			return lista.get(0);
		} catch (Exception e) {
			LOGGER.error(e);
			throw new IntegrationException("Erro ao obter dados da Cliente.");
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.dao.ClienteDao#pesquisar(br.com.guedes.sistemaPodologia.model.Paciente)
	 */
	@SuppressWarnings("unchecked")
	public List<Paciente> pesquisar(final Paciente paciente) throws IntegrationException {
		try {
			LOGGER.info("Pesquisando Paciente...");
			StringBuilder sql = new StringBuilder();
			
			sql.append("select pe.pes_codigo, pe.pes_nome, pa.pac_codigo ");
			sql.append("from tb_pessoa pe, tb_paciente pa ");
			sql.append("where pe.pes_codigo = pa.pes_codigo  ");
			if (paciente != null && paciente.getPessoa().getPesNome() != null) {
				sql.append("and upper(pe.pes_nome) like upper('%" + paciente.getPessoa().getPesNome().trim() + "%') ");
			}
			sql.append("order by pe.pes_nome ");
			
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
			List<Paciente> listaRetorno = new ArrayList<Paciente>();
			List<Object[]> entitys = query.list();
			for (Object[] entity : entitys) {
				Paciente pacienteRetorno = new Paciente();
				pacienteRetorno.setPessoa(new Pessoa());
				pacienteRetorno.getPessoa().setPesCodigo((Integer) entity[0]);
				pacienteRetorno.getPessoa().setPesNome((String) entity[1]);
				pacienteRetorno.setPacCodigo((Integer) entity[2]);
				listaRetorno.add(pacienteRetorno);
			}
			return listaRetorno;
		} catch (Exception e) {
			LOGGER.error("Erro ao pesquisar Cliente.", e);
			throw new IntegrationException("Erro ao pesquisar Cliente.", e);
		}
	}	
}
