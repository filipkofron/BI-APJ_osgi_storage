package cz.kofron.storage.view.gui;

import java.util.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import cz.kofron.storage.model.entity.ItemGroup;

public class ItemGroupList extends ListView<String>
{
	private final static ObservableList<String> items = FXCollections.observableArrayList();

	private Collection<ItemGroup> currentItems;
	private ActionHandler actionHandler;

	public ItemGroupList(ActionHandler actionHandler)
	{
		this.actionHandler = actionHandler;

		setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent arg0)
			{
				ItemGroupList.this.actionHandler.onSelectItemGroup();
			}
		});
	}

	public void update(Collection<ItemGroup> itemsToShow)
	{
		this.currentItems = itemsToShow;
		items.clear();
		if (itemsToShow != null)
		{
			for (ItemGroup item : itemsToShow)
			{
				items.add("[" + item.getId() + "] " + item.getName() + " - " + item.getDescription().replace('\n', ' ').replace('\r', ' '));
			}
		}
		setItems(items);
	}

	public ItemGroup getItemGroupByIndex(int index)
	{
		int i = 0;
		for (ItemGroup itemGroup : currentItems)
		{
			if (i++ == index)
			{
				return itemGroup;
			}
		}
		return null;
	}
}
