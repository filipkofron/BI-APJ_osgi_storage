package cz.kofron.storage.business.service.impl;

import java.util.ArrayList;

import cz.kofron.storage.business.service.IStorageFacadeService;
import cz.kofron.storage.integration.service.DAOFactoryService;
import cz.kofron.storage.model.entity.Item;
import cz.kofron.storage.model.entity.ItemGroup;
import cz.kofron.storage.model.entity.User;


public class DefaultStorageFacadeService implements IStorageFacadeService
{

	@Override
	public ItemGroup addItemGroup(String name, String description)
	{
		return DAOFactoryService.getInstance().getItemGroupDAO().addItemGroup(name, description);
	}

	@Override
	public boolean removeItemGroup(ItemGroup itemGroup)
	{
		return DAOFactoryService.getInstance().getItemGroupDAO().removeItemGroup(itemGroup);
	}

	@Override
	public boolean updateItemGroup(ItemGroup itemGroup)
	{
		return DAOFactoryService.getInstance().getItemGroupDAO().updateItemGroup(itemGroup);
	}

	@Override
	public ArrayList<ItemGroup> getItemGroups()
	{
		return DAOFactoryService.getInstance().getItemGroupDAO().getItemGroups();
	}

	@Override
	public Item addItem(long timeAdded, String info, int groupId, int addedBy)
	{
		return DAOFactoryService.getInstance().getItemDAO().addItem(timeAdded, info, groupId, addedBy);
	}

	@Override
	public boolean removeItem(Item item)
	{
		return DAOFactoryService.getInstance().getItemDAO().removeItem(item);
	}

	@Override
	public boolean updateItem(Item item)
	{
		return DAOFactoryService.getInstance().getItemDAO().updateItem(item);
	}

	@Override
	public ArrayList<Item> getItems(int groupId)
	{
		return DAOFactoryService.getInstance().getItemDAO().getItems(groupId);
	}

	@Override
	public boolean login(String username, String password)
	{
		User user =  DAOFactoryService.getInstance().getUserDAO().getUserByName(username);
		if(user == null)
		{
			return false;
		}
		return user.checkPassword(password);
	}

}
