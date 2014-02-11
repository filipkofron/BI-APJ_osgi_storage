package cz.kofron.storage.view.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MainMenuBar extends MenuBar
{
	private ActionHandler actionHandler;
	
	public MainMenuBar(ActionHandler actionHandler)
	{
		this.actionHandler = actionHandler;
		Menu fileMenu = new Menu("File");
		MenuItem exitItem = new MenuItem("Exit");
		exitItem.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				MainMenuBar.this.actionHandler.onExit();
			}
		});
		MenuItem refreshItem = new MenuItem("Refresh");
		refreshItem.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				MainMenuBar.this.actionHandler.onRefresh();
			}
		});
		
		fileMenu.getItems().add(exitItem);
		fileMenu.getItems().add(refreshItem);
		
		getMenus().add(fileMenu);
	}
}
