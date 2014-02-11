package cz.kofron.storage.view.gui;

import java.util.Collection;

import javafx.application.Platform;
import cz.kofron.storage.business.facade.StorageFacadeFactory;
import cz.kofron.storage.model.entity.Item;
import cz.kofron.storage.model.entity.ItemGroup;
import cz.kofron.storage.model.entity.User;

public class ContentUpdater extends Thread
{
	public final static int ITEM_GROUP_LIST = 0x0001;
	public final static int ITEM_GROUP_DETAILS = 0x0002;
	public final static int ITEM_LIST = 0x0004;
	public final static int ITEM_DETAILS = 0x0008;
	
	public final static int ALL = 0x000F;
	
	private boolean stopped = false;
	private boolean job = false;
	private long sleepLength = 0;
	private int updateFlags = 0;
	private MainWindow mainWindow;
	
	public ContentUpdater(MainWindow mainWindow)
	{
		this.mainWindow = mainWindow;
	}
	
	@Override
	public void run()
	{
		while(!stopped)
		{
			synchronized (this)
			{
				try
				{
					if(!job)
					{
						wait();
					}
					else
					{
						job = false;
					}
				}
				catch (InterruptedException e)
				{
				}
				long sleepNow = sleepLength;
				sleepLength = 0;
				if(sleepLength > 0)
				{
					try
					{
						
						Thread.sleep(sleepNow);
					}
					catch (InterruptedException e)
					{
					}
				}
				
				if(!mainWindow.isLogged())
				{
					mainWindow.setLogged(StorageFacadeFactory.getInstance().login("pepa", "zdepa"));
					if(!mainWindow.isLogged())
					{
						Platform.runLater(new Runnable()
						{
							@Override
							public void run()
							{
								MessageBox.show("Couldn't log in.", 666, null);
							}
						});
						continue;
					}
				}
				
				Platform.runLater(new Runnable()
				{
					@Override
					public void run()
					{
						doRefresh();
					}
				});
			}
		}
	}
	
	private void doRefresh()
	{
		Collection<ItemGroup> itemGroups = null;
		boolean selectedItemGroup = mainWindow.getItemGroupDetail().getItemGroupId() != null;
		
		if((updateFlags & (ITEM_GROUP_LIST | ITEM_GROUP_DETAILS)) != 0)
		{
			itemGroups = StorageFacadeFactory.getInstance().getItemGroups();
			if((updateFlags & ITEM_GROUP_LIST) != 0)
			{
				mainWindow.getItemGroupList().update(itemGroups);
			}
		}
		
		if((updateFlags & ITEM_GROUP_DETAILS) != 0)
		{
			if(selectedItemGroup)
			{
				for(ItemGroup itemGroup : itemGroups)
				{
					if(itemGroup.getId() == mainWindow.getItemGroupDetail().getItemGroupId())
					{
						mainWindow.getItemGroupDetail().update(itemGroup);
					}
				}
			}
		}

		Collection<Item> items = null;
		if((updateFlags & (ITEM_LIST | ITEM_DETAILS)) != 0 && selectedItemGroup)
		{
			items = StorageFacadeFactory.getInstance().getItems(mainWindow.getItemGroupDetail().getItemGroupId());
			if((updateFlags & ITEM_LIST) != 0)
			{
				mainWindow.getItemList().update(items);
			}
		}
		
		if((updateFlags & ITEM_DETAILS) != 0 && selectedItemGroup)
		{
			Integer itemId = mainWindow.getItemDetail().getItemId();
			if(itemId != null)
			{
				for(Item item : items)
				{
					if(item.getId() == mainWindow.getItemDetail().getItemId())
					{
						User theOne = null;
						for(User user : StorageFacadeFactory.getInstance().getUsers())
						{
							if(user.getId() == item.getAddedBy())
							{
								theOne = user;
							}
						}
						if(theOne == null)
						{
							theOne = new User(-1, "Invalid user", "", "");
						}
						mainWindow.getItemDetail().update(item, theOne);
					}
				}
			}
			else
			{
				mainWindow.getItemDetail().update(new Item(-1, 0, "", 0, 0), new User(-1, "", "", ""));
			}
		}
	}
	
	public void setStopped(boolean stopped)
	{
		this.stopped = stopped;
		synchronized (this)
		{
			this.notifyAll();
		}
	}
	
	public void refreshAfterWhile(long time, int flags)
	{
		this.updateFlags = flags;
		sleepLength = time;
		job = true;
		synchronized (this)
		{
			this.notifyAll();
		}
	}
	
	public void refresh(int flags)
	{
		this.updateFlags = flags;
		job = true;
		synchronized (this)
		{
			this.notifyAll();
		}
	}
}
