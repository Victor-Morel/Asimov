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

	private JButton launchSimulation;

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
		JMenuItem New = new JMenuItem("Nouveau");
		JMenuItem Save = new JMenuItem("Sauvegarder");
		JMenuItem LoadXml = new JMenuItem("Charger XML");
		JMenuItem LoadImage = new JMenuItem("Charger Image");
		file.add(New);
		file.add(Save);
		file.add(LoadXml);
		file.add(LoadImage);


		addNodeFire.addActionListener(controlAction.getControlNode());
		addNode.addActionListener(controlAction.getControlNode());
		addEdgeEscarpe.addActionListener(controlAction.getControlEdge());
		addEdgePlat.addActionListener(controlAction.getControlEdge());
		addEdgeInonde.addActionListener(controlAction.getControlEdge());
		addRobotChenille.addActionListener(controlAction.getControlRobot());
		addRobotPates.addActionListener(controlAction.getControlRobot());
		addRobotToutTerrain.addActionListener(controlAction.getControlRobot());
		launchSimulation.addActionListener(controlAction.getControlWindows());
		Save.addActionListener(controlAction.getControlWindows());
		New.addActionListener(controlAction.getControlWindows());
		LoadXml.addActionListener(controlAction.getControlWindows());
		LoadImage.addActionListener(controlAction.getControlWindows());


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
