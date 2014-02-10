package cz.kofron.storage.protocol.request;

import java.io.Serializable;

import cz.kofron.storage.business.facade.StorageFacade;
import cz.kofron.storage.protocol.service.IResponseService;

public abstract class Request implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1711357546684855149L;

	public abstract void execute(StorageFacade facade,
			IResponseService responseService);
}
