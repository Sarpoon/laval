package gui.panels;

import gui.frames.MainWindow;

import java.awt.BorderLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CenterPanel extends JPanel {

	public CenterPanel(MainWindow outer) {
		buildUp();
	}

	private void buildUp() {
		setLayout(new BorderLayout());
	}
}
