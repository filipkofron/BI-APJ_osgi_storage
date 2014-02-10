package cz.kofron.storage.view.gui;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindow extends Application
{
	private MainMenuBar mainMenuBar;
	private ItemList itemList;
	private ItemDetail itemDetail;
	private ItemGroupList itemGroupList;
	private ItemGroupDetail itemGroupDetail;
	private ActionHandler actionHandler;

	private ContentUpdater contentUpdater;
	
	private void prepareMakeMenuBar()
	{
		mainMenuBar = new MainMenuBar();
	}

	private void prepareItemList()
	{
		itemList = new ItemList();
	}

	private void prepareItemDetail()
	{
		itemDetail = new ItemDetail();
	}
	
	private void prepareItemGroupList()
	{
		itemGroupList = new ItemGroupList(actionHandler);
	}

	private void prepareItemGroupDetail()
	{
		itemGroupDetail = new ItemGroupDetail(actionHandler);
	}

	private Scene createScene()
	{
		setUserAgentStylesheet(STYLESHEET_MODENA);

		BorderPane borderPane = new BorderPane();
		Scene scene = new Scene(borderPane, 600, 420);

		prepareMakeMenuBar();
		prepareItemList();
		prepareItemDetail();
		prepareItemGroupList();
		prepareItemGroupDetail();

		borderPane.setTop(mainMenuBar);

		SplitPane mainSplitPane = new SplitPane();
		mainSplitPane.setOrientation(Orientation.HORIZONTAL);

		BorderPane leftBorderPane = new BorderPane();
		BorderPane rightBorderPane = new BorderPane();
		
		SplitPane leftSplitPane = new SplitPane();
		leftSplitPane.setOrientation(Orientation.VERTICAL);
		
		SplitPane rightSplitPane = new SplitPane();
		rightSplitPane.setOrientation(Orientation.VERTICAL);

		BorderPane leftUpBorderPane = new BorderPane();
		BorderPane leftDownBorderPane = new BorderPane();
		BorderPane rightUpBorderPane = new BorderPane();
		BorderPane rightDownBorderPane = new BorderPane();
		
		leftUpBorderPane.setCenter(itemGroupList);
		leftDownBorderPane.setCenter(itemGroupDetail);
		rightUpBorderPane.setCenter(itemList);
		rightDownBorderPane.setCenter(itemDetail);
		
		leftSplitPane.getItems().add(leftUpBorderPane);
		leftSplitPane.getItems().add(leftDownBorderPane);
		rightSplitPane.getItems().add(rightUpBorderPane);
		rightSplitPane.getItems().add(rightDownBorderPane);
		
		leftBorderPane.setCenter(leftSplitPane);
		rightBorderPane.setCenter(rightSplitPane);

		mainSplitPane.getItems().add(leftBorderPane);
		mainSplitPane.getItems().add(rightBorderPane);

		borderPane.setCenter(mainSplitPane);

		return scene;
	}

	@Override
	public void start(Stage stage) throws Exception
	{
		actionHandler = new ActionHandler(this);
		stage.setScene(createScene());
		
		contentUpdater = new ContentUpdater(this);
		contentUpdater.refreshAfterWhile(1500, ContentUpdater.ALL);
		contentUpdater.start();
		stage.show();
	}
	
	@Override
	public void stop() throws Exception
	{
		contentUpdater.setStopped(true);
		super.stop();
	}

	public MainMenuBar getMainMenuBar()
	{
		return mainMenuBar;
	}

	public ItemList getItemList()
	{
		return itemList;
	}

	public ItemDetail getItemDetail()
	{
		return itemDetail;
	}

	public ItemGroupList getItemGroupList()
	{
		return itemGroupList;
	}

	public ItemGroupDetail getItemGroupDetail()
	{
		return itemGroupDetail;
	}
	
	public ContentUpdater getContentUpdater()
	{
		return contentUpdater;
	}
}
