package cz.kofron.storage.derby.service.impl;

import java.sql.Connection;
import java.util.ArrayList;

import cz.kofron.storage.integration.dao.ItemGroupDAO;
import cz.kofron.storage.model.entity.ItemGroup;

public class DerbyItemGroupDAOImpl implements ItemGroupDAO
{

	public DerbyItemGroupDAOImpl(Connection derbyConnection)
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public ItemGroup addItemGroup(String name, String description)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeItemGroup(ItemGroup itemGroup)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateItemGroup(ItemGroup itemGroup)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<ItemGroup> getItemGroups()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
