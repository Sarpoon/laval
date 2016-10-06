package gui.listeners.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class CloseApplicationAction extends AbstractAction {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
