package cz.kofron.storage.client.impl;

import java.util.ArrayList;

import cz.kofron.storage.client.net.Client;
import cz.kofron.storage.integration.dao.ItemGroupDAO;
import cz.kofron.storage.model.entity.ItemGroup;
import cz.kofron.storage.protocol.request.RequestAddItemGroup;
import cz.kofron.storage.protocol.request.RequestGetItemGroups;
import cz.kofron.storage.protocol.request.RequestItemGroup;
import cz.kofron.storage.protocol.response.ResponseBoolean;
import cz.kofron.storage.protocol.response.ResponseItemGroups;

public class NetItemGroupDAOImpl implements ItemGroupDAO
{
	private Client client;

	public NetItemGroupDAOImpl(Client client)
	{
		this.client = client;
	}

	@Override
	public ItemGroup addItemGroup(String name, String description)
	{
		ResponseItemGroups itemGroups = (ResponseItemGroups) client.communicate(new RequestAddItemGroup(name, description));
		if(itemGroups.getItemGroups().size() == 1)
		{
			return itemGroups.getItemGroups().get(0);
		}
		return null;
	}

	@Override
	public boolean removeItemGroup(ItemGroup itemGroup)
	{
		ResponseBoolean responseBoolean = (ResponseBoolean) client.communicate(new RequestItemGroup(itemGroup, RequestItemGroup.Operation.REMOVE));
		
		return responseBoolean.getValue();
	}

	@Override
	public boolean updateItemGroup(ItemGroup itemGroup)
	{
		ResponseBoolean responseBoolean = (ResponseBoolean) client.communicate(new RequestItemGroup(itemGroup, RequestItemGroup.Operation.UPDATE));
		
		return responseBoolean.getValue();
	}

	@Override
	public ArrayList<ItemGroup> getItemGroups()
	{
		ResponseItemGroups itemGroups = (ResponseItemGroups) client.communicate(new RequestGetItemGroups());
		
		return itemGroups.getItemGroups();
	}
}
