package domain.basket;

import java.awt.Point;

import util.ItemsCollection;
import domain.prices.Price;

public class BasketItemFactory {

	public BasketItem createItem(String type, Point point) {
		BasketItem newItem;
		ItemDescription itemDescription = ItemsCollection.getInstance()
				.getItem(type);
		newItem = new BasketItem(point, itemDescription.type,
				itemDescription.description, new Price(
						itemDescription.basePrice), itemDescription.radius);
		return newItem;
	}
}
