package drawing.basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import domain.basket.BasketItem;
import domain.basket.ItemDescription;
import drawing.ItemDrawer;

public class BasicItemDrawer implements ItemDrawer {

	private int radius;
	private Color color;

	public BasicItemDrawer(ItemDescription itemDescription) {
		this.radius = itemDescription.radius;
		this.color = itemDescription.color;
	}

	public void drawFruit(Graphics g, BasketItem item) {
		Point fruitPoint = item.getPoint();
		if (item.isSelected()) {
			g.setColor(new Color(255, 0, 0));
			int offsetRadius = radius + 1;
			g.fillOval((int) fruitPoint.getX() - offsetRadius,
					(int) fruitPoint.getY() - offsetRadius, offsetRadius * 2,
					offsetRadius * 2);
		}
		g.setColor(color);
		g.fillOval((int) fruitPoint.getX() - radius, (int) fruitPoint.getY()
				- radius, radius * 2, radius * 2);
	}
}
