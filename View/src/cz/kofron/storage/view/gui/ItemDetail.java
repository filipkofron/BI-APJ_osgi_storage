package cz.kofron.storage.view.gui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import cz.kofron.storage.model.entity.Item;
import cz.kofron.storage.model.entity.User;

public class ItemDetail extends GridPane
{
	private Integer itemId = null;
	private Text timeAddedField;
	private TextArea infoArea;
	private Text addedByField;
	private int addedBy;
	private int groupId;
	private Button addButton;
	private Button saveButton;
	private Label timeAddedLabel;
	private Label infoLabel;
	private Label addedByLabel;
	private ActionHandler actionHandler;
	
	public ItemDetail(ActionHandler actionHandler)
	{
		this.actionHandler = actionHandler;
		timeAddedField = new Text();
		addedByField = new Text();
		infoArea = new TextArea();

		addButton = new Button("Add");
		saveButton = new Button("Save");

		timeAddedLabel = new Label("Added on:");
		addedByLabel = new Label("AddedBy:");
		infoLabel = new Label("Info:");

		addButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				ItemDetail.this.actionHandler.onAddItem();
			}
		});

		saveButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				ItemDetail.this.actionHandler.onSaveItem();
			}
		});

		add(timeAddedLabel, 0, 1);
		add(timeAddedField, 2, 1, 1, 1);

		add(addedByLabel, 0, 2);
		add(addedByField, 2, 2, 1, 1);

		add(infoLabel, 0, 3);
		add(infoArea, 1, 3, 3, 1);

		add(saveButton, 1, 5, 1, 1);
		add(addButton, 2, 5, 1, 1);
	}

	private final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");

	public long getTimeAdded()
	{
		try
		{
			return dateFormat.parse(timeAddedField.getText()).getTime();
		}
		catch (ParseException e)
		{
		}

		return 0;
	}

	public String getInfo()
	{
		return infoArea.getText();
	}

	public int getAddedBy()
	{
		return addedBy;
	}

	public int getGroupId()
	{
		return groupId;
	}

	public Integer getItemId()
	{
		return itemId;
	}

	public void setItemId(Integer itemId)
	{
		this.itemId = itemId;
	}

	public void update(Item item, User user)
	{
		addedBy = item.getAddedBy();
		itemId = item.getId();
		groupId = item.getGroupId();

		if (itemId >= 0)
		{
			timeAddedField.setText(dateFormat.format(new Date(item.getTimeAdded())));
		}
		else
		{
			timeAddedField.setText("");
		}
		addedByField.setText(user.getUsername());
		infoArea.setText(item.getInfo());
	}
}
