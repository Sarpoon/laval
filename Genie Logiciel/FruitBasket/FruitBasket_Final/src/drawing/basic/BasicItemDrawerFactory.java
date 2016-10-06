package drawing.basic;

import gui.frames.MainWindow;
import util.ItemsCollection;
import domain.basket.BasketItem;
import domain.basket.ItemDescription;
import drawing.ItemDrawer;
import drawing.ItemDrawerFactory;

public class BasicItemDrawerFactory implements ItemDrawerFactory {

	public BasicItemDrawerFactory(MainWindow outer) {
	}

	public ItemDrawer getItemDrawer(BasketItem item) {
		ItemDescription itemDescription = ItemsCollection.getInstance()
				.getItem(item.getType());
		return new BasicItemDrawer(itemDescription);
	}
}
