package domain.prices;

public interface PricingStrategy {
	Price getDiscount(Price price);
}
