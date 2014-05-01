package br.com.guedes.sistemaPodologia.facade;

import java.util.List;

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
	 * @param paciente Paciente
	 * @return List<Paciente>
	 * @throws IntegrationException
	 */
	public List<Paciente> pesquisar(final Paciente paciente) throws IntegrationException;
	
	/**
	 * Salvar Cliente.
	 * 
	 * @param pessoa Pessoa
	 * @throws IntegrationException
	 * @throws BusinessException
	 */
	public void salvar(final Pessoa pessoa) throws IntegrationException, BusinessException;
	
	/**
	 * Obter todos os dados do Cliente pelo seu ID.
	 * 
	 * @param paciente Paciente
	 * @return Paciente
	 * @throws IntegrationException
	 */
	public Paciente obterPorId(Paciente paciente) throws IntegrationException;
}
