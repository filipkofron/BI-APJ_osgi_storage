package cz.kofron.storage.protocol.response;

import java.io.Serializable;
import java.util.ArrayList;

import cz.kofron.storage.model.entity.Item;

public class ResponseItems extends Response implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2756638475764035595L;
	private ArrayList<Item> items;
	
	public ResponseItems(ArrayList<Item> items)
	{
		this.items = items;
	}

	public ArrayList<Item> getItems()
	{
		return items;
	}
}
