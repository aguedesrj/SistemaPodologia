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
public class ClienteDaoImpl extends HibernateDaoSupport implements ClienteDao {

	private static final Logger LOGGER = Logger.getLogger(ClienteDaoImpl.class);
	
	@Autowired  
    private SessionFactory sessionFactory;
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.dao.ClienteDao#pesquisarPorCriterios(br.com.guedes.sistemaPodologia.model.Pessoa)
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
			LOGGER.error("Erro ao pesquisar Cliente.", e);
			throw new IntegrationException("Erro ao pesquisar Cliente.", e);
		}
	}
	
/*
	public List<ItensVenda> obterVendasPorPeriodo(final String dataInicio, final String dataFim) throws BusinessException, IntegrationException {
		try {
			StringBuilder sql = new StringBuilder();
			
			sql.append("select count(v.ven_codigo), ");
			sql.append("       extract(day from v.ven_datafim)||'/'||extract(month from v.ven_datafim)||'/'||extract(year from v.ven_datafim) || ' 00:00:00' ");
			sql.append("  from tbl_venda v ");
			sql.append(" where v.ven_datafim between '" + dataInicio.replace('/', '.') + "' and '" + dataFim.replace('/', '.') + "' ");
			sql.append("group by extract(day from v.ven_datafim)||'/'||extract(month from v.ven_datafim)||'/'||extract(year from v.ven_datafim) ");
			
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
			if (query == null || query.list().isEmpty()) {
				throw new BusinessException("Nenhuma venda encontrada.");
			}
			
			List<ItensVenda> listaRetorno = new ArrayList<ItensVenda>();
			List<Object[]> entitys = query.list();
			for (Object[] entity : entitys) {
				ItensVenda itensVenda = new ItensVenda();
				Venda venda = new Venda();
				
				// seta valores.
				itensVenda.setItvQuantidade((Integer) entity[0]);
				venda.setVenDataFim(Util.converterStringParaCalendar((String) entity[1]));
				itensVenda.setVenda(venda);
				
				listaRetorno.add(itensVenda);
			}
			return listaRetorno;
		} catch (Exception e) {
			LOGGER.error("Erro ao obter vendas por período.", e);
			if (e instanceof BusinessException) {
				throw new BusinessException(e.getMessage());
			}
			throw new IntegrationException("Erro ao obter vendas por período.", e);
		}
	}	
 */
	
}
