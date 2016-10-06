package gui.listeners.action;

import gui.frames.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToggleAllDiscountsAction implements ActionListener {

	private final MainWindow outer;

	public ToggleAllDiscountsAction(MainWindow outer) {
		this.outer = outer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		outer.controller.setAllDiscount();
	}
}
