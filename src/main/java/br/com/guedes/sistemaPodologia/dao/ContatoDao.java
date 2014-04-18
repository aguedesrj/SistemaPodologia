package br.com.guedes.sistemaPodologia.dao;

import java.util.List;

import br.com.guedes.sistemaPodologia.model.Contato;
import br.com.guedes.sistemaPodologia.model.Pessoa;
import br.com.guedes.sistemaPodologia.model.TipoContato;
import br.com.guedes.sistemaPodologia.util.IntegrationException;

public interface ContatoDao {

	/**
	 * Lista dos tipo de contatos.
	 * 
	 * @return List<TipoContato>
	 * @throws IntegrationException
	 */
	public List<TipoContato> listaTipoContatos() throws IntegrationException;
	
	/**
	 * Lista de contatos por Pessoa.
	 * 
	 * @param pessoa Pessoa
	 * @return List<Contato>
	 * @throws IntegrationException
	 */
	public List<Contato> listaContatosPorPessoa(final Pessoa pessoa) throws IntegrationException;
}
