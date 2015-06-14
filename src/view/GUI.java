package view;


import controller.ControllerAction;
import controller.ControllerMouse;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;


public class GUI extends JFrame {

	/**
	 * JPANEL principale
	 */
	private JPanel rootPanel;

	/**
	 * JButtons de la fenetre
	 */
	private JButton addNodeFire, addNode,
			addRobotToutTerrain, addRobotChenille, addRobotPates,
			addEdgeEscarpe, addEdgePlat, addEdgeInonde;

	/**
	 *  SheetDisplay
	 */
	private SheetDisplay sheetDisplay;

	/**
	 * Controlleur de la souris
	 */
	private ControllerMouse controlMouse;

	/**
	 * Conscruit l interface graphique
	 * @param controlAction Controlleur des actions
	 * @param controlMouse Controlleur de la souris
	 */
	public GUI(ControllerAction controlAction, ControllerMouse controlMouse) {

		super("Asimov");

		this.controlMouse = controlMouse;

		setContentPane(rootPanel);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu file = new JMenu("File");
		menuBar.add(file);
		JMenuItem save = new JMenuItem("Save");
		JMenuItem LoadXml = new JMenuItem("Load XML");
		JMenuItem LoadImage = new JMenuItem("Load Image");
		file.add(save);
		file.add(LoadXml);
		file.add(LoadImage);


		addNodeFire.addActionListener(controlAction);
		addNode.addActionListener(controlAction);
		addEdgeEscarpe.addActionListener(controlAction);
		addEdgePlat.addActionListener(controlAction);
		addEdgeInonde.addActionListener(controlAction);
		addRobotChenille.addActionListener(controlAction);
		addRobotPates.addActionListener(controlAction);
		addRobotToutTerrain.addActionListener(controlAction);
		save.addActionListener(controlAction);
		LoadXml.addActionListener(controlAction);
		LoadImage.addActionListener(controlAction);


		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setMinimumSize(new DimensionUIResource(600, 600));
		setResizable(false);
		setFocusable(true);
		setVisible(true);
	}

	/**
	 * Initalise les components personalise
	 */
	private void createUIComponents() {
		sheetDisplay = new SheetDisplay(controlMouse);

	}

	/**
	 * Acceder au SheetDisplay
	 * @return sheetDisplay
	 */
	public SheetDisplay getSheetDisplay() {
		return sheetDisplay;
	}
}
