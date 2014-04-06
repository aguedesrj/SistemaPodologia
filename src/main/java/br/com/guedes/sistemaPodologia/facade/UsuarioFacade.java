package br.com.guedes.sistemaPodologia.facade;

import br.com.guedes.sistemaPodologia.model.Usuario;
import br.com.guedes.sistemaPodologia.util.BusinessException;
import br.com.guedes.sistemaPodologia.util.IntegrationException;

public interface UsuarioFacade {

	/**
	 * Efetuar Login do Usuário.
	 * 
	 * @param login String
	 * @param senha String
	 * @return Usuario
	 * @throws BusinessException
	 * @throws IntegrationException
	 */
	public Usuario efetuarLogin(final String login, final String senha) throws BusinessException, IntegrationException;
}
