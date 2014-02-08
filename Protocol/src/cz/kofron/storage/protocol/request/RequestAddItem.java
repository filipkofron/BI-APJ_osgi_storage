package cz.kofron.storage.protocol.request;

import java.io.Serializable;
import java.util.ArrayList;

import cz.kofron.storage.business.service.IStorageFacadeService;
import cz.kofron.storage.model.entity.Item;
import cz.kofron.storage.protocol.response.ResponseItems;
import cz.kofron.storage.protocol.service.IResponseService;

public class RequestAddItem extends Request implements Serializable
{

	private long timeAdded;
	private String info;
	private int groupId;
	private int addedBy;
	
	
	
	public RequestAddItem(long timeAdded, String info, int groupId, int addedBy)
	{
		super();
		this.timeAdded = timeAdded;
		this.info = info;
		this.groupId = groupId;
		this.addedBy = addedBy;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 2134740741832691758L;
	
	
	@Override
	public void execute(IStorageFacadeService facade, IResponseService responseService)
	{
		Item item = facade.addItem(timeAdded, info, groupId, addedBy);
		
		ArrayList<Item> items = new ArrayList<>();
		items.add(item);
		
		responseService.sendResponse(new ResponseItems(items));
	}
	
}
