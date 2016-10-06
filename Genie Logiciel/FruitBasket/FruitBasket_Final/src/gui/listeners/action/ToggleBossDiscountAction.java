package gui.listeners.action;

import gui.frames.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToggleBossDiscountAction implements ActionListener {

	private final MainWindow outer;

	public ToggleBossDiscountAction(MainWindow outer) {
		this.outer = outer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		outer.controller.setBossDiscount();
	}
}
