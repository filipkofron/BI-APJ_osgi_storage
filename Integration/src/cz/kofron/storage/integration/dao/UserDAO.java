package cz.kofron.storage.integration.dao;

import cz.kofron.storage.model.entity.User;

public interface UserDAO
{
	public User getUserByName(String name);
}
