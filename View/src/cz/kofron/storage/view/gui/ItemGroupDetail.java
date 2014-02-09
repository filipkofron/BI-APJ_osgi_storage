package cz.kofron.storage.view.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ItemGroupDetail extends GridPane
{
	private Label nameLabel;
	private Label descLabel;
	private TextField nameTextField;
	private TextArea descTextArea;
	private Button addButton;
	private Button saveButton;
	private ActionHandler eventHandler;
	private int itemId;

	public ItemGroupDetail(ActionHandler eventHandler)
	{
		this.eventHandler = eventHandler;
		nameLabel = new Label("Name:");
		descLabel = new Label("Description:");

		nameTextField = new TextField();
		descTextArea = new TextArea();

		addButton = new Button("Add");
		saveButton = new Button("Save");

		addButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				ItemGroupDetail.this.eventHandler.onAddItemGroup();
			}
		});

		saveButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				ItemGroupDetail.this.eventHandler.onSaveItemGroup();
			}
		});

		add(nameLabel, 0, 1);
		add(nameTextField, 1, 1, 1, 1);

		add(descLabel, 0, 2);
		add(descTextArea, 1, 2, 2, 1);

		add(saveButton, 1, 4, 1, 1);
		add(addButton, 2, 4, 1, 1);
	}

	public int getItemGroupId()
	{
		return itemId;
	}

	public String getItemGroupName()
	{
		return nameTextField.getText();
	}

	public String getItemGroupDescription()
	{
		return descTextArea.getText();
	}
}
