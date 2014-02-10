package cz.kofron.storage.integration.service.impl;

import java.util.ArrayList;

import cz.kofron.storage.integration.dao.ItemDAO;
import cz.kofron.storage.model.entity.Item;

public class ItemDAOImpl implements ItemDAO
{

	private static Integer id = new Integer(0);

	public static int getUniqId()
	{
		synchronized (id)
		{
			return id++;
		}
	}

	private ArrayList<Item> items = new ArrayList<Item>();

	public ItemDAOImpl()
	{
		items.add(new Item(getUniqId(), System.currentTimeMillis(), "none", 0,
				0));
		items.add(new Item(getUniqId(), System.currentTimeMillis(), "neco", 0,
				0));
	}

	@Override
	public Item addItem(long timeAdded, String info, int groupId, int addedBy)
	{
		Item item = new Item(getUniqId(), timeAdded, info, groupId, addedBy);
		items.add(item);
		return item;
	}

	@Override
	public boolean removeItem(Item item)
	{
		Item toRemove = null;
		for (Item it : items)
		{
			if (it.getId() == item.getId())
			{
				toRemove = it;
				break;
			}
		}
		if (toRemove != null)
		{
			items.remove(toRemove);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateItem(Item item)
	{
		Item toUpdate = null;
		for (Item it : items)
		{
			if (it.getId() == item.getId())
			{
				toUpdate = it;
				break;
			}
		}
		if (toUpdate != null)
		{
			items.remove(toUpdate);
			items.add(item);
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Item> getItems(int groupId)
	{
		return new ArrayList<Item>(items);
	}
}
