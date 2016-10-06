package domain.invoice;

import java.text.DecimalFormat;

import domain.prices.Price;

public class InvoiceLine {
	private String description;
	private String type;
	private int quantity;
	private Price price;

	public InvoiceLine(String description, String type, Price price) {
		this.description = description;
		this.type = type;
		this.quantity = 1;
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getTotal() {
		return quantity * price.getPriceValue();
	}

	public String describe() {
		double basePriceValue = price.getPriceValue();
		double lineTotalValue = getTotal();
		String lineTotalString = new DecimalFormat("##.##")
				.format(lineTotalValue);
		return description + "\t" + String.valueOf(quantity) + " @ "
				+ String.valueOf(basePriceValue) + "\t" + lineTotalString
				+ " $";
	}

	public String getType() {
		return type;
	}

	public void incrementQuantity() {
		this.quantity = quantity + 1;
	}
}
