package cz.kofron.storage.derby.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cz.kofron.storage.integration.dao.ItemGroupDAO;
import cz.kofron.storage.model.entity.ItemGroup;

public class DerbyItemGroupDAOImpl implements ItemGroupDAO
{

	private PreparedStatement addItemGroupStmt;
	private PreparedStatement removeItemGroupStmt;
	private PreparedStatement updateItemGroupStmt;
	private PreparedStatement getItemGroupsStmt;
	
	private Connection derbyConnection;
	
	public DerbyItemGroupDAOImpl(Connection derbyConnection)
	{
		this.derbyConnection = derbyConnection;
		
		try
		{
			addItemGroupStmt = derbyConnection.prepareStatement("INSERT INTO ITEMGROUPS VALUES(DEFAULT, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			removeItemGroupStmt = derbyConnection.prepareStatement("DELETE FROM ITEMGROUPS WHERE ID = ?");
			updateItemGroupStmt = derbyConnection.prepareStatement("UPDATE ITEMGROUPS SET NAME=?, DESCRIPTION=? WHERE ID = ?");
			getItemGroupsStmt = derbyConnection.prepareStatement("SELECT * FROM ITEMGROUPS");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public ItemGroup addItemGroup(String name, String description)
	{
		try
		{
			addItemGroupStmt.setString(1, name);
			addItemGroupStmt.setString(2, description);
			addItemGroupStmt.execute();
			ResultSet rs = addItemGroupStmt.getGeneratedKeys();
			derbyConnection.commit();
			if(rs.next())
			{
				ItemGroup itemGroup = new ItemGroup(rs.getInt(1), name, description);
				return itemGroup;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean removeItemGroup(ItemGroup itemGroup)
	{
		try
		{
			removeItemGroupStmt.setInt(1, itemGroup.getId());
			removeItemGroupStmt.execute();
			derbyConnection.commit();
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateItemGroup(ItemGroup itemGroup)
	{
		try
		{
			updateItemGroupStmt.setString(1, itemGroup.getName());
			updateItemGroupStmt.setString(2, itemGroup.getDescription());
			updateItemGroupStmt.setInt(3, itemGroup.getId());
			updateItemGroupStmt.execute();
			derbyConnection.commit();
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<ItemGroup> getItemGroups()
	{
		try {
            ResultSet rs = getItemGroupsStmt.executeQuery();
            ArrayList<ItemGroup> itemGroups = new ArrayList<ItemGroup>();
            while (rs.next()) {
                itemGroups.add(new ItemGroup(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            return itemGroups;
        } catch (SQLException ex) {
        	ex.printStackTrace();
            return new ArrayList<ItemGroup>();
        }
	}

}
