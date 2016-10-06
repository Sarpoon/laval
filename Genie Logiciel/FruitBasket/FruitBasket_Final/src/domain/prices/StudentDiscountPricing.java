package domain.prices;

import util.PropertiesFinder;

public class StudentDiscountPricing implements PricingStrategy {

	@Override
	public Price getDiscount(Price price) {
		double studentDiscount = Double.parseDouble((String) PropertiesFinder
				.getInstance().getProperty("strategy.studentDiscount"));
		double discountValue = price.getPriceValue() * studentDiscount;
		;
		return new Price(discountValue);
	}

}
