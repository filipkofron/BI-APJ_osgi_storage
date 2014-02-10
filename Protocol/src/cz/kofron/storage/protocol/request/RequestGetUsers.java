package cz.kofron.storage.protocol.request;

import cz.kofron.storage.business.facade.StorageFacade;
import cz.kofron.storage.protocol.response.ResponseUsers;
import cz.kofron.storage.protocol.service.IResponseService;

public class RequestGetUsers extends Request
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1484359982590376784L;

	public RequestGetUsers()
	{
	}

	@Override
	public void execute(StorageFacade facade, IResponseService responseService)
	{
		responseService.sendResponse(new ResponseUsers(facade.getUsers()));
	}
}
