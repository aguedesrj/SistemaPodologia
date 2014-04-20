package br.com.guedes.sistemaPodologia.dao;

import br.com.guedes.sistemaPodologia.model.Paciente;
import br.com.guedes.sistemaPodologia.util.IntegrationException;


public interface PacienteDao {

	/**
	 * Obter Paciente por idPessoa.
	 * 
	 * @param pesCodigo Integer
	 * @return Paciente
	 * @throws IntegrationException
	 */
	public Paciente obterPorIdPessoa(final Integer pesCodigo) throws IntegrationException;
}
