package domain.basket;

import java.awt.Color;

public class ItemDescription {
	public String type;
	public String description;
	public Color color;
	public double basePrice;
	public String imagePath;
	public int radius;

	public ItemDescription(String type, String description, Color color,
			double price, String imagePath, int radius) {
		this.type = type;
		this.description = description;
		this.color = color;
		this.basePrice = price;
		this.radius = radius;
		this.imagePath = imagePath;
	}
}
