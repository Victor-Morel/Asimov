package controller;

import model.graph.Node;
import model.graph.TypeNode;
import utils.FileManager;
import view.ChooseFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControllerAction implements ActionListener {

    String path;
    Boolean node, fire, plat, inonder, escarpe;
    Controller control;

    public ControllerAction(String _path, Controller control) {
        super();
        this.path = _path;
        this.control = control;
        initialization();
    }

    private void initialization() {
        node = false;
        fire = false;
        plat = false;
        inonder = false;
        escarpe = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().matches("Noeud")) {
            node = true;
        }
        if (e.getActionCommand().matches("Feu")) {
            fire = true;
        }
        if (e.getActionCommand().matches("Plat")) {
            plat = true;
        }
        if (e.getActionCommand().matches("Escarpe")) {
            inonder = true;
        }
        if (e.getActionCommand().matches("Inonde")) {
            escarpe = true;
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

        }
        if (e.getActionCommand().matches("Chenille")) {

        }
        if (e.getActionCommand().matches("Pates")) {

        }

    }

    public void addElement(int x, int y) {

        int id = 0;
        if (node) {
            control.addNode(new Node(id, x, y));
            node = false;
        }
        if (fire) {
            control.addNode(new Node(id, x, y, TypeNode.INCENDIE));
            fire = false;
        }
        if (plat) {

            // control.addEdge();
            plat = false;
        }
        if (escarpe) {

            escarpe = false;
        }
        if (inonder) {

            inonder = false;
        }
    }

    protected void clickOnNode(int x, int y) {
        int currentNode = 0;
        for (Node n : control.getGraph().getAllNodes()) {
            for (int i = -10; i < 10; i++) {
                for (int j = -10; j < 10; j++) {
                    if ((x == (int) n.getX() + i) && (y == (int) n.getY() + j)) {
                        n.setCurrentNode(true);
                        currentNode = n.getID();
                        break;
                    }
                }
            }
        }
        for (Node n : control.getGraph().getAllNodes()) {
            if (n.getID() != currentNode && n.isCurrentNode()) {
                n.setCurrentNode(false);
            }
        }
    }
}


