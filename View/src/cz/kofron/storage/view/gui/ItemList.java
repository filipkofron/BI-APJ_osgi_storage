package cz.kofron.storage.view.gui;

import java.util.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import cz.kofron.storage.model.entity.Item;

public class ItemList extends ListView<String>
{
	private final static ObservableList<String> items = FXCollections.observableArrayList();
	
	public void update(Collection<Item> itemsToShow)
	{
		items.clear();
		if(itemsToShow != null)
		{
			for(Item item : itemsToShow)
			{
				items.add(item.toString());
			}
		}
		setItems(items);
	}
}
