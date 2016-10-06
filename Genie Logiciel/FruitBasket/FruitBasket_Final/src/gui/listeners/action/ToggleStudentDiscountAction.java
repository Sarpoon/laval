package gui.listeners.action;

import gui.frames.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToggleStudentDiscountAction implements ActionListener {

	private final MainWindow outer;

	public ToggleStudentDiscountAction(MainWindow outer) {
		this.outer = outer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		outer.controller.setStudentDiscount();
	}
}
