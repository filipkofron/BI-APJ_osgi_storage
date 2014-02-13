package cz.kofron.storage.derby.service.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.osgi.framework.BundleContext;

import cz.kofron.storage.integration.dao.ItemDAO;
import cz.kofron.storage.integration.dao.ItemGroupDAO;
import cz.kofron.storage.integration.dao.UserDAO;
import cz.kofron.storage.integration.service.DAOFactoryService;
import cz.kofron.storage.model.entity.User;

public class DerbyDAOFactoryServiceImpl extends DAOFactoryService
{
	private ItemDAO itemDAO;
	private ItemGroupDAO itemGroupDAO;
	private UserDAO userDAO;
	private BundleContext context;
	private Connection derbyConnection;
	private final static Logger log = Logger.getLogger(DerbyDAOFactoryServiceImpl.class.getName());

	public DerbyDAOFactoryServiceImpl(BundleContext context)
	{
		this.context = context;

		derbyConnection = createConnection();

		try
		{
			DatabaseMetaData m = derbyConnection.getMetaData();
			ResultSet rs = m.getTables(null, null, "USERS", null);
			if (!rs.next())
			{
				Statement s = derbyConnection.createStatement();
				s.executeUpdate("CREATE TABLE USERS (ID INT NOT NULL GENERATED ALWAYS AS IDENTITY, USERNAME VARCHAR(50), HASH VARCHAR(64), SALT VARCHAR(50), PRIMARY KEY (ID))");
				log.info("Table USERS created.");
				
				User testUser = new User(0, "pepa", "", "sock from_hell 666");
				testUser.setPassword("zdepa");
				
				Statement s2 = derbyConnection.createStatement();
				s2.execute("INSERT INTO USERS VALUES(DEFAULT, '" + testUser.getUsername() + "', '" + testUser.getHash() + "', '" + testUser.getSalt() + "')");
				log.info("Added test user");
			}
			rs = m.getTables(null, null, "ITEMS", null);
			if (!rs.next())
			{
				Statement s = derbyConnection.createStatement();
				s.executeUpdate("CREATE TABLE ITEMS (ID INT NOT NULL GENERATED ALWAYS AS IDENTITY, TIMEADDED BIGINT, INFO VARCHAR(200), GROUPID INT, ADDEDBY INT, PRIMARY KEY (ID))");
				log.log(Level.INFO, "Table ITEMS created.");
			}
			rs = m.getTables(null, null, "ITEMGROUPS", null);
			if (!rs.next())
			{
				Statement s = derbyConnection.createStatement();
				s.executeUpdate("CREATE TABLE ITEMGROUPS (ID INT NOT NULL GENERATED ALWAYS AS IDENTITY, NAME VARCHAR(200), DESCRIPTION VARCHAR(200), PRIMARY KEY (ID))");
				log.log(Level.INFO, "Table ITEMGROUPS created.");
			}
			derbyConnection.commit();
		}
		catch(SQLException e)
		{
			log.log(Level.SEVERE, null, e);
		}
	}

	private Connection createConnection()
	{
		Connection dbCon = null;
		try
		{
			new org.apache.derby.jdbc.EmbeddedDriver();
			String url = "jdbc:derby:" + System.getProperty("user.home") + "/storageDB; create=true";
			dbCon = DriverManager.getConnection(url);
		}
		catch (SQLException ex)
		{
			log.log(Level.SEVERE, null, ex);
		}
		return dbCon;
	}

	@Override
	public ItemDAO getItemDAO()
	{
		if(itemDAO == null)
		{
			itemDAO = new DerbyItemDAOImpl(derbyConnection);
		}
		
		return itemDAO;
	}

	@Override
	public ItemGroupDAO getItemGroupDAO()
	{
		if(itemGroupDAO == null)
		{
			itemGroupDAO = new DerbyItemGroupDAOImpl(derbyConnection);
		}
		
		return itemGroupDAO;
	}

	@Override
	public UserDAO getUserDAO()
	{
		if(userDAO == null)
		{
			userDAO = new DerbyUserDAOImpl(derbyConnection);
		}
		
		return userDAO;
	}

}
