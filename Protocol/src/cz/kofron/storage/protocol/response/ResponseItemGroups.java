package cz.kofron.storage.protocol.response;

import java.util.ArrayList;

import cz.kofron.storage.model.entity.ItemGroup;

public class ResponseItemGroups extends Response
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6360606270984704719L;
	private ArrayList<ItemGroup> itemGroups;
	
	public ResponseItemGroups(ArrayList<ItemGroup> itemGroups)
	{
		this.itemGroups = itemGroups;
	}

	public ArrayList<ItemGroup> getItemGroups()
	{
		return itemGroups;
	}
}
