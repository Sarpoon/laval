package gui.panels;

import gui.frames.MainWindow;
import gui.listeners.mouse.ModeHandleListener;
import gui.listeners.mouse.MotionListener;
import gui.listeners.mouse.WheelListener;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import domain.basket.BasketControllerObserver;
import drawing.BasketDrawer;
import drawing.ItemDrawerFactory;
import drawing.MainDrawer;
import drawing.complex.ComplexBasketDrawer;
import drawing.complex.ComplexItemDrawerFactory;

@SuppressWarnings("serial")
public class DrawingPanel extends JPanel implements BasketControllerObserver {

	private final MainWindow mainWindow;
	private ItemDrawerFactory itemDrawerFactory;
	private BasketDrawer basketDrawer;
	public Dimension initialDimension;

	public DrawingPanel(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		this.itemDrawerFactory = new ComplexItemDrawerFactory(mainWindow);
		this.basketDrawer = new ComplexBasketDrawer(mainWindow);
		mainWindow.controller.registerObserver(this);
		buildUp();
	}

	private void buildUp() {
		addMouseListener(new ModeHandleListener(mainWindow));
		addMouseMotionListener(new MotionListener(mainWindow));
		addMouseWheelListener(new WheelListener());

		setBorder(new javax.swing.border.BevelBorder(BevelBorder.LOWERED));
		int width = (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width * 0.85);
		setPreferredSize(new Dimension(width, 1));
		setVisible(true);
		int height = (int) (width * 0.6);
		initialDimension = new Dimension(width, height);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		MainDrawer mainDrawer = new MainDrawer(mainWindow.controller,
				itemDrawerFactory, basketDrawer, initialDimension);
		mainDrawer.draw(g);
	}

	public void setDrawers(ItemDrawerFactory fruitDrawerFactory,
			BasketDrawer basketDrawer) {
		this.itemDrawerFactory = fruitDrawerFactory;
		this.basketDrawer = basketDrawer;
		repaint();
	}

	@Override
	public void notifyUpdatedItems() {
		repaint();
	}

	@Override
	public void notifyInvoiceCreated() {
	}
}
