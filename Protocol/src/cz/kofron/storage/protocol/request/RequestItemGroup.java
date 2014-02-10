package cz.kofron.storage.protocol.request;

import java.io.Serializable;

import cz.kofron.storage.business.facade.StorageFacade;
import cz.kofron.storage.model.entity.ItemGroup;
import cz.kofron.storage.protocol.response.ResponseBoolean;
import cz.kofron.storage.protocol.service.IResponseService;

public class RequestItemGroup extends Request implements Serializable
{
	public enum Operation
	{
		REMOVE, UPDATE
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
	public void execute(StorageFacade facade,
			IResponseService responseService)
	{
		switch (type)
		{
			case REMOVE:
				responseService.sendResponse(new ResponseBoolean(facade
						.removeItemGroup(itemGroup)));
				break;
			case UPDATE:
				responseService.sendResponse(new ResponseBoolean(facade
						.updateItemGroup(itemGroup)));
				break;
		}
	}
}
