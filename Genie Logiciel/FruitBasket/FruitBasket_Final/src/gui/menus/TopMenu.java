package gui.menus;

import gui.frames.MainWindow;
import gui.listeners.action.BasicDrawingModeAction;
import gui.listeners.action.CloseApplicationAction;
import gui.listeners.action.ComplexDrawingModeAction;
import gui.listeners.action.ReadMeOpenAction;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class TopMenu extends JMenuBar {

	private final MainWindow outer;
	private JMenuItem about;
	private JMenuItem save;
	private JMenuItem open;
	private JMenuItem quit;
	private JMenuItem basicDrawing;
	private JMenuItem complexDrawing;

	private JMenu fileMenu;
	private JMenu optionMenu;
	private JMenu aboutMenu;

	public TopMenu(MainWindow outer) {
		this.outer = outer;

		about = new JMenuItem("Informations importantes");
		save = new JMenuItem("Sauvegarder");
		open = new JMenuItem("Ouvrir");
		quit = new JMenuItem("Quitter");
		basicDrawing = new JMenuItem("Affichage simple");
		complexDrawing = new JMenuItem("Affichage détaillé");

		fileMenu = new JMenu("Fichier");
		optionMenu = new JMenu("Options");
		aboutMenu = new JMenu("A Lire");

		buildUp();
	}

	private void buildUp() {
		quit.addActionListener(new CloseApplicationAction());
		about.addActionListener(new ReadMeOpenAction());
		basicDrawing.addActionListener(new BasicDrawingModeAction(outer));
		complexDrawing.addActionListener(new ComplexDrawingModeAction(outer));

		fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.add(new JSeparator());
		fileMenu.add(quit);

		optionMenu.add(basicDrawing);
		optionMenu.add(complexDrawing);

		aboutMenu.add(about);

		add(fileMenu);
		add(optionMenu);
		add(aboutMenu);
	}
}
