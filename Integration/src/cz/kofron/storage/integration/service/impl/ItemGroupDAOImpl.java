package cz.kofron.storage.integration.service.impl;

import java.util.ArrayList;

import cz.kofron.storage.integration.dao.ItemGroupDAO;
import cz.kofron.storage.model.entity.ItemGroup;

public class ItemGroupDAOImpl implements ItemGroupDAO
{
	private static Integer id = new Integer(0);

	public static int getUniqId()
	{
		synchronized (id)
		{
			return id++;
		}
	}
	
	private ArrayList<ItemGroup> itemGroups = new ArrayList<>();
	
	@Override
	public ItemGroup addItemGroup(String name, String description)
	{
		ItemGroup group = new ItemGroup(getUniqId(), name, description);
		itemGroups.add(group);
		return group;
	}

	@Override
	public boolean removeItemGroup(ItemGroup itemGroup)
	{
		ItemGroup toRemove = null;
		for(ItemGroup it : itemGroups)
		{
			if(it.getId() == itemGroup.getId())
			{
				toRemove = it;
				break;
			}
		}
		if(toRemove != null)
		{
			itemGroups.remove(toRemove);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateItemGroup(ItemGroup itemGroup)
	{
		ItemGroup toUpdate = null;
		for(ItemGroup it : itemGroups)
		{
			if(it.getId() == itemGroup.getId())
			{
				toUpdate = it;
				break;
			}
		}
		if(toUpdate != null)
		{
			itemGroups.remove(toUpdate);
			itemGroups.add(itemGroup);
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<ItemGroup> getItemGroups()
	{
		return new ArrayList<ItemGroup>(itemGroups);
	}

}
