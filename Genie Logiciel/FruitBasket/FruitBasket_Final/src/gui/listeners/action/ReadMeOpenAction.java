package gui.listeners.action;

import gui.frames.ReadMeWindow;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class ReadMeOpenAction extends AbstractAction {

	@Override
	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unused")
		ReadMeWindow readMeWindow = new ReadMeWindow();
	}

}
