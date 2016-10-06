package gui.frames;

import gui.listeners.action.QuitJWindowAction;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JWindow;

import util.PropertiesFinder;

@SuppressWarnings("serial")
public class ReadMeWindow extends JWindow {

	private JPanel centerPanel;
	private JPanel southPanel;

	private JTextPane textPane;
	private JScrollPane scrollTextPane;

	private Dimension textPaneDimension;

	private JButton quitButton;

	public ReadMeWindow() {
		centerPanel = new JPanel();
		southPanel = new JPanel();
		textPane = new JTextPane();
		scrollTextPane = new JScrollPane(textPane);
		textPaneDimension = new Dimension(450, 450);
		quitButton = new JButton("Quitter");
		initializeWindow();
	}

	private void initializeWindow() {
		setSize(500, 500);
		validate();
		setVisible(true);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		textPane.setPreferredSize(textPaneDimension);
		scrollTextPane.setPreferredSize(textPaneDimension);

		textPane.setContentType("text/html");
		String message = (String) PropertiesFinder.getInstance().getProperty(
				"read_me.text");
		textPane.setText(message);

		quitButton.addActionListener(new QuitJWindowAction(this));

		centerPanel.add(scrollTextPane);
		southPanel.add(quitButton);

		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
	}

}
