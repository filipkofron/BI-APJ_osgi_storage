package cz.kofron.storage.integration.service.impl;

import java.util.ArrayList;

import cz.kofron.storage.integration.dao.UserDAO;
import cz.kofron.storage.model.entity.User;

public class UserDAOImpl implements UserDAO
{
	private static Integer id = new Integer(0);
	
	public static int getUniqId()
	{
		synchronized (id)
		{
			return id++;
		}
	}
	
	private ArrayList<User> users = new ArrayList<>();
	
	public UserDAOImpl()
	{
		User testUser = new User(getUniqId(), "pepa", "", "sock from_hell 666");
		testUser.setPassword("zdepa");
		users.add(testUser);
	}

	@Override
	public User getUserByName(String name)
	{
		for(User user : users)
		{
			if(user.getUsername().equals(name))
			{
				return user;
			}
		}
		return null;
	}
}
