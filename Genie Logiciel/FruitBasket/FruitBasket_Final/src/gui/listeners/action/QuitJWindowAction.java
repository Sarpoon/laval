package gui.listeners.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JWindow;

@SuppressWarnings("serial")
public class QuitJWindowAction extends AbstractAction {

	private JWindow window;

	public QuitJWindowAction(JWindow windowToQuit) {
		window = windowToQuit;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		window.dispose();

	}

}
