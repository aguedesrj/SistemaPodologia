package br.com.guedes.sistemaPodologia.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 
 * @author Guedes
 *
 */
public class ClienteDaoImpl extends HibernateDaoSupport implements ClienteDao {

	private static final Logger LOGGER = Logger.getLogger(ClienteDaoImpl.class);
	
	@Autowired  
    private SessionFactory sessionFactory;
}
