package br.com.guedes.sistemaPodologia.dao;

import java.util.List;

import br.com.guedes.sistemaPodologia.model.Estado;
import br.com.guedes.sistemaPodologia.util.IntegrationException;

public interface EnderecoDao {

	/**
	 * Lista dos estados.
	 * 
	 * @return List<Estado>
	 * @throws IntegrationException
	 */
	public List<Estado> listaEstados() throws IntegrationException;
}
