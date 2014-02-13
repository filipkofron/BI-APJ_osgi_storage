package cz.kofron.storage.derby.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cz.kofron.storage.integration.dao.ItemDAO;
import cz.kofron.storage.model.entity.Item;

public class DerbyItemDAOImpl implements ItemDAO
{
	private PreparedStatement addItemStmt;
	private PreparedStatement removeItemStmt;
	private PreparedStatement updateItemStmt;
	private PreparedStatement getItemsStmt;
	
	private Connection derbyConnection;
	
	public DerbyItemDAOImpl(Connection derbyConnection)
	{
		this.derbyConnection = derbyConnection;
		
		try
		{
			addItemStmt = derbyConnection.prepareStatement("INSERT INTO ITEMS VALUES(DEFAULT, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			removeItemStmt = derbyConnection.prepareStatement("DELETE FROM ITEMS WHERE ID = ?");
			updateItemStmt = derbyConnection.prepareStatement("UPDATE ITEMS SET TIMEADDED=?, INFO=?, GROUPID=?, ADDEDBY=? WHERE ID = ?");
			getItemsStmt = derbyConnection.prepareStatement("SELECT * FROM ITEMS WHERE GROUPID=?");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Item addItem(long timeAdded, String info, int groupId, int addedBy)
	{
		try
		{
			addItemStmt.setLong(1, timeAdded);
			addItemStmt.setString(2, info);
			addItemStmt.setInt(3, groupId);
			addItemStmt.setInt(4, addedBy);
			addItemStmt.execute();
			ResultSet rs = addItemStmt.getGeneratedKeys();
			derbyConnection.commit();
			if(rs.next())
			{
				Item item = new Item(rs.getInt(1), timeAdded, info, groupId, addedBy);
				return item;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean removeItem(Item item)
	{
		try
		{
			removeItemStmt.setInt(1, item.getId());
			removeItemStmt.execute();
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
	public boolean updateItem(Item item)
	{
		try
		{
			updateItemStmt.setLong(1, item.getTimeAdded());
			updateItemStmt.setString(2, item.getInfo());
			updateItemStmt.setInt(3, item.getGroupId());
			updateItemStmt.setInt(4, item.getAddedBy());
			updateItemStmt.setInt(5, item.getId());
			updateItemStmt.execute();
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
	public ArrayList<Item> getItems(int groupId)
	{
		try {
			getItemsStmt.setInt(1, groupId);
            ResultSet rs = getItemsStmt.executeQuery();
            ArrayList<Item> items = new ArrayList<Item>();
            while (rs.next()) {
                items.add(new Item(rs.getInt(1), rs.getLong(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
            }
            return items;
        } catch (SQLException ex) {
        	ex.printStackTrace();
            return new ArrayList<Item>();
        }
	}

}
