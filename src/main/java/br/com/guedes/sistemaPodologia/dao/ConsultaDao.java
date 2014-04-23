package br.com.guedes.sistemaPodologia.dao;

import br.com.guedes.sistemaPodologia.model.Consulta;
import br.com.guedes.sistemaPodologia.model.Paciente;
import br.com.guedes.sistemaPodologia.util.IntegrationException;


public interface ConsultaDao {

	/**
	 * Obter dados da última consulta.
	 * 
	 * @param paciente Paciente
	 * @return Consulta
	 * @throws IntegrationException
	 */
	public Consulta obterUltimaConsulta(final Paciente paciente) throws IntegrationException;
}
