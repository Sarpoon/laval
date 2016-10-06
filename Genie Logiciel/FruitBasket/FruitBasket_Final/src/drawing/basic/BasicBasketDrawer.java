package drawing.basic;

import gui.frames.MainWindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import drawing.BasketDrawer;

public class BasicBasketDrawer implements BasketDrawer {

	public BasicBasketDrawer(MainWindow outer) {
	}

	public void drawBasket(Graphics g, Dimension dimension) {
		int width = (int) dimension.getWidth();
		int height = (int) dimension.getHeight();
		g.setColor(new Color(140, 98, 57));
		g.fillRect(width / 4, (int) (height / 1.75), width / 2, height / 4);
	}
}
