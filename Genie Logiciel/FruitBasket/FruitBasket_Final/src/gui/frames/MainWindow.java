package gui.frames;

import gui.menus.TopMenu;
import gui.panels.CenterPanel;
import gui.panels.DrawingPanel;
import gui.panels.RightPanel;
import gui.panels.TopPanel;

import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import domain.basket.BasketController;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private TopMenu topMenu;

	private JPanel mainPanel;

	private JSplitPane centerSplitPane;
	private TopPanel topPanel;
	private RightPanel rightPanel;
	public DrawingPanel drawingPanel;
	private CenterPanel centerPanel;

	public BasketController controller;
	private ApplicationMode applicationMode;
	public Point actualMousePoint;

	public enum ApplicationMode {
		SELECT, ADD
	}

	public MainWindow() {
		this.applicationMode = ApplicationMode.SELECT;
		controller = new BasketController();
		mainPanel = new JPanel();
		rightPanel = new RightPanel(this);
		topPanel = new TopPanel(this);
		centerPanel = new CenterPanel(this);
		drawingPanel = new DrawingPanel(this);
		topMenu = new TopMenu(this);
		actualMousePoint = new Point();
		initializeWindow();
	}

	public void setMode(ApplicationMode mode) {
		applicationMode = mode;
	}

	private void initializeWindow() {
		setTitle("Fruit Basket");
		int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		setSize(screenWidth, screenHeight);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(topMenu);
		setContentPane(buildContent());
	}

	private JScrollPane buildContent() {
		setJMenuBar(topMenu);

		centerPanel.add(drawingPanel);

		centerSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				centerPanel, rightPanel);
		centerSplitPane.setOneTouchExpandable(true);
		centerSplitPane.setContinuousLayout(true);

		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(centerSplitPane, BorderLayout.CENTER);
		mainPanel.add(topPanel, BorderLayout.NORTH);

		return new JScrollPane(mainPanel);
	}

	public String getItemType() {
		return this.topPanel.getItemType();
	}

	public ApplicationMode getActualMode() {
		return this.applicationMode;
	}
}
