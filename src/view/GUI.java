package view;


import controller.ControllerAction;
import controller.ControllerMouse;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;


public class GUI extends JFrame {

	private JPanel rootPanel;
	private javax.swing.JToolBar jToolBar;
	private JButton addNodeFire, addNode, addEdgeEscarpe, addEdgePlat, addEdgeInonde;
	private SheetDisplay sheetDisplay;


	public GUI(ControllerAction controlAction, ControllerMouse controlMouse) {

		super("Asimov");

		setContentPane(rootPanel);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu file = new JMenu("File");
		menuBar.add(file);
		JMenuItem save = new JMenuItem("Save");
		JMenuItem load = new JMenuItem("Load");
		file.add(save);
		file.add(load);

		addMouseListener(controlMouse);
		addNodeFire.addActionListener(controlAction);
		addNode.addActionListener(controlAction);
		addEdgeEscarpe.addActionListener(controlAction);
		addEdgePlat.addActionListener(controlAction);
		addEdgeInonde.addActionListener(controlAction);
		save.addActionListener(controlAction);
		load.addActionListener(controlAction);


		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setMinimumSize(new DimensionUIResource(600, 600));
		setResizable(false);
		setFocusable(true);
		setVisible(true);
	}

	private void createUIComponents() {
		sheetDisplay = new SheetDisplay();

	}

	public SheetDisplay getSheetDisplay() {
		return sheetDisplay;
	}
}
