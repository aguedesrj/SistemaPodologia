package br.com.guedes.sistemaPodologia.dao;

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
}
