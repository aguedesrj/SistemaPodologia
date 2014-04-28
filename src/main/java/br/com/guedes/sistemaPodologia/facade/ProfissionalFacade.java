package br.com.guedes.sistemaPodologia.facade;

import java.util.List;

import br.com.guedes.sistemaPodologia.model.Estado;
import br.com.guedes.sistemaPodologia.model.Pessoa;
import br.com.guedes.sistemaPodologia.model.Profissional;
import br.com.guedes.sistemaPodologia.model.TipoContato;
import br.com.guedes.sistemaPodologia.util.BusinessException;
import br.com.guedes.sistemaPodologia.util.IntegrationException;

public interface ProfissionalFacade {

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
	 * Pesquisar por crit??rios.
	 * 
	 * @param profissional Profissional
	 * @return List<Profissional>
	 * @throws IntegrationException
	 */
	public List<Profissional> pesquisarPorCriterios(final Profissional profissional) throws IntegrationException;
	
	/**
	 * Salvar Profissional.
	 * 
	 * @param profissional Profissional
	 * @throws IntegrationException
	 * @throws BusinessException
	 */
	public void salvar(final Profissional profissional) throws IntegrationException, BusinessException;
	
	/**
	 * Obter Profissional por seu ID.
	 * 
	 * @param profissional Profissional
	 * @return Profissional
	 * @throws IntegrationException
	 */
	public Profissional obterPorId(final Profissional profissional) throws IntegrationException;
}
