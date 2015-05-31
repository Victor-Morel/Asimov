package view;


import utils.FileManager;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.DimensionUIResource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI extends JFrame {

	String path;
	private JPanel panel1;
	private javax.swing.JToolBar jToolBar;
	private JButton addNodeFire;
	private JButton addNode;
	private JButton addEdgeEscarpe;
	private JButton addEdgePlat;
	private JButton addEdgeInonde;


	public GUI(String path) {

		super("Asimov");

		add(panel1);


		this.path = path;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new DimensionUIResource(600, 600));

		setResizable(false);
		setFocusable(true);
		setVisible(true);


		addMenuBar();
	}



	public void addMenuBar() {

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu file = new JMenu("File");
		menuBar.add(file);

		JMenuItem save = new JMenuItem("Save");
		JMenuItem load = new JMenuItem("Load");
		file.add(save);
		file.add(load);


		class edgeInondeAction implements ActionListener {
			public void actionPerformed(ActionEvent e) {

			}
		}

		class edgePlatAction implements ActionListener {
			public void actionPerformed(ActionEvent e) {

			}
		}

		class edgeEscarpeAction implements ActionListener {
			public void actionPerformed(ActionEvent e) {

			}
		}

		class nodeAction implements ActionListener {
			public void actionPerformed(ActionEvent e) {

			}
		}

		class fireAction implements ActionListener {
			public void actionPerformed(ActionEvent e) {

			}
		}

		class loadAction implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				new FileManager(setJFileChooser());
			}
		}


		class saveAction implements ActionListener {
			public void actionPerformed(ActionEvent e) {

			}
		}


		addNodeFire.addActionListener(new fireAction());
		addNode.addActionListener(new nodeAction());
		addEdgeEscarpe.addActionListener(new edgeEscarpeAction());
		addEdgePlat.addActionListener(new edgePlatAction());
		addEdgeInonde.addActionListener(new edgeInondeAction());
		save.addActionListener(new saveAction());
		load.addActionListener(new loadAction());

	}

	public java.io.File setJFileChooser() {
		while (true) {
			JFileChooser chooser = new JFileChooser(path);
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"XML", "xml");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				return chooser.getSelectedFile();
			}
		}
	}

}
