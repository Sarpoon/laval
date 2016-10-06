package gui;

import gui.frames.MainWindow;

import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;

public class Main {
	public static void main(String[] args) {
		UIManager.put("Synthetica.window.decoration", Boolean.FALSE);
		try {
			UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
		} catch (UnsupportedLookAndFeelException | ParseException e) {
			e.printStackTrace();
		}
		MainWindow mainWindow = new MainWindow();
		mainWindow.setExtendedState(mainWindow.getExtendedState()
				| JFrame.MAXIMIZED_BOTH);
		mainWindow.setVisible(true);
	}
}
