package domain.basket;

import java.awt.Point;

import domain.prices.Price;

public class BasketItem {

	private String type;
	private Point point;
	private int radius;
	private Price price;
	private String description;
	private boolean selectionStatus;

	public BasketItem(Point point, String type, String description,
			Price price, int radius) {
		this.point = point;
		this.type = type;
		this.description = description;
		this.price = price;
		this.radius = radius;
		this.selectionStatus = false;
	}

	public Point getPoint() {
		return point;
	}

	public Price getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	public boolean contains(int x, int y) {
		return (xIsInsideItemWidth(x) && yIsInsideItemHeight(y));
	}

	private boolean xIsInsideItemWidth(int x) {
		return (x < point.getX() + (radius)) && (x > point.getX() - (radius));
	}

	private boolean yIsInsideItemHeight(int y) {
		return (y < point.getY() + (radius)) && (y > point.getY() - (radius));
	}

	public void switchSelection() {
		this.selectionStatus = !this.selectionStatus;
	}

	public boolean isSelected() {
		return this.selectionStatus;
	}

	public void translate(Point delta) {
		this.point.x = (int) (this.point.getX() + delta.x);
		this.point.y = (int) (this.point.getY() + delta.y);
	}
}
