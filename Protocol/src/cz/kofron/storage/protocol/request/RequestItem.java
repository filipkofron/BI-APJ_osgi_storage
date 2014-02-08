package cz.kofron.storage.protocol.request;

import java.io.Serializable;

import cz.kofron.storage.business.service.IStorageFacadeService;
import cz.kofron.storage.model.entity.Item;
import cz.kofron.storage.protocol.response.ResponseBoolean;
import cz.kofron.storage.protocol.service.IResponseService;

public class RequestItem extends Request implements Serializable
{
	public enum Operation
	{
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
	public void execute(IStorageFacadeService facade, IResponseService responseService)
	{
		switch(type)
		{
			case REMOVE:
				responseService.sendResponse(new ResponseBoolean(facade.removeItem(item)));
				break;
			case UPDATE:
				responseService.sendResponse(new ResponseBoolean(facade.updateItem(item)));
				break;
		}
	}
}
