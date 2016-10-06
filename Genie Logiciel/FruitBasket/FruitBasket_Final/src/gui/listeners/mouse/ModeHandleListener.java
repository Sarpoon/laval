package gui.listeners.mouse;

import gui.frames.MainWindow;
import gui.frames.MainWindow.ApplicationMode;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

public class ModeHandleListener extends MouseAdapter {

	final MainWindow mainWindow;

	public ModeHandleListener(MainWindow outer) {
		this.mainWindow = outer;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point mousePoint = e.getPoint();
		mainWindow.actualMousePoint = e.getPoint();
		ApplicationMode actualMode = mainWindow.getActualMode();
		if (actualMode == ApplicationMode.ADD) {
			String type = mainWindow.getItemType();
			mainWindow.controller.addItem(mousePoint, type);
		} else if (actualMode == ApplicationMode.SELECT
				&& SwingUtilities.isLeftMouseButton(e)) {
			mainWindow.controller.switchSelection(e.getPoint().x,
					e.getPoint().y);
		}
	}
}
