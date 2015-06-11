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
}


