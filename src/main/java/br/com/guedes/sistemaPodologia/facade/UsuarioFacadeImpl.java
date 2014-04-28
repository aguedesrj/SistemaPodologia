package br.com.guedes.sistemaPodologia.facade;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.guedes.sistemaPodologia.dao.UsuarioDao;
import br.com.guedes.sistemaPodologia.model.Usuario;
import br.com.guedes.sistemaPodologia.util.BusinessException;
import br.com.guedes.sistemaPodologia.util.IntegrationException;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
public class UsuarioFacadeImpl implements UsuarioFacade {
	
	private static final Logger LOGGER = Logger.getLogger(UsuarioFacadeImpl.class);
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired  
    private SessionFactory sessionFactory;	

	public Usuario efetuarLogin(final String login, final String senha) throws BusinessException, IntegrationException {
		LOGGER.info("Efetuar login.");
		return usuarioDao.efetuarLogin(login, senha);
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
