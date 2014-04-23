package br.com.guedes.sistemaPodologia.facade;

import java.util.List;

import br.com.guedes.sistemaPodologia.model.Contato;
import br.com.guedes.sistemaPodologia.model.Estado;
import br.com.guedes.sistemaPodologia.model.Paciente;
import br.com.guedes.sistemaPodologia.model.Pessoa;
import br.com.guedes.sistemaPodologia.model.TipoContato;
import br.com.guedes.sistemaPodologia.util.BusinessException;
import br.com.guedes.sistemaPodologia.util.IntegrationException;

public interface ClienteFacade {

	/**
	 * Obter todos os tipo de contatos.
	 * 
	 * @return List<TipoContato>
	 * @throws IntegrationException
	 */
	public List<TipoContato> listaTipoContatos() throws IntegrationException;
	
	/**
	 * Obter a lista de Estados.
	 * 
	 * @return List<Estado>
	 * @throws IntegrationException
	 */
	public List<Estado> listaEstados() throws IntegrationException;
	
	/**
	 * Pesquisar por critérios.
	 * 
	 * @param pessoa Pessoa
	 * @return List<Pessoa>
	 * @throws IntegrationException
	 */
	public List<Pessoa> pesquisarPorCriterios(final Pessoa pessoa) throws IntegrationException;
	
	/**
	 * Salvar Cliente.
	 * 
	 * @param pessoa Pessoa
	 * @param listaContatos List<Contato>
	 * @param paciente Paciente
	 * @throws IntegrationException
	 * @throws BusinessException
	 */
	public void salvar(final Pessoa pessoa, final List<Contato> listaContatos, final Paciente paciente) throws IntegrationException, BusinessException;
	
	/**
	 * 
	 * @param pessoa Pessoa
	 * @return Pessoa
	 * @throws IntegrationException
	 */
	public Pessoa obterPorId(Pessoa pessoa) throws IntegrationException;
}
