package br.com.guedes.sistemaPodologia.facade;

import java.util.List;

import br.com.guedes.sistemaPodologia.model.Tratamento;
import br.com.guedes.sistemaPodologia.util.BusinessException;
import br.com.guedes.sistemaPodologia.util.IntegrationException;

public interface TratamentoFacade {

	/**
	 * Pesquisar por critérios.
	 * 
	 * @param tratamento Tratamento
	 * @return List<Tratamento>
	 * @throws IntegrationException
	 */
	public List<Tratamento> pesquisarPorCriterios(final Tratamento tratamento) throws IntegrationException;
	
	/**
	 * Salvar Cliente.
	 * 
	 * @param tratamento Tratamento
	 * @throws IntegrationException
	 * @throws BusinessException
	 */
	public void salvar(final Tratamento tratamento) throws IntegrationException, BusinessException;
	
	/**
	 * 
	 * @param tratamento Tratamento
	 * @return Pessoa
	 * @throws IntegrationException
	 */
	public Tratamento obterPorId(Tratamento tratamento) throws IntegrationException;
}
