package cz.kofron.storage.client.impl;

import java.util.ArrayList;

import cz.kofron.storage.client.net.Client;
import cz.kofron.storage.integration.dao.UserDAO;
import cz.kofron.storage.model.entity.User;
import cz.kofron.storage.protocol.request.RequestGetUsers;
import cz.kofron.storage.protocol.response.ResponseUsers;

public class NetUserDAOImpl implements UserDAO
{
	private Client client;
	
	public NetUserDAOImpl(Client client)
	{
		this.client = client;
	}

	@Override
	public User getUserByName(String name)
	{
		ResponseUsers users = (ResponseUsers) client.communicate(new RequestGetUsers());
		User ret = null;
		for(User user : users.getUsers())
		{
			if(user.equals(name))
			{
				ret = user;
				break;
			}
		}
		return ret;
	}

	@Override
	public ArrayList<User> getUsers()
	{
		ResponseUsers users = (ResponseUsers) client.communicate(new RequestGetUsers());
		return users.getUsers();
	}
}
