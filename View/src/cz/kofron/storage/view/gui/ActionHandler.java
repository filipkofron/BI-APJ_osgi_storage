package cz.kofron.storage.view.gui;

import javafx.application.Platform;
import cz.kofron.storage.control.ui.EventController;
import cz.kofron.storage.control.ui.IBooleanCallBack;
import cz.kofron.storage.model.entity.Item;
import cz.kofron.storage.model.entity.ItemGroup;

public class ActionHandler
{
	private MainWindow mainWindow;

	public ActionHandler(MainWindow mainWindow)
	{
		this.mainWindow = mainWindow;
	}

	public void onAddItemGroup()
	{
		String name = mainWindow.getItemGroupDetail().getItemGroupName();
		String desc = mainWindow.getItemGroupDetail().getItemGroupDescription();
		if (name.trim().length() < 1)
		{
			MessageBox.show("Name must be nonempty!", MessageBox.ERROR, null);
			return;
		}
		EventController.onAddItemGroup(name, desc, new IBooleanCallBack()
		{

			@Override
			public void call(boolean value)
			{
				if (value)
				{
					MessageBox.show("Added succesfully", MessageBox.INFO, null);
				}
				else
				{
					MessageBox.show("Adding failed!", MessageBox.ERROR, null);
				}
				mainWindow.getContentUpdater().refresh(ContentUpdater.ALL);
			}
		});
	}

	public void onSaveItemGroup()
	{
		Integer id = mainWindow.getItemGroupDetail().getItemGroupId();
		if (id == null)
		{
			MessageBox.show("Cannot save nothing.", MessageBox.ERROR, null);
			return;
		}
		String name = mainWindow.getItemGroupDetail().getItemGroupName();
		if (name.trim().length() < 1)
		{
			MessageBox.show("Name must be nonempty!", MessageBox.ERROR, null);
			return;
		}
		String desc = mainWindow.getItemGroupDetail().getItemGroupDescription();
		ItemGroup itemGroup = new ItemGroup(id, name, desc);
		EventController.onUpdateItemGroup(itemGroup, new IBooleanCallBack()
		{

			@Override
			public void call(boolean value)
			{
				if (value)
				{
					MessageBox.show("Saved succesfully", MessageBox.INFO, null);
				}
				else
				{
					MessageBox.show("Saved failed!", MessageBox.ERROR, null);
				}
				mainWindow.getContentUpdater().refresh(ContentUpdater.ITEM_GROUP_DETAILS | ContentUpdater.ITEM_GROUP_LIST);
			}
		});
	}

	public void onSelectItemGroup()
	{
		int index = mainWindow.getItemGroupList().getSelectionModel().getSelectedIndex();
		if (index >= 0)
		{
			ItemGroup itemGroup = mainWindow.getItemGroupList().getItemGroupByIndex(index);
			if (itemGroup != null)
			{
				if (mainWindow.getItemGroupDetail().getItemGroupId() == null || mainWindow.getItemGroupDetail().getItemGroupId() != itemGroup.getId())
				{
					mainWindow.getItemGroupDetail().setItemGroupId(itemGroup.getId());
					mainWindow.getItemDetail().setItemId(null);
					mainWindow.getContentUpdater().refresh(ContentUpdater.ITEM_GROUP_DETAILS | ContentUpdater.ITEM_LIST | ContentUpdater.ITEM_DETAILS);
				}
			}
		}
	}

	public void onSelectItem()
	{
		int index = mainWindow.getItemList().getSelectionModel().getSelectedIndex();
		if (index >= 0)
		{
			Item item = mainWindow.getItemList().getItemByIndex(index);
			if (item != null)
			{
				if (mainWindow.getItemDetail().getItemId() == null || mainWindow.getItemDetail().getItemId() != item.getId())
				{
					mainWindow.getItemDetail().setItemId(item.getId());
					mainWindow.getContentUpdater().refresh(ContentUpdater.ITEM_DETAILS);
				}
			}
		}
	}

	public void onAddItem()
	{
		long timeAdded = System.currentTimeMillis();
		Integer groupId = mainWindow.getItemGroupDetail().getItemGroupId();
		if(groupId == null)
		{
			MessageBox.show("No item group selected!", MessageBox.ERROR, null);
			return;
		}
		String info = mainWindow.getItemDetail().getInfo();
		if (info.trim().length() < 1)
		{
			MessageBox.show("Info must be nonempty!", MessageBox.ERROR, null);
			return;
		}
		EventController.onAddItem(timeAdded, info, groupId, new IBooleanCallBack()
		{

			@Override
			public void call(boolean value)
			{
				if (value)
				{
					MessageBox.show("Added succesfully", MessageBox.INFO, null);
				}
				else
				{
					MessageBox.show("Adding failed!", MessageBox.ERROR, null);
				}
				mainWindow.getContentUpdater().refresh(ContentUpdater.ITEM_LIST | ContentUpdater.ITEM_DETAILS);
			}
		});
	}

	public void onSaveItem()
	{
		Integer id = mainWindow.getItemDetail().getItemId();
		if (id == null)
		{
			MessageBox.show("Cannot save nothing.", MessageBox.ERROR, null);
			return;
		}
		long timeAdded = mainWindow.getItemDetail().getTimeAdded();
		int groupId = mainWindow.getItemDetail().getGroupId();
		int addedBy = mainWindow.getItemDetail().getAddedBy();
		String info = mainWindow.getItemDetail().getInfo();
		if (info.trim().length() < 1)
		{
			MessageBox.show("Info must be nonempty!", MessageBox.ERROR, null);
			return;
		}
		Item item = new Item(id, timeAdded, info, groupId, addedBy);
		EventController.onUpdateItem(item, new IBooleanCallBack()
		{

			@Override
			public void call(boolean value)
			{
				if (value)
				{
					MessageBox.show("Saved succesfully", MessageBox.INFO, null);
				}
				else
				{
					MessageBox.show("Saved failed!", MessageBox.ERROR, null);
				}
				mainWindow.getContentUpdater().refresh(ContentUpdater.ITEM_LIST | ContentUpdater.ITEM_DETAILS);
			}
		});
	}
	
	public void onRefresh()
	{
		mainWindow.getContentUpdater().refresh(ContentUpdater.ALL);
	}
	
	public void onExit()
	{
		Platform.exit();
	}
}
