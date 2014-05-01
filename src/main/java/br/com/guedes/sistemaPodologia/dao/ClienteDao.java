package br.com.guedes.sistemaPodologia.dao;

import java.util.List;

import br.com.guedes.sistemaPodologia.model.Paciente;
import br.com.guedes.sistemaPodologia.util.IntegrationException;


public interface ClienteDao {

	/**
	 * Obter todos os dados do Cliente pelo seu ID.
	 * 
	 * @param pesCodigo Integer
	 * @return Paciente
	 * @throws IntegrationException
	 */
	public Paciente obterPorId(final Integer pesCodigo) throws IntegrationException;
	
	/**
	 * Pesquisar por critérios o Cliente.
	 * 
	 * @param paciente Paciente
	 * @return List<Paciente>
	 * @throws IntegrationException
	 */
	public List<Paciente> pesquisar(final Paciente paciente) throws IntegrationException;
}
