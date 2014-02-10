package cz.kofron.storage.integration.service.impl;

import java.util.ArrayList;
import java.util.Comparator;

import cz.kofron.storage.integration.dao.ItemGroupDAO;
import cz.kofron.storage.integration.service.DAOFactoryService;
import cz.kofron.storage.model.entity.Item;
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

	public ItemGroupDAOImpl()
	{
		itemGroups.add(new ItemGroup(getUniqId(), "A group", "this is a simple group description"));
		itemGroups.add(new ItemGroup(getUniqId(), "Another group", "this is another simple group description"));
	}

	@Override
	public ItemGroup addItemGroup(String name, String description)
	{
		for (ItemGroup it : itemGroups)
		{
			if (it.getName().equals(name))
			{
				return null;
			}
		}
		ItemGroup group = new ItemGroup(getUniqId(), name, description);
		itemGroups.add(group);
		return group;
	}

	@Override
	public boolean removeItemGroup(ItemGroup itemGroup)
	{
		ItemGroup toRemove = null;
		for (ItemGroup it : itemGroups)
		{
			if (it.getId() == itemGroup.getId())
			{
				toRemove = it;
				break;
			}
		}
		if (toRemove != null)
		{
			ArrayList<Item> items = DAOFactoryService.getInstance().getItemDAO().getItems(toRemove.getId());
			for (Item item : items)
			{
				DAOFactoryService.getInstance().getItemDAO().removeItem(item);
			}
			itemGroups.remove(toRemove);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateItemGroup(ItemGroup itemGroup)
	{
		ItemGroup toUpdate = null;
		for (ItemGroup it : itemGroups)
		{
			if (it.getId() == itemGroup.getId())
			{
				toUpdate = it;
				break;
			}
		}
		if (toUpdate != null)
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
		ArrayList<ItemGroup> ret = new ArrayList<ItemGroup>(itemGroups);

		ret.sort(new Comparator<ItemGroup>()
		{
			public int compare(ItemGroup a, ItemGroup b)
			{
				return a.getName().compareTo(b.getName());
			};
		});
		return ret;
	}

}
