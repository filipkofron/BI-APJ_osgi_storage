package cz.kofron.storage.derby.service.impl;

import java.sql.Connection;
import java.util.ArrayList;

import cz.kofron.storage.integration.dao.ItemDAO;
import cz.kofron.storage.model.entity.Item;

public class DerbyItemDAOImpl implements ItemDAO
{

	
	
	public DerbyItemDAOImpl(Connection derbyConnection)
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public Item addItem(long timeAdded, String info, int groupId, int addedBy)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeItem(Item item)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateItem(Item item)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Item> getItems(int groupId)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
