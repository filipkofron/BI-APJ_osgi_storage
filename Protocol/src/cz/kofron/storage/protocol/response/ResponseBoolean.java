package cz.kofron.storage.protocol.response;

import java.io.Serializable;

public class ResponseBoolean extends Response implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8353765538360660637L;
	private boolean value;

	public ResponseBoolean(boolean value)
	{
		this.value = value;
	}

	public boolean getValue()
	{
		return value;
	}

}
