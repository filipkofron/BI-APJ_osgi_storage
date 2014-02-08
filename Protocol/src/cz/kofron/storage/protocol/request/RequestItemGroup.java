package cz.kofron.storage.protocol.request;

import java.io.Serializable;

import cz.kofron.storage.business.service.IStorageFacadeService;
import cz.kofron.storage.model.entity.ItemGroup;

public class RequestItemGroup extends Request implements Serializable
{
	public enum Operation
	{
		ADD,
		REMOVE,
		UPDATE
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2119205834512986281L;
	private ItemGroup itemGroup;
	private Operation type;

	public RequestItemGroup(ItemGroup itemGroup, Operation type)
	{
		this.itemGroup = itemGroup;
		this.type = type;
	}

	public ItemGroup getItemGroup()
	{
		return itemGroup;
	}
	
	@Override
	public void execute(IStorageFacadeService facade)
	{
		switch(type)
		{
			case ADD:
				facade.addItemGroup(itemGroup);
				break;
			case REMOVE:
				facade.removeItemGroup(itemGroup);
				break;
			case UPDATE:
				facade.updateItemGroup(itemGroup);
				break;
		}
	}
}
