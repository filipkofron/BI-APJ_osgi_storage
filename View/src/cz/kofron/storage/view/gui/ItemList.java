package cz.kofron.storage.view.gui;

import java.util.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import cz.kofron.storage.model.entity.Item;

public class ItemList extends ListView<String>
{
	private final static ObservableList<String> items = FXCollections.observableArrayList();
	private Collection<Item> currentItems;
	private ActionHandler actionHandler;

	public ItemList(ActionHandler actionHandler)
	{
		this.actionHandler = actionHandler;

		setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent arg0)
			{
				ItemList.this.actionHandler.onSelectItem();
			}
		});
	}

	public void update(Collection<Item> itemsToShow)
	{
		this.currentItems = itemsToShow;
		items.clear();
		if (itemsToShow != null)
		{
			for (Item item : itemsToShow)
			{
				items.add("[" + item.getId() + "] " + item.getInfo());
			}
		}
		setItems(items);
	}

	public Item getItemByIndex(int index)
	{
		int i = 0;
		for (Item item : currentItems)
		{
			if (i++ == index)
			{
				return item;
			}
		}
		return null;
	}
}
