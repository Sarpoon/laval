package domain.prices;

public class Price {
	double price;

	public Price(double price) {
		this.price = price;
	}

	public double getPriceValue() {
		return price;
	}

	public void setPrice(double newPriceValue) {
		this.price = newPriceValue;
	}

	public Price addTo(Price subTotal) {
		double newPriceValue = price + subTotal.getPriceValue();
		return new Price(newPriceValue);
	}
}
