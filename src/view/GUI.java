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
	private JLabel JLabelEdge;
	private JLabel JLabelNode;

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
		JMenu option = new JMenu("Options");
		menuBar.add(file);
		menuBar.add(run);
		menuBar.add(option);

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
		run.add(Pyromano);

		option.add(AStar);
		option.add(Dijkstra);



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


		addNodeFire.setForeground(Color.RED);

		addEdgePlat.setForeground(Color.black);
		addEdgeInonde.setForeground(new Color(0, 29,255));
		addEdgeEscarpe.setForeground(new Color(0, 136, 32));

		addRobotPates.setForeground(new Color(0, 136, 32));
		addRobotChenille.setForeground(new Color(0, 29,255));
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
