package cz.kofron.storage.control.ui;

import cz.kofron.storage.business.facade.StorageFacadeFactory;
import cz.kofron.storage.model.entity.Item;
import cz.kofron.storage.model.entity.ItemGroup;

public class EventController
{
	public static void onAddItemGroup(String name, String description, IBooleanCallBack booleanCallBack)
	{
		 booleanCallBack.call(StorageFacadeFactory.getInstance().addItemGroup(name, description) != null);
	}

	public static void onRemoveItemGroup(ItemGroup itemGroup, IBooleanCallBack booleanCallBack)
	{
		booleanCallBack.call(StorageFacadeFactory.getInstance().removeItemGroup(itemGroup));
	}

	public static void onUpdateItemGroup(ItemGroup itemGroup, IBooleanCallBack booleanCallBack)
	{
		booleanCallBack.call(StorageFacadeFactory.getInstance().updateItemGroup(itemGroup));
	}

	public static void onAddItem(long timeAdded, String info, int groupId, int addedBy, IBooleanCallBack booleanCallBack)
	{
		booleanCallBack.call(StorageFacadeFactory.getInstance().addItem(timeAdded, info, groupId, addedBy) != null);
	}

	public static void onRemoveItem(Item item, IBooleanCallBack booleanCallBack)
	{
		booleanCallBack.call(StorageFacadeFactory.getInstance().removeItem(item));
	}

	public static void onUpdateItem(Item item, IBooleanCallBack booleanCallBack)
	{
		booleanCallBack.call(StorageFacadeFactory.getInstance().updateItem(item));
	}

	public static void onLogin(String username, String password, IBooleanCallBack booleanCallBack)
	{
		booleanCallBack.call(StorageFacadeFactory.getInstance().login(username, password));
	}
}
