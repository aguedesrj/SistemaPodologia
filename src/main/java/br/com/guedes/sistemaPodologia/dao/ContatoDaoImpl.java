package br.com.guedes.sistemaPodologia.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import br.com.guedes.sistemaPodologia.model.Contato;
import br.com.guedes.sistemaPodologia.model.Pessoa;
import br.com.guedes.sistemaPodologia.model.TipoContato;
import br.com.guedes.sistemaPodologia.util.IntegrationException;

@Repository
public class ContatoDaoImpl extends HibernateDaoSupport implements ContatoDao {

	private static final Logger LOGGER = Logger.getLogger(ContatoDaoImpl.class);
	
	/*
	 * (non-Javadoc)
	 * @see br.com.sistemacomercial.guedes.dao.ContatoDao#listaTipoContatos()
	 */
	@SuppressWarnings("unchecked")
	public List<TipoContato> listaTipoContatos() throws IntegrationException {
		try {
			return getHibernateTemplate().findByValueBean(new StringBuilder("from TipoContato ").toString(), TipoContato.class);
		} catch (Exception e) {
			LOGGER.fatal("Erro ao buscar lista de tipos de contatos.", e);
			e.printStackTrace();
			throw new IntegrationException("Erro ao buscar lista de tipos de contatos.");
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemaPodologia.dao.ContatoDao#listaContatosPorPessoa(br.com.guedes.sistemaPodologia.model.Pessoa)
	 */
	@SuppressWarnings("unchecked")
	public List<Contato> listaContatosPorPessoa(final Pessoa pessoa) throws IntegrationException {
		try {
			return getHibernateTemplate().findByValueBean(new StringBuilder("from Contato where pessoa.pesCodigo = " + pessoa.getPesCodigo()).toString(), Contato.class);
		} catch (Exception e) {
			LOGGER.fatal("Erro ao buscar lista os contatos por pessoa.", e);
			e.printStackTrace();
			throw new IntegrationException("Erro ao buscar lista os contatos por pessoa.");
		}
	}
}
