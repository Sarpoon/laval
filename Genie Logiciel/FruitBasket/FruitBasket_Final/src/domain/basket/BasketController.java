package domain.basket;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import domain.invoice.Invoice;
import domain.invoice.InvoiceDescripter;

public class BasketController {

	private Basket basket;
	private BasketItemFactory itemFactory;
	private List<BasketControllerObserver> observers;

	public BasketController(Basket basket) {
		this.basket = basket;
		commonInitialization();
	}

	public BasketController() {
		basket = new Basket();
		commonInitialization();
	}

	private void commonInitialization() {
		observers = new LinkedList<BasketControllerObserver>();
		itemFactory = new BasketItemFactory();
	}

	public void addFruit(BasketItem aFruit) {
		basket.add(aFruit);
	}

	public void addItem(Point mousePoint, String type) {
		BasketItem newItem = itemFactory.createItem(type, mousePoint);
		basket.add(newItem);
		notifyObserversForUpdatedItems();
	}

	public List<BasketItem> getFruitList() {
		return basket.getFruitList();
	}

	public void registerObserver(BasketControllerObserver newListener) {
		observers.add(newListener);
	}

	public void unregisterObserver(BasketControllerObserver listener) {
		observers.remove(listener);
	}

	public void notifyObserversForUpdatedItems() {
		for (BasketControllerObserver observer : observers) {
			observer.notifyUpdatedItems();
		}
	}

	public int getNumberOfFruits() {
		return basket.getNumberOfFruits();
	}

	public double getSubTotal() {
		return basket.getSubTotal();
	}

	public String getInvoiceDescription() {
		Invoice invoice = basket.getInvoice();
		return new InvoiceDescripter(invoice).printInvoice();
	}

	public void setBossDiscount() {
		basket.setBossDiscount();
	}

	public void setNoDiscount() {
		basket.setNoDiscount();
	}

	public void setStudentDiscount() {
		basket.setStudentDiscount();
	}

	public void setAllDiscount() {
		basket.setAllDiscounts();
	}

	public boolean isThereAnItemAtTheseCoordinates(int x, int y) {
		return basket.isThereAnItemAtTheseCoordinates(x, y);
	}

	public void switchSelection(int x, int y) {
		basket.swapSelectionForCoordinates(x, y);
		notifyObserversForUpdatedItems();
	}

	public void updateSelectedItemsPositions(Point delta) {
		basket.updateSelectedItemsPosition(delta);
		notifyObserversForUpdatedItems();
	}
}
