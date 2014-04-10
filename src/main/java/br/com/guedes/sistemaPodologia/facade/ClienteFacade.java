package br.com.guedes.sistemaPodologia.facade;

import java.util.List;

import br.com.guedes.sistemaPodologia.model.TipoContato;
import br.com.guedes.sistemaPodologia.util.IntegrationException;

public interface ClienteFacade {

	/**
	 * Obter todos os tipo de contatos.
	 * 
	 * @return List<TipoContato>
	 * @throws IntegrationException
	 */
	public List<TipoContato> listaTipoContatos() throws IntegrationException;
}
