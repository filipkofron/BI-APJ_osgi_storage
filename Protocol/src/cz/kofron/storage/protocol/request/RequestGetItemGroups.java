package cz.kofron.storage.protocol.request;

import java.io.Serializable;

import cz.kofron.storage.business.service.IStorageFacadeService;

public class RequestGetItemGroups extends Request implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2622090574653021166L;

	@Override
	public void execute(IStorageFacadeService facade)
	{
		facade.getItemGroups();
	}
	
}
