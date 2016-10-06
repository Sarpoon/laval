package gui.panels;

import gui.frames.MainWindow;
import gui.listeners.action.CreateAndShowInvoiceAction;
import gui.listeners.action.ToggleAllDiscountsAction;
import gui.listeners.action.ToggleBossDiscountAction;
import gui.listeners.action.ToggleStudentDiscountAction;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import domain.basket.BasketControllerObserver;

@SuppressWarnings("serial")
public class RightPanel extends JPanel implements BasketControllerObserver {
	private MainWindow outer;
	private JTabbedPane tabPane;
	private JPanel innerPanel;
	private JLabel priceLabel;
	private JLabel nbrItemsLabel;
	private JButton billButton;
	private JCheckBox bossDiscountOption;
	private JCheckBox studentDiscountOption;
	private JCheckBox allDiscountOption;

	public RightPanel(MainWindow outer) {
		this.outer = outer;
		outer.controller.registerObserver(this);
		tabPane = new JTabbedPane();
		innerPanel = new JPanel();
		priceLabel = new JLabel();
		nbrItemsLabel = new JLabel();
		billButton = new JButton();
		bossDiscountOption = new JCheckBox();
		studentDiscountOption = new JCheckBox();
		allDiscountOption = new JCheckBox();
		buildUp();
	}

	private void buildUp() {
		int width = (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width * 0.13);
		setPreferredSize(new Dimension(width, 1));
		JLabel priceInfoLabel = new JLabel();
		JLabel nbrItemsInfoLabel = new JLabel();

		nbrItemsInfoLabel.setText("Nombre d'articles : ");
		nbrItemsInfoLabel.setPreferredSize(new Dimension((int) (width * 0.6),
				20));
		nbrItemsLabel.setPreferredSize(new Dimension((int) (width * 0.3), 20));
		nbrItemsLabel.setText("0");

		priceInfoLabel.setText("Sous total : ");
		priceInfoLabel.setPreferredSize(new Dimension((int) (width * 0.6), 20));
		priceLabel.setPreferredSize(new Dimension((int) (width * 0.3), 20));
		priceLabel.setText("0.0 $");

		billButton.setText("Facturer");
		billButton.addActionListener(new CreateAndShowInvoiceAction(outer));

		bossDiscountOption.setText("Escompte du patron");
		bossDiscountOption.setPreferredSize(new Dimension((int) (width * 0.9),
				15));
		bossDiscountOption
				.addActionListener(new ToggleBossDiscountAction(outer));

		studentDiscountOption.setText("Escompte etudiant");
		studentDiscountOption.setPreferredSize(new Dimension(
				(int) (width * 0.9), 15));
		studentDiscountOption
				.addActionListener(new ToggleStudentDiscountAction(outer));

		allDiscountOption.setText("Les deux");
		allDiscountOption.setPreferredSize(new Dimension((int) (width * 0.9),
				15));
		allDiscountOption
				.addActionListener(new ToggleAllDiscountsAction(outer));

		JPanel itemsPanel = new JPanel();
		itemsPanel.setLayout(new FlowLayout());
		itemsPanel.setPreferredSize(new Dimension(width, 20));
		itemsPanel.add(nbrItemsInfoLabel);
		itemsPanel.add(nbrItemsLabel);

		JPanel pricePanel = new JPanel();
		pricePanel.setPreferredSize(new Dimension(width, 20));
		pricePanel.add(priceInfoLabel);
		pricePanel.add(priceLabel);

		JPanel bossPanel = new JPanel();
		bossPanel.setPreferredSize(new Dimension(width, 80));
		bossPanel.add(bossDiscountOption);
		JPanel studentPanel = new JPanel();
		studentPanel.add(studentDiscountOption);
		JPanel allDiscountPanel = new JPanel();
		allDiscountPanel.add(allDiscountOption);

		ButtonGroup dealGroup = new ButtonGroup();
		dealGroup.add(bossDiscountOption);
		dealGroup.add(studentDiscountOption);
		dealGroup.add(allDiscountOption);

		JPanel billPanel = new JPanel();
		billPanel.setPreferredSize(new Dimension(width, 10));
		billPanel.setLayout(new BorderLayout());
		billPanel.add(billButton);

		innerPanel.setPreferredSize(new Dimension(width, 200));
		innerPanel.setLayout(new GridLayout(6, 0));
		innerPanel.add(itemsPanel);
		innerPanel.add(pricePanel);
		innerPanel.add(bossPanel);
		innerPanel.add(studentPanel);
		innerPanel.add(allDiscountPanel);
		innerPanel.add(billButton);

		tabPane.addTab("Informations", innerPanel);
		add(tabPane);
		setVisible(true);
	}

	@Override
	public void notifyUpdatedItems() {
		int numberOfFruits = outer.controller.getNumberOfFruits();
		double subTotal = outer.controller.getSubTotal();

		nbrItemsLabel.setText(String.valueOf(numberOfFruits));
		String priceString = new DecimalFormat("##.##").format(subTotal);
		priceLabel.setText(String.valueOf(priceString) + " $");
		repaint();
	}

	@Override
	public void notifyInvoiceCreated() {
		// Rien a faire lors de la creation d'une facture.
	}
}
