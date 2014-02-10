package cz.kofron.storage.view.gui;

import cz.kofron.storage.control.ui.EventController;
import cz.kofron.storage.control.ui.IBooleanCallBack;
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
		if(name.trim().length() < 1)
		{
			MessageBox.show("Name must be nonempty!", MessageBox.ERROR, null);
			return;
		}
		EventController.onAddItemGroup(name, desc, new IBooleanCallBack()
		{
			
			@Override
			public void call(boolean value)
			{
				if(value)
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
		if(id == null)
		{
			MessageBox.show("Cannot save nothing.", MessageBox.ERROR, null);
			return;
		}
		String name = mainWindow.getItemGroupDetail().getItemGroupName();
		if(name.trim().length() < 1)
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
				if(value)
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
		if(index >= 0)
		{
			ItemGroup itemGroup = mainWindow.getItemGroupList().getItemGroupByIndex(index);
			if(itemGroup != null)
			{
				if(mainWindow.getItemGroupDetail().getItemGroupId() == null || mainWindow.getItemGroupDetail().getItemGroupId() != itemGroup.getId())
				{
					mainWindow.getItemGroupDetail().setItemGroupId(itemGroup.getId());
					mainWindow.getContentUpdater().refresh(ContentUpdater.ITEM_GROUP_DETAILS | ContentUpdater.ITEM_LIST | ContentUpdater.ITEM_DETAILS);
				}
			}
		}
	}
}
