package cz.kofron.storage.protocol.request;

import java.io.Serializable;

import cz.kofron.storage.business.facade.StorageFacade;
import cz.kofron.storage.protocol.response.ResponseBoolean;
import cz.kofron.storage.protocol.service.IResponseService;

public class RequestLogin extends Request implements Serializable
{
	private transient boolean loggedIn;

	/**
	 * 
	 */
	private static final long serialVersionUID = 5704369441483365292L;
	private String username;
	private String password;

	public RequestLogin(String username, String password)
	{
		this.username = username;
		this.password = password;
	}

	@Override
	public void execute(StorageFacade facade,
			IResponseService responseService)
	{
		loggedIn = facade.login(username, password);
		responseService.sendResponse(new ResponseBoolean(loggedIn));
	}

	public boolean isLoggedIn()
	{
		return loggedIn;
	}
}
