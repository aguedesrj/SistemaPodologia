package br.com.guedes.sistemaPodologia.dao;

import java.util.List;

import br.com.guedes.sistemaPodologia.model.Pessoa;
import br.com.guedes.sistemaPodologia.util.IntegrationException;


public interface ClienteDao {

	/**
	 * Pesquisar por critérios.
	 * 
	 * @param pessoa Pessoa
	 * @return List<Pessoa>
	 * @throws IntegrationException
	 */
	public List<Pessoa> pesquisarPorCriterios(final Pessoa pessoa) throws IntegrationException;
}
