package gui.listeners.action;

import gui.frames.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import drawing.BasketDrawer;
import drawing.ItemDrawerFactory;
import drawing.basic.BasicBasketDrawer;
import drawing.basic.BasicItemDrawerFactory;

public class BasicDrawingModeAction implements ActionListener {

	private final MainWindow outer;

	public BasicDrawingModeAction(MainWindow outer) {
		this.outer = outer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ItemDrawerFactory fruitDrawerFactory = new BasicItemDrawerFactory(outer);
		BasketDrawer basketDrawer = new BasicBasketDrawer(outer);
		outer.drawingPanel.setDrawers(fruitDrawerFactory, basketDrawer);
	}
}
