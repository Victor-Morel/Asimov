package controller;

import model.graph.Edge;
import model.graph.Node;
import model.graph.TypeEdge;
import model.robot.AllTerrainRobot;
import model.robot.CaterpillarRobot;
import model.robot.LeggedRobot;
import model.robot.TypeRecherche;
import utils.FileManager;
import view.ChooseFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class ControllerAction implements ActionListener {

    String path;
    Boolean node, fire, plat, inonde, escarpe,
            terrain, pate, chenille;
    Controller control;
    Node node1, node2;

    public ControllerAction(String _path, Controller control) {
        super();
        this.path = _path;
        this.control = control;
        initialization();
    }

    private void initialization() {
        node1 = new Node();
        node2 = new Node();
        node = false;
        fire = false;
        plat = false;
        inonde = false;
        escarpe = false;
        terrain = false;
        pate = false;
        chenille = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().matches("Noeud")) {
            this.initialization();
            node = true;
        }
        else if (e.getActionCommand().matches("Feu")) {
            this.initialization();
            fire = true;
        }
        else if (e.getActionCommand().matches("Plat")) {
            this.initialization();
            plat = true;
        }
        else if (e.getActionCommand().matches("Escarpe")) {
            this.initialization();
            escarpe = true;
        }
        else if (e.getActionCommand().matches("Inonde")) {
            this.initialization();
            inonde = true;
        }
        else if (e.getActionCommand().matches("Save")) {
            ChooseFile chooseFile = new ChooseFile();
            File file = chooseFile.selectFile(this.path);
            if (null != file) {
                FileManager.saveFileManager(file, control.getGraph());
            }
        }
        else if (e.getActionCommand().matches("Load")) {
            ChooseFile chooseFile = new ChooseFile();
            File file = chooseFile.selectFile(this.path);
            if (null != file) {
                control.setGraph(FileManager.loadFileManager(file));
                control.displayGraphe();
            }
        }
        else if (e.getActionCommand().matches("Tout Terrain")) {
            this.initialization();
            terrain = true;
        }
        else if (e.getActionCommand().matches("Chenille")) {
            this.initialization();
            chenille = true;
        }
        else if (e.getActionCommand().matches("Pates")) {
            this.initialization();
            pate = true;
        }

    }

    public void addNode(int x, int y) {

        int id = -1;
        if (node) {
            control.addNode(new Node(x, y));
        }
        else if (fire) {
            control.addNode(new Node(x, y, Node.FIRE_DEFAULT_TEMPERATURE));
        }
    }

    protected Node clickOnNode(int x, int y) {
        for (Node n : control.getGraph().getAllNodes()) {
            for (int i = -10; i < 10; i++) {
                for (int j = -10; j < 10; j++) {
                    if ((x == (int) n.getX() + i) && (y == (int) n.getY() + j)) {
                        return n;
                    }
                }
            }
        }
        return new Node();
    }

    protected Node selectCurrentNode(int x, int y) {
        Node currentNode;
        currentNode = clickOnNode(x, y);
        currentNode.setCurrentNode(true);
        checkOnlyNode(currentNode.getID());
        return currentNode;
    }

    public void addEdge(Node currentNode) {
        double valuation = 0;

        if (-1 == node1.getID()) {
            node1 = currentNode;
        } else if (-1 == node2.getID()) {
            node2 = currentNode;

            if (plat) {
                control.addEdge(new Edge(node1, node2, valuation, TypeEdge.PLAT));
            }
            else if (escarpe) {
                control.addEdge(new Edge(node1, node2, valuation, TypeEdge.ESCARPE));
            }
            else if (inonde) {
                control.addEdge(new Edge(node1, node2, valuation, TypeEdge.INONDE));
            }


            node1 = new Node();
            node2 = new Node();
        }
    }

    public void addRobot(Node currentNode) {
        int _capacity = 10;
        TypeRecherche type = TypeRecherche.ASTAR;

        if (-1 == node1.getID()) {
            node1 = currentNode;

            if (terrain) {
                control.addRobot(new AllTerrainRobot(type, _capacity));
            }
            else if (chenille) {
                control.addRobot(new CaterpillarRobot(type, _capacity));
            }
            else if (pate) {
                control.addRobot(new LeggedRobot(type, _capacity));
            }
        }
    }

    private void checkOnlyNode(int currentNode) {
        for (Node n : control.getGraph().getAllNodes()) {
            if (n.getID() != currentNode && n.isCurrentNode()) {
                n.setCurrentNode(false);
            }
        }
    }
}


