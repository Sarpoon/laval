package domain.invoice;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import domain.basket.BasketItem;
import domain.prices.Price;
import domain.prices.PricingStrategy;

public class Invoice {

	private Map<String, InvoiceLine> invoiceItems;
	private PricingStrategy pricingStrategy;

	public Invoice(PricingStrategy pricingStrategy) {
		invoiceItems = new HashMap<String, InvoiceLine>();
		this.pricingStrategy = pricingStrategy;
	}

	public void addItem(BasketItem item) {
		Price price = item.getPrice();
		String type = item.getType();
		String description = item.getDescription();
		InvoiceLine newLine = new InvoiceLine(description, type, price);
		addLine(newLine);
	}

	private void addLine(InvoiceLine newLine) {
		String itemType = newLine.getType();
		InvoiceLine existingLine = invoiceItems.get(itemType);
		if (existingLine != null) {
			existingLine.incrementQuantity();
			invoiceItems.put(itemType, existingLine);
		} else {
			invoiceItems.put(itemType, newLine);
		}
	}

	public int getNumberOfLines() {
		return invoiceItems.size();
	}

	public Price getSubTotal() {
		Price subTotal = new Price(0);
		for (InvoiceLine line : getInvoiceLines()) {
			double totalPriceOfLine = line.getTotal();
			double newPriceValue = subTotal.getPriceValue() + totalPriceOfLine;
			subTotal.setPrice(newPriceValue);
		}
		return subTotal;
	}

	List<InvoiceLine> getInvoiceLines() {
		List<InvoiceLine> lines = new LinkedList<InvoiceLine>();
		for (Entry<String, InvoiceLine> setEntry : invoiceItems.entrySet()) {
			lines.add(setEntry.getValue());
		}
		return lines;
	}

	public Price getDiscount() {
		Price price = getSubTotal();
		return pricingStrategy.getDiscount(price);
	}

}
