package cz.kofron.storage.protocol.response;

import java.util.ArrayList;

import cz.kofron.storage.model.entity.User;

public class ResponseUsers extends Response
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9097765683081928670L;
	private ArrayList<User> users;

	public ResponseUsers(ArrayList<User> users)
	{
		this.users = users;
	}

	public ArrayList<User> getUsers()
	{
		return users;
	}
}
