package gui.listeners.action;

import gui.frames.MainWindow;
import gui.frames.MainWindow.ApplicationMode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdditionModeAction implements ActionListener {

	private final MainWindow outer;

	public AdditionModeAction(MainWindow outer) {
		this.outer = outer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		outer.setMode(ApplicationMode.ADD);
	}
}
