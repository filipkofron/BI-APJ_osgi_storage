package cz.kofron.storage.protocol.request;

import java.io.Serializable;

import cz.kofron.storage.business.service.IStorageFacadeService;
import cz.kofron.storage.protocol.response.ResponseItems;
import cz.kofron.storage.protocol.service.IResponseService;

public class RequestGetItems extends Request implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2968481553131016326L;
	private int groupId;

	public RequestGetItems(int groupId)
	{
		super();
		this.groupId = groupId;
	}

	public int getGroupId()
	{
		return groupId;
	}
	
	@Override
	public void execute(IStorageFacadeService facade, IResponseService responseService)
	{
		responseService.sendResponse(new ResponseItems(facade.getItems(groupId)));
	}
}
