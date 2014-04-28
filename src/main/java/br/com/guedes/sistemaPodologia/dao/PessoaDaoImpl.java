package br.com.guedes.sistemaPodologia.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
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
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.dao.PessoaDao#obterPorId(java.lang.Integer)
	 */
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
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.dao.PessoaDao#pesquisarPorCriterios(br.com.guedes.sistemaPodologia.model.Pessoa)
	 */
	@SuppressWarnings("unchecked")
	public List<Pessoa> pesquisarPorCriterios(final Pessoa pessoa) throws IntegrationException {
		try {
			LOGGER.info("Pesquisando Pessoa...");
			StringBuilder sql = new StringBuilder();
			
			sql.append("select p.pes_codigo, p.pes_nome from tb_pessoa p ");
			sql.append("where upper(p.pes_nome) like upper('%" + pessoa.getPesNome().trim() + "%') ");
			sql.append("order by p.pes_nome ");
			
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
			List<Pessoa> listaRetorno = new ArrayList<Pessoa>();
			List<Object[]> entitys = query.list();
			for (Object[] entity : entitys) {
				Pessoa pessoaRetorno = new Pessoa();
				pessoaRetorno.setPesCodigo((Integer) entity[0]);
				pessoaRetorno.setPesNome((String) entity[1]);
				listaRetorno.add(pessoaRetorno);
			}
			return listaRetorno;
		} catch (Exception e) {
			LOGGER.error("Erro ao pesquisar Pessoa.", e);
			throw new IntegrationException("Erro ao pesquisar Pessoa.", e);
		}
	}
}
