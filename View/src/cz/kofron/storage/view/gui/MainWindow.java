package cz.kofron.storage.view.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindow extends Application
{
	private MainMenuBar mainMenuBar;
	private ItemTable itemTable;
	private ItemDetail itemDetail;
	
	private void prepareMakeMenuBar()
	{
		mainMenuBar = new MainMenuBar();
	}
	
	private void prepareItemTable()
	{
		itemTable = new ItemTable();
	}
	
	private void prepareItemDetail()
	{
		itemDetail = new ItemDetail();
	}
	
	private Scene createScene()
	{
		setUserAgentStylesheet(STYLESHEET_MODENA);
		
		BorderPane borderPane = new BorderPane();
		Scene scene = new Scene(borderPane, 600, 420);
		
		prepareMakeMenuBar();
		prepareItemTable();
		prepareItemDetail();
		
		borderPane.setTop(mainMenuBar);

		SplitPane splitPane = new SplitPane();
		
		BorderPane leftBorderPane = new BorderPane();
		BorderPane rightBorderPane = new BorderPane();
		
		leftBorderPane.setCenter(itemTable);
		rightBorderPane.setCenter(itemDetail);
		
		splitPane.getItems().add(leftBorderPane);
		splitPane.getItems().add(rightBorderPane);
		
		borderPane.setCenter(splitPane);
		
		return scene;
	}
	
	@Override
	public void start(Stage stage) throws Exception
	{
		stage.setScene(createScene());
		
		stage.show();
	}

}
