package controller;

import model.graph.Edge;
import model.graph.Node;
import model.graph.TypeEdge;
import model.graph.TypeNode;
import utils.FileManager;
import view.ChooseFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControllerAction implements ActionListener {

    String path;
    Boolean node, fire, plat, inonder, escarpe,
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
        inonder = false;
        escarpe = false;
        terrain = false;
        pate = false;
        chenille = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().matches("Noeud")) {
            node = true;
            fire = false;
            plat = false;
            inonder = false;
            escarpe = false;
            terrain = false;
            pate = false;
            chenille = false;
        }
        if (e.getActionCommand().matches("Feu")) {
            node = false;
            fire = true;
            plat = false;
            inonder = false;
            escarpe = false;
            terrain = false;
            pate = false;
            chenille = false;
        }
        if (e.getActionCommand().matches("Plat")) {
            node1 = new Node();
            node2 = new Node();
            node = false;
            fire = false;
            plat = true;
            inonder = false;
            escarpe = false;
            terrain = false;
            pate = false;
            chenille = false;
        }
        if (e.getActionCommand().matches("Escarpe")) {
            node1 = new Node();
            node2 = new Node();
            node = false;
            fire = false;
            plat = false;
            inonder = false;
            escarpe = true;
            terrain = false;
            pate = false;
            chenille = false;
            inonder = false;
        }
        if (e.getActionCommand().matches("Inonde")) {
            node1 = new Node();
            node2 = new Node();
            node = false;
            fire = false;
            plat = false;
            inonder = true;
            escarpe = false;
            terrain = false;
            pate = false;
            chenille = false;
        }
        if (e.getActionCommand().matches("Save")) {
            ChooseFile chooseFile = new ChooseFile();
            FileManager.saveFileManager(chooseFile.selectFile(this.path), control.getGraph());
        }
        if (e.getActionCommand().matches("Load")) {
            ChooseFile chooseFile = new ChooseFile();
            control.setGraph(FileManager.loadFileManager(chooseFile.selectFile(this.path)));
            control.displayGraphe();
        }
        if (e.getActionCommand().matches("Tout Terrain")) {
            node = false;
            fire = false;
            plat = false;
            inonder = false;
            escarpe = false;
            terrain = true;
            pate = false;
            chenille = false;
        }
        if (e.getActionCommand().matches("Chenille")) {
            node = false;
            fire = false;
            plat = false;
            inonder = false;
            escarpe = false;
            terrain = false;
            pate = false;
            chenille = true;
        }
        if (e.getActionCommand().matches("Pates")) {
            node = false;
            fire = false;
            plat = false;
            inonder = false;
            escarpe = false;
            terrain = false;
            pate = true;
            chenille = false;
        }

    }

    public void addNode(int x, int y) {

        int id = -1;
        if (node) {
            control.addNode(new Node(id, x, y));
            node = false;
        }
        if (fire) {
            control.addNode(new Node(id, x, y, TypeNode.INCENDIE));
            fire = false;
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
                plat = false;
            }
            if (escarpe) {
                control.addEdge(new Edge(node1, node2, valuation, TypeEdge.ESCARPE));
                escarpe = false;
            }
            if (inonder) {
                control.addEdge(new Edge(node1, node2, valuation, TypeEdge.INONDE));
                inonder = false;
            }
            node1 = new Node();
            node2 = new Node();
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


