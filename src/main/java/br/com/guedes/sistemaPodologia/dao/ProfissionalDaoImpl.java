package br.com.guedes.sistemaPodologia.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.com.guedes.sistemaPodologia.model.Pessoa;
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
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.dao.ProfissionalDao#obterPorId(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public Profissional obterPorId(final Integer prfCodigo) throws IntegrationException {
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("from Profissional where prfCodigo = " + prfCodigo);
			
			List<Profissional> listaRetorno = (ArrayList<Profissional>) getHibernateTemplate().find(hql.toString());
			if (listaRetorno.isEmpty()) {
				return null;
			}
			return listaRetorno.get(0);
		} catch (Exception e) {
			LOGGER.error("Erro ao obter por id o Profissional.", e);
			throw new IntegrationException("Erro ao obter por id o Profissional.", e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.dao.ProfissionalDao#pesquisar(br.com.guedes.sistemaPodologia.model.Profissional)
	 */
	@SuppressWarnings("unchecked")
	public List<Profissional> pesquisar(final Profissional profissional) throws IntegrationException {
		try {
			LOGGER.info("Pesquisando Profissional...");
			StringBuilder sql = new StringBuilder();
			
			sql.append("select pe.pes_codigo, pe.pes_nome, pr.prf_codigo ");
			sql.append("from tb_pessoa pe, tb_profissional pr ");
			sql.append("where pe.pes_codigo = pr.pes_codigo  ");
//			if (profissional != null && profissional.getPessoa().getPesNome() != null) {
//				sql.append("and upper(pe.pes_nome) like upper('%" + profissional.getPessoa().getPesNome().trim() + "%') ");
//			}
			sql.append("order by pe.pes_nome ");
			
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
			List<Profissional> listaRetorno = new ArrayList<Profissional>();
			List<Object[]> entitys = query.list();
			for (Object[] entity : entitys) {
				Profissional profissionalRetorno = new Profissional();
				profissionalRetorno.setPessoa(new Pessoa());
				profissionalRetorno.getPessoa().setPesCodigo((Integer) entity[0]);
				profissionalRetorno.getPessoa().setPesNome((String) entity[1]);
				profissionalRetorno.setPrfCodigo((Integer) entity[2]);
				listaRetorno.add(profissionalRetorno);
			}
			return listaRetorno;
		} catch (Exception e) {
			LOGGER.error("Erro ao pesquisar Profissional.", e);
			throw new IntegrationException("Erro ao pesquisar Profissional.", e);
		}
	}
}
