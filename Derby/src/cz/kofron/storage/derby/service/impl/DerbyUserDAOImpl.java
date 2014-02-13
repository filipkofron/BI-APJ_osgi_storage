package cz.kofron.storage.derby.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cz.kofron.storage.integration.dao.UserDAO;
import cz.kofron.storage.model.entity.User;

public class DerbyUserDAOImpl implements UserDAO
{
	private PreparedStatement userByName;
	private PreparedStatement users;

	public DerbyUserDAOImpl(Connection db)
	{
		try
		{
			userByName = db.prepareStatement("SELECT * FROM USERS WHERE USERNAME = ?");
			users = db.prepareStatement("SELECT * FROM USERS");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public User getUserByName(String name)
	{
		try
		{
			userByName.setString(1, name);
			ResultSet rs = userByName.executeQuery();
	        return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<User> getUsers()
	{
		try
		{
			ResultSet rs = users.executeQuery();
			ArrayList<User> users = new ArrayList<User>();
            while (rs.next()) {
            	users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
	        return users;
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean login(String username, String password)
	{
		User user = getUserByName(username);

		if (user != null)
		{
			return user.checkPassword(password);
		}
		return false;
	}

}
