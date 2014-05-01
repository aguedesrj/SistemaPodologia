package br.com.guedes.sistemaPodologia.dao;

import java.util.List;

import br.com.guedes.sistemaPodologia.model.Profissional;
import br.com.guedes.sistemaPodologia.util.IntegrationException;


public interface ProfissionalDao {

	/**
	 * Buscar todos os dados do Profissional.
	 * 
	 * @param prfCodigo Integer
	 * @return Profissional
	 * @throws IntegrationException
	 */
	public Profissional obterPorId(final Integer prfCodigo) throws IntegrationException;
	
	/**
	 * Pesquisar por critérios.
	 * 
	 * @param Profissional Profissional
	 * @return List<Profissional>
	 * @throws IntegrationException
	 */
	public List<Profissional> pesquisar(final Profissional profissional) throws IntegrationException;
}
