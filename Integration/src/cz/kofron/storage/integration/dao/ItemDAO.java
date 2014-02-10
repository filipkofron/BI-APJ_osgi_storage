package cz.kofron.storage.integration.dao;

import java.util.ArrayList;

import cz.kofron.storage.model.entity.Item;

public interface ItemDAO
{
	public Item addItem(long timeAdded, String info, int groupId, int addedBy);

	public boolean removeItem(Item item);

	public boolean updateItem(Item item);

	public ArrayList<Item> getItems(int groupId);
}
