package cz.kofron.storage.protocol.request;

import java.io.Serializable;
import java.util.ArrayList;

import cz.kofron.storage.business.service.IStorageFacadeService;
import cz.kofron.storage.model.entity.ItemGroup;
import cz.kofron.storage.protocol.response.ResponseItemGroups;
import cz.kofron.storage.protocol.service.IResponseService;

public class RequestAddItemGroup extends Request implements Serializable
{

	public RequestAddItemGroup(String name, String description)
	{
		super();
		this.name = name;
		this.description = description;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2048363523464815097L;
	private String name;
	private String description;
	
	@Override
	public void execute(IStorageFacadeService facade, IResponseService responseService)
	{
		ItemGroup itemGroup = facade.addItemGroup(name, description);
		ArrayList<ItemGroup> itemGroups = new ArrayList<ItemGroup>();
		itemGroups.add(itemGroup);
		responseService.sendResponse(new ResponseItemGroups(itemGroups));
	}

}
