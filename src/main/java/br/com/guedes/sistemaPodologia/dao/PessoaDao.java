package br.com.guedes.sistemaPodologia.dao;

import java.util.List;

import br.com.guedes.sistemaPodologia.model.Pessoa;
import br.com.guedes.sistemaPodologia.util.IntegrationException;


public interface PessoaDao {

	/**
	 * Obter Pessoa por id.
	 * 
	 * @param pesCodigo Integer
	 * @return Pessoa
	 * @throws IntegrationException
	 */
	public Pessoa obterPorId(final Integer pesCodigo) throws IntegrationException;
	
	/**
	 * Pesquisar por critérios.
	 * 
	 * @param pessoa Pessoa
	 * @return List<Pessoa>
	 * @throws IntegrationException
	 */
	public List<Pessoa> pesquisarPorCriterios(final Pessoa pessoa) throws IntegrationException;
}
