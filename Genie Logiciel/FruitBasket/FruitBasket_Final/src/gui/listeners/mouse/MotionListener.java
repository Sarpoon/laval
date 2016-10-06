package gui.listeners.mouse;

import gui.frames.MainWindow;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.SwingUtilities;

public class MotionListener extends MouseMotionAdapter {
	private MainWindow mainWindow;
	private Point delta;

	public MotionListener(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		this.delta = new Point();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			delta.setLocation((e.getX() - mainWindow.actualMousePoint.x),
					(e.getY() - mainWindow.actualMousePoint.y));
			mainWindow.controller.updateSelectedItemsPositions(delta);
			mainWindow.actualMousePoint = e.getPoint();
		}
	}
}
