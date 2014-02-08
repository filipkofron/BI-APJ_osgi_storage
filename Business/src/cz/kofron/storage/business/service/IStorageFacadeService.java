package cz.kofron.storage.business.service;

import java.util.ArrayList;

import cz.kofron.storage.model.entity.Item;
import cz.kofron.storage.model.entity.ItemGroup;


public interface IStorageFacadeService
{
	public ItemGroup addItemGroup(String name, String description);
	public boolean removeItemGroup(ItemGroup itemGroup);
	public boolean updateItemGroup(ItemGroup itemGroup);
	
	public ArrayList<ItemGroup> getItemGroups();
	

	public Item addItem(long timeAdded, String info, int groupId, int addedBy);
	public boolean removeItem(Item item);
	public boolean updateItem(Item item);
	
	public ArrayList<Item> getItems(int groupId);
	

	public boolean login(String username, String password);
}
