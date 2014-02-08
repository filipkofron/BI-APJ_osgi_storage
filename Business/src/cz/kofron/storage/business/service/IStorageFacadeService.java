package cz.kofron.storage.business.service;

import cz.kofron.storage.model.entity.Item;
import cz.kofron.storage.model.entity.ItemGroup;
import cz.kofron.storage.model.entity.User;


public interface IStorageFacadeService
{
	public void addItemGroup(ItemGroup itemGroup);
	public void removeItemGroup(ItemGroup itemGroup);
	public void updateItemGroup(ItemGroup itemGroup);
	
	public void getItemGroups();
	

	public void addItem(Item item);
	public void removeItem(Item item);
	public void updateItem(Item item);
	
	public void getItems(int groupId);
	

	public void login(User user);
}
