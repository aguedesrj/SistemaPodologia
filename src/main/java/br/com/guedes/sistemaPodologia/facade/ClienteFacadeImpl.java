package br.com.guedes.sistemaPodologia.facade;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.guedes.sistemaPodologia.dao.ClienteDao;
import br.com.guedes.sistemaPodologia.model.TipoContato;
import br.com.guedes.sistemaPodologia.util.IntegrationException;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
public class ClienteFacadeImpl extends HibernateDaoSupport implements ClienteFacade {
	
	private static final Logger LOGGER = Logger.getLogger(ClienteFacadeImpl.class);

	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired  
    private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<TipoContato> listaTipoContatos() throws IntegrationException {
		LOGGER.info("Todos do tipo de contatos.");
		return getHibernateTemplate().find("from TipoContato");
	}	

	public ClienteDao getClienteDao() {
		return clienteDao;
	}

	public void setClienteDao(ClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}	
}
