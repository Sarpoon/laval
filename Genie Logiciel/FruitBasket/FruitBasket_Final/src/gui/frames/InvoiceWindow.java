package gui.frames;

import gui.listeners.action.QuitJWindowAction;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JWindow;

@SuppressWarnings("serial")
public class InvoiceWindow extends JWindow {

	private String invoiceDescription;
	private JPanel centerPanel;
	private JPanel southPanel;

	private JTextPane textPane;
	private JScrollPane scrollTextPane;

	private Dimension textPaneDimension;

	private JButton quitButton;

	public InvoiceWindow(String invoiceDescription) {
		this.invoiceDescription = invoiceDescription;
		centerPanel = new JPanel();
		southPanel = new JPanel();
		textPane = new JTextPane();
		scrollTextPane = new JScrollPane(textPane);
		textPaneDimension = new Dimension(200, 300);
		quitButton = new JButton("Quitter");
		initializeWindow();
	}

	private void initializeWindow() {
		setSize(220, 320);
		validate();
		setVisible(true);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		textPane.setPreferredSize(textPaneDimension);
		scrollTextPane.setPreferredSize(textPaneDimension);

		textPane.setText(invoiceDescription);

		quitButton.addActionListener(new QuitJWindowAction(this));

		centerPanel.add(scrollTextPane);
		southPanel.add(quitButton);

		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
	}
}
