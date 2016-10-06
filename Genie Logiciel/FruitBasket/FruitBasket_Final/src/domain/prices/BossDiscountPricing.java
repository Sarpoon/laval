package domain.prices;

import util.PropertiesFinder;

public class BossDiscountPricing implements PricingStrategy {

	@Override
	public Price getDiscount(Price price) {
		double bossDiscount = Double.parseDouble((String) PropertiesFinder
				.getInstance().getProperty("strategy.bossDiscount"));
		double discountValue = price.getPriceValue() * bossDiscount;
		return new Price(discountValue);
	}

}
