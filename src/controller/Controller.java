package controller;

import model.graph.Graph;
import model.graph.Node;
import view.GUI;
import view.SheetDisplay;
import view.VNode.VNode;

public class Controller {

    private String path;
    private ControllerAction controlAction;
    private ControllerMouse controlMouse;
    private SheetDisplay sheetDisplay;
    private Graph graph;
    private GUI window;

    public Controller(String _path) {
        this.path = _path;
        this.sheetDisplay = new SheetDisplay();
        this.controlAction = new ControllerAction(path, this);
        this.controlMouse = new ControllerMouse(controlAction);

        this.graph = new Graph();

        window = new GUI(controlAction, controlMouse);
    }


    public void run() {

    }

    public void draw() {

    }

    // ajouter tortue
    public void addNode(Node node) {
        //addNode to graph
        graph.addNode(node);

        //addNode to sheet
        VNode viewNode = new VNode(window.getSheetDisplay(), node);
        window.getSheetDisplay().addNode(viewNode);

        this.repaint();
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public void repaint() {
        window.getSheetDisplay().repaint();
    }
}
