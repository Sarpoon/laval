package domain.prices;

import java.util.LinkedList;
import java.util.List;

public class CompositePricingStrategy implements PricingStrategy {

	private List<PricingStrategy> strategies;

	public CompositePricingStrategy() {
		strategies = new LinkedList<PricingStrategy>();
	}

	public void addStrategy(PricingStrategy newStrategy) {
		strategies.add(newStrategy);
	}

	public void removeStrategy(PricingStrategy strategyToRemove) {
		strategies.remove(strategyToRemove);
	}

	@Override
	public Price getDiscount(Price price) {
		Price totalDiscountAmount = new Price(0);
		for (PricingStrategy strategy : strategies) {
			Price strategyDiscount = strategy.getDiscount(price);
			totalDiscountAmount = totalDiscountAmount.addTo(strategyDiscount);
		}
		return totalDiscountAmount;
	}

}
