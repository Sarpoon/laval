package domain.prices;

import util.PropertiesFinder;

public class NoDiscountPricing implements PricingStrategy {

	@Override
	public Price getDiscount(Price price) {
		double noDiscount = Double.parseDouble((String) PropertiesFinder
				.getInstance().getProperty("strategy.noDiscount"));
		return new Price(noDiscount);
	}

}
