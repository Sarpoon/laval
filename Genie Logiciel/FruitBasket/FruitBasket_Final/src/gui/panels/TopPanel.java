package gui.panels;

import gui.frames.MainWindow;
import gui.listeners.action.AdditionModeAction;
import gui.listeners.action.SelectionModeAction;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import util.ItemsCollection;

@SuppressWarnings("serial")
public class TopPanel extends JPanel {

	final MainWindow outer;

	ButtonGroup group;
	private JToggleButton selectionButton;
	private JToggleButton additionButton;
	private JComboBox<Object> itemTypeBox;

	public TopPanel(MainWindow outer) {
		this.outer = outer;
		group = new ButtonGroup();
		selectionButton = new JToggleButton("Mode sélection");
		additionButton = new JToggleButton("Mode Addition");
		List<String> items = ItemsCollection.getInstance().getTypes();
		itemTypeBox = new JComboBox<Object>(new DefaultComboBoxModel<Object>(items.toArray()));
		buildUp();
	}

	private void buildUp() {
		setLayout(new FlowLayout((FlowLayout.LEFT)));

		selectionButton.addActionListener(new SelectionModeAction(outer));
		additionButton.addActionListener(new AdditionModeAction(outer));

		add(selectionButton);
		add(additionButton);

		group.add(selectionButton);
		group.add(additionButton);

		add(itemTypeBox);
	}

	public String getItemType() {
		return (String) this.itemTypeBox.getSelectedItem();
	}
}
