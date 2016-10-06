package drawing.complex;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import domain.basket.BasketItem;
import domain.basket.ItemDescription;
import drawing.ItemDrawer;

public class ComplexItemDrawer implements ItemDrawer {

	private BufferedImage orangeImage;

	public ComplexItemDrawer(ItemDescription itemDescription) {
		loadImage(itemDescription.imagePath);
	}

	private void loadImage(String filepath) {
		try {
			orangeImage = ImageIO.read(new File(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawFruit(Graphics g, BasketItem item) {
		Point fruitPoint = item.getPoint();
		int width = (int) fruitPoint.getX() - orangeImage.getWidth(null) / 2;
		int height = (int) fruitPoint.getY() - orangeImage.getHeight(null) / 2;
		if (item.isSelected()) {
			g.setColor(new Color(255, 0, 0));
			g.drawRect(width - 5, height - 5, orangeImage.getWidth(null) + 10,
					orangeImage.getHeight(null) + 10);
		}
		g.drawImage(orangeImage, width, height, null);
	}
}
