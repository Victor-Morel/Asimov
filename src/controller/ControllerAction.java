package controller;

import model.graph.Graph;
import model.graph.Node;
import model.graph.Type;
import utils.FileManager;
import view.ChooseFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControllerAction implements ActionListener {

    String path;
    Boolean node, fire, plat, inonder, escarpe;
    Graph graph;

    public ControllerAction(String _path, Graph _graph) {
        super();
        this.path = _path;
        this.graph = _graph;
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

        }
        if (e.getActionCommand().matches("Load")) {
            ChooseFile chooseFile = new ChooseFile();
            new FileManager(chooseFile.selectFile(this.path));
        }

    }

    public void addElement(int x, int y) {
        //TODO add node and edge to good graph

        //TODO manage id

        int id;
        if (node) {
            Node n = new Node(id, x, y, Type.NORMAL);
            graph.addNode(n);
            node = false;
        }
        if (fire) {
            Node n = new Node(id, x, y, Type.INCENDIE);
            graph.addNode(n);
            fire = false;
        }
        if (plat) {

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


