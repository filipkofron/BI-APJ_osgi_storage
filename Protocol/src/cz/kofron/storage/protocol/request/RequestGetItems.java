package cz.kofron.storage.protocol.request;

import java.io.Serializable;

import cz.kofron.storage.business.service.IStorageFacadeService;

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
	public void execute(IStorageFacadeService facade)
	{
		facade.getItems(groupId);
	}
}
