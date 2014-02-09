package cz.kofron.storage.business.facade;

import java.util.ArrayList;

import cz.kofron.storage.model.entity.Item;
import cz.kofron.storage.model.entity.ItemGroup;

public abstract class StorageFacade
{
	public abstract ItemGroup addItemGroup(String name, String description);

	public abstract boolean removeItemGroup(ItemGroup itemGroup);

	public abstract boolean updateItemGroup(ItemGroup itemGroup);

	public abstract ArrayList<ItemGroup> getItemGroups();

	public abstract Item addItem(long timeAdded, String info, int groupId, int addedBy);

	public abstract boolean removeItem(Item item);

	public abstract boolean updateItem(Item item);

	public abstract ArrayList<Item> getItems(int groupId);

	public abstract boolean login(String username, String password);
}
