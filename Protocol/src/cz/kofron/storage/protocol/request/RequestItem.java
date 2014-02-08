package cz.kofron.storage.protocol.request;

import java.io.Serializable;

import cz.kofron.storage.business.service.IStorageFacadeService;
import cz.kofron.storage.model.entity.Item;

public class RequestItem extends Request implements Serializable
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
	private Item item;
	private Operation type;

	public RequestItem(Item item, Operation type)
	{
		super();
		this.item = item;
		this.type = type;
	}

	public Item getItem()
	{
		return item;
	}
	
	public Operation getType()
	{
		return type;
	}

	@Override
	public void execute(IStorageFacadeService facade)
	{
		switch(type)
		{
			case ADD:
				facade.addItem(item);
				break;
			case REMOVE:
				facade.removeItem(item);
				break;
			case UPDATE:
				facade.updateItem(item);
				break;
		}
	}
}
