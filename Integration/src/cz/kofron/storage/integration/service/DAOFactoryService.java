package cz.kofron.storage.integration.service;

import java.util.logging.Logger;

import cz.kofron.storage.integration.dao.ItemDAO;
import cz.kofron.storage.integration.dao.ItemGroupDAO;
import cz.kofron.storage.integration.dao.UserDAO;
import cz.kofron.storage.integration.service.impl.DefaultDAOFactoryService;

public abstract class DAOFactoryService
{
	private final static Logger log = Logger.getLogger(DAOFactoryService.class.getName());
	
	private static DAOFactoryService instance;
	
	public abstract ItemDAO getItemDAO();
	public abstract ItemGroupDAO getItemGroupDAO();
	public abstract UserDAO getUserDAO();
	
	private static DAOFactoryService retrieveService()
	{
		
		return null;
	}
	
	public static DAOFactoryService getInstance()
	{
		if(instance == null)
		{
			instance = retrieveService();
		}
		
		if(instance == null)
		{
			log.warning("DAO implementation service not found, using default!");
			instance = new DefaultDAOFactoryService();
		}
		
		return instance;
	}
}
