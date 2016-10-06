package drawing.complex;

import gui.frames.MainWindow;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import drawing.BasketDrawer;

public class ComplexBasketDrawer implements BasketDrawer {

	private BufferedImage basketImage;

	public ComplexBasketDrawer(MainWindow outer) {
		try {
			basketImage = ImageIO
					.read(new File("ressources/images/basket.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawBasket(Graphics g, Dimension dimension) {
		int width = (int) dimension.getWidth();
		int height = (int) dimension.getHeight();

		g.drawImage(basketImage,
				(int) ((width / 2) - basketImage.getWidth(null) / 2),
				(int) ((height / 1.5) - basketImage.getHeight(null) / 2), null);
	}
}
