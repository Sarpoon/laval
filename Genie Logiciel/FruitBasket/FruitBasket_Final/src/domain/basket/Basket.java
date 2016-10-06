package domain.basket;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import domain.invoice.Invoice;
import domain.prices.BossDiscountPricing;
import domain.prices.CompositePricingStrategy;
import domain.prices.NoDiscountPricing;
import domain.prices.Price;
import domain.prices.PricingStrategy;
import domain.prices.StudentDiscountPricing;

public class Basket {

	private List<BasketItem> itemList;
	private PricingStrategy pricingStrategy;

	public Basket() {
		itemList = new LinkedList<BasketItem>();
		pricingStrategy = new NoDiscountPricing();
	}

	public void add(BasketItem aFruit) {
		itemList.add(aFruit);
	}

	public boolean isEmpty() {
		return itemList.isEmpty();
	}

	public List<BasketItem> getFruitList() {
		return itemList;
	}

	public int getNumberOfFruits() {
		return itemList.size();
	}

	public double getSubTotal() {
		Price subTotal = new Price(0);
		for (BasketItem fruit : itemList) {
			Price fruitPrice = fruit.getPrice();
			subTotal = subTotal.addTo(fruitPrice);
		}
		return subTotal.getPriceValue();
	}

	public Invoice getInvoice() {
		Invoice invoice = new Invoice(pricingStrategy);
		for (BasketItem fruit : itemList) {
			invoice.addItem(fruit);
		}
		return invoice;
	}

	public void setBossDiscount() {
		pricingStrategy = new BossDiscountPricing();
	}

	public void setNoDiscount() {
		pricingStrategy = new NoDiscountPricing();
	}

	public void setStudentDiscount() {
		pricingStrategy = new StudentDiscountPricing();
	}

	public void setAllDiscounts() {
		CompositePricingStrategy newStrategy = new CompositePricingStrategy();
		newStrategy.addStrategy(new BossDiscountPricing());
		newStrategy.addStrategy(new StudentDiscountPricing());
		pricingStrategy = newStrategy;

	}

	public boolean isThereAnItemAtTheseCoordinates(int x, int y) {
		for (BasketItem item : this.itemList) {
			if (item.contains(x, y)) {
				return true;
			}
		}
		return false;

	}

	public void swapSelectionForCoordinates(int x, int y) {
		for (BasketItem item : this.itemList) {
			if (item.contains(x, y)) {
				item.switchSelection();
			}
		}
	}

	public void updateSelectedItemsPosition(Point delta) {
		for (BasketItem item : this.itemList) {
			if (item.isSelected()) {
				item.translate(delta);
			}
		}
	}

}
