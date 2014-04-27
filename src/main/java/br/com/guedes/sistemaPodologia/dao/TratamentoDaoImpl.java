package br.com.guedes.sistemaPodologia.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
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
			LOGGER.info("Pesquisando Tratamento...");
			StringBuilder sql = new StringBuilder();
			
			sql.append("select t.tra_codigo, t.tra_descricao from tb_tratamento t ");
			sql.append("where upper(t.tra_descricao) like upper('%" + tratamento.getTraDescricao().trim() + "%') ");
			sql.append("order by t.tra_descricao ");
			
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
			List<Tratamento> listaRetorno = new ArrayList<Tratamento>();
			List<Object[]> entitys = query.list();
			for (Object[] entity : entitys) {
				Tratamento tratamentoRetorno = new Tratamento();
				tratamentoRetorno.setTraCodigo((Integer) entity[0]);
				tratamentoRetorno.setTraDescricao((String) entity[1]);
				listaRetorno.add(tratamentoRetorno);
			}
			return listaRetorno;
		} catch (Exception e) {
			LOGGER.error("Erro ao pesquisar Tratamento.", e);
			throw new IntegrationException("Erro ao pesquisar Tratamento.", e);
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
