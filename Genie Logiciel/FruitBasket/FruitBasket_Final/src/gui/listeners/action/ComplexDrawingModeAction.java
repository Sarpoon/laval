package gui.listeners.action;

import gui.frames.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import drawing.BasketDrawer;
import drawing.ItemDrawerFactory;
import drawing.complex.ComplexBasketDrawer;
import drawing.complex.ComplexItemDrawerFactory;

public class ComplexDrawingModeAction implements ActionListener {

	private final MainWindow outer;

	public ComplexDrawingModeAction(MainWindow outer) {
		this.outer = outer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ItemDrawerFactory fruitDrawerFactory = new ComplexItemDrawerFactory(
				outer);
		BasketDrawer basketDrawer = new ComplexBasketDrawer(outer);
		outer.drawingPanel.setDrawers(fruitDrawerFactory, basketDrawer);
	}
}
