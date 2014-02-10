package cz.kofron.storage.integration.service.impl;

import cz.kofron.storage.integration.dao.ItemDAO;
import cz.kofron.storage.integration.dao.ItemGroupDAO;
import cz.kofron.storage.integration.dao.UserDAO;
import cz.kofron.storage.integration.service.DAOFactoryService;

public class DefaultDAOFactoryService extends DAOFactoryService
{
	private ItemDAO itemDAO;
	private ItemGroupDAO itemGroupDAO;
	private UserDAO userDAO;

	@Override
	public ItemDAO getItemDAO()
	{
		if (itemDAO == null)
		{
			itemDAO = new ItemDAOImpl();
		}

		return itemDAO;
	}

	@Override
	public ItemGroupDAO getItemGroupDAO()
	{
		if (itemGroupDAO == null)
		{
			itemGroupDAO = new ItemGroupDAOImpl();
		}

		return itemGroupDAO;
	}

	@Override
	public UserDAO getUserDAO()
	{
		if (userDAO == null)
		{
			userDAO = new UserDAOImpl();
		}

		return userDAO;
	}

}
