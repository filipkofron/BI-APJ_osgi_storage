package cz.kofron.storage.view.gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MainMenuBar extends MenuBar
{
	public MainMenuBar()
	{
		Menu fileMenu = new Menu("File");
		MenuItem exitItem = new MenuItem("Exit");
		
		fileMenu.getItems().add(exitItem);
		
		getMenus().add(fileMenu);
	}
}
