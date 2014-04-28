package br.com.guedes.sistemaPodologia.dao;

import java.util.List;

import br.com.guedes.sistemaPodologia.model.Profissional;
import br.com.guedes.sistemaPodologia.util.IntegrationException;


public interface ProfissionalDao {

	/**
	 * Pesquisar por critérios.
	 * 
	 * @param Profissional Profissional
	 * @return List<Pessoa>
	 * @throws IntegrationException
	 */
	public List<Profissional> pesquisarPorCriterios(final Profissional profissional) throws IntegrationException;
}
