package cz.kofron.storage.view.gui;

import cz.kofron.storage.control.ui.EventController;
import cz.kofron.storage.control.ui.IBooleanCallBack;

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
		EventController.onAddItemGroup(name, desc, new IBooleanCallBack()
		{
			
			@Override
			public void call(boolean value)
			{
				
			}
		});
	}
	
	public void onSaveItemGroup()
	{
		System.out.println("onSaveItemGroup()");
	}
}
