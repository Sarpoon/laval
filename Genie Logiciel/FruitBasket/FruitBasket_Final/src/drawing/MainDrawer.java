package drawing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import domain.basket.BasketController;
import domain.basket.BasketItem;

public class MainDrawer {

	private final BasketController controller;
	private ItemDrawerFactory fruitDrawerFactory;
	private BasketDrawer basketDrawer;
	private Dimension initialDimension;

	public MainDrawer(BasketController controller,
			ItemDrawerFactory fruitDrawerFactory, BasketDrawer basketDrawer,
			Dimension initialDimension) {
		this.controller = controller;
		this.fruitDrawerFactory = fruitDrawerFactory;
		this.basketDrawer = basketDrawer;
		this.initialDimension = initialDimension;
	}

	public void draw(Graphics g) {
		basketDrawer.drawBasket(g, initialDimension);
		List<BasketItem> fruits = controller.getFruitList();
		for (BasketItem fruit : fruits) {
			ItemDrawer fruitDrawer = fruitDrawerFactory.getItemDrawer(fruit);
			fruitDrawer.drawFruit(g, fruit);
		}
	}
}
