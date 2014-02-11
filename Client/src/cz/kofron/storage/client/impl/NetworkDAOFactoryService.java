package cz.kofron.storage.client.impl;

import cz.kofron.storage.client.net.Client;
import cz.kofron.storage.integration.dao.ItemDAO;
import cz.kofron.storage.integration.dao.ItemGroupDAO;
import cz.kofron.storage.integration.dao.UserDAO;
import cz.kofron.storage.integration.service.DAOFactoryService;

public class NetworkDAOFactoryService extends DAOFactoryService
{
	private ItemDAO itemDAO;
	private ItemGroupDAO itemGroupDAO;
	private UserDAO userDAO;
	
	private Client client;
	
	public NetworkDAOFactoryService(Client client)
	{
		this.client = client;
	}

	@Override
	public ItemDAO getItemDAO()
	{
		if(itemDAO == null)
		{
			itemDAO = new NetItemDAOImpl(client);
		}
		
		return itemDAO;
	}

	@Override
	public ItemGroupDAO getItemGroupDAO()
	{
		if(itemGroupDAO == null)
		{
			itemGroupDAO = new NetItemGroupDAOImpl(client);
		}
		
		return itemGroupDAO;
	}

	@Override
	public UserDAO getUserDAO()
	{
		if(userDAO == null)
		{
			userDAO = new NetUserDAOImpl(client);
		}
		
		return userDAO;
	}
}
