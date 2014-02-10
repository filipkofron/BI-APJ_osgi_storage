package cz.kofron.storage.integration.dao;

import java.util.ArrayList;

import cz.kofron.storage.model.entity.ItemGroup;

public interface ItemGroupDAO
{
	public ItemGroup addItemGroup(String name, String description);

	public boolean removeItemGroup(ItemGroup itemGroup);

	public boolean updateItemGroup(ItemGroup itemGroup);

	public ArrayList<ItemGroup> getItemGroups();
}
