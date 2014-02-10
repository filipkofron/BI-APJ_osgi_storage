package cz.kofron.storage.integration.dao;

import java.util.ArrayList;

import cz.kofron.storage.model.entity.User;

public interface UserDAO
{
	public User getUserByName(String name);
	public ArrayList<User> getUsers();
}
