package view;


import controller.Controller;
import controller.ControllerMouse;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;


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

	JCheckBoxMenuItem AStar, Dijkstra;

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
	public GUI(Controller controlAction, ControllerMouse controlMouse) {

		super("Asimov");

		this.controlMouse = controlMouse;

		setContentPane(rootPanel);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu file = new JMenu("File");
		JMenu run = new JMenu("Run");
		menuBar.add(file);
		menuBar.add(run);

		JMenuItem New = new JMenuItem("Nouveau");
		JMenuItem Save = new JMenuItem("Sauvegarder");
		JMenuItem LoadXml = new JMenuItem("Charger XML");
		JMenuItem LoadImage = new JMenuItem("Charger Image");
		JMenuItem RunAlgo = new JMenuItem("Lancer Simulation");
		JMenuItem StopAlgo = new JMenuItem("Stop Simulation");
		JCheckBoxMenuItem Pyromano = new JCheckBoxMenuItem("Mode Pyromane");
		AStar = new JCheckBoxMenuItem("Strategie AStar");
		Dijkstra = new JCheckBoxMenuItem("Strategie Dijkstra");
		AStar.setSelected(true);

		file.add(New);
		file.add(Save);
		file.add(LoadXml);
		file.add(LoadImage);
		run.add(RunAlgo);
		run.add(StopAlgo);
		run.add(AStar);
		run.add(Dijkstra);
		run.add(Pyromano);


		addNodeFire.addActionListener(controlAction.getControlNode());
		addNode.addActionListener(controlAction.getControlNode());

		addEdgeEscarpe.addActionListener(controlAction.getControlEdge());
		addEdgePlat.addActionListener(controlAction.getControlEdge());
		addEdgeInonde.addActionListener(controlAction.getControlEdge());

		addRobotChenille.addActionListener(controlAction.getControlRobot());
		addRobotPates.addActionListener(controlAction.getControlRobot());
		addRobotToutTerrain.addActionListener(controlAction.getControlRobot());

		StopAlgo.addActionListener(controlAction.getControlWindows());
		RunAlgo.addActionListener(controlAction.getControlWindows());
		Save.addActionListener(controlAction.getControlWindows());
		New.addActionListener(controlAction.getControlWindows());
		LoadXml.addActionListener(controlAction.getControlWindows());
		LoadImage.addActionListener(controlAction.getControlWindows());
		Dijkstra.addActionListener(controlAction.getControlWindows());
		AStar.addActionListener(controlAction.getControlWindows());
		Pyromano.addActionListener(controlAction.getControlWindows());


		addEdgePlat.setForeground(Color.black);
		addEdgeEscarpe.setForeground(Color.CYAN);
		addEdgeInonde.setForeground(Color.MAGENTA);

		addRobotPates.setForeground(Color.GREEN);
		addRobotChenille.setForeground(Color.RED);
		addRobotToutTerrain.setForeground(Color.BLACK);



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


	public JCheckBoxMenuItem getDijkstra() {
		return Dijkstra;
	}

	public JCheckBoxMenuItem getAStar() {
		return AStar;
	}
}
