package br.com.guedes.sistemaPodologia.dao;

import java.util.List;

import br.com.guedes.sistemaPodologia.model.Tratamento;
import br.com.guedes.sistemaPodologia.util.IntegrationException;


public interface TratamentoDao {

	/**
	 * Pesquisar por critérios.
	 * 
	 * @param tratamento Tratamento
	 * @return List<Tratamento>
	 * @throws IntegrationException
	 */
	public List<Tratamento> pesquisarPorCriterios(final Tratamento tratamento) throws IntegrationException;
}
