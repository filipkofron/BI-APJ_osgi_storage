package cz.kofron.storage.client.impl;

import java.util.ArrayList;

import cz.kofron.storage.client.net.Client;
import cz.kofron.storage.integration.dao.ItemDAO;
import cz.kofron.storage.model.entity.Item;
import cz.kofron.storage.protocol.request.RequestAddItem;
import cz.kofron.storage.protocol.request.RequestGetItems;
import cz.kofron.storage.protocol.request.RequestItem;
import cz.kofron.storage.protocol.response.ResponseBoolean;
import cz.kofron.storage.protocol.response.ResponseItems;

public class NetItemDAOImpl implements ItemDAO
{
	private Client client;

	public NetItemDAOImpl(Client client)
	{
		this.client = client;
	}

	@Override
	public Item addItem(long timeAdded, String info, int groupId, int addedBy)
	{
		ResponseItems items = (ResponseItems) client.communicate(new RequestAddItem(timeAdded, info, groupId, addedBy));
		if(items.getItems().size() == 1)
		{
			return items.getItems().get(0);
		}
		return null;
	}

	@Override
	public boolean removeItem(Item item)
	{
		ResponseBoolean res = (ResponseBoolean) client.communicate(new RequestItem(item, RequestItem.Operation.REMOVE));
		return res.getValue();
	}

	@Override
	public boolean updateItem(Item item)
	{
		ResponseBoolean res = (ResponseBoolean) client.communicate(new RequestItem(item, RequestItem.Operation.UPDATE));
		return res.getValue();
	}

	@Override
	public ArrayList<Item> getItems(int groupId)
	{
		ResponseItems items = (ResponseItems) client.communicate(new RequestGetItems(groupId));
		return items.getItems();
	}
}
