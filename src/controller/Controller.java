package controller;

import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import view.GUI;
import view.Node.AVNode;
import view.Node.VFire;
import view.Node.VNode;
import view.SheetDisplay;

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

        this.setGraph(new Graph());

        window = new GUI(controlAction, controlMouse);
    }


    public void run() {

    }

    public void draw() {

    }

    // ajouter un noeud
    public void addNode(Node node) {

        //addNode to graph
        getGraph().addNode(node);

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

    public Graph getGraph() {
        return graph;
    }


    protected void displayGraphe() {
        AVNode viewNode = null;
        for (Node node : graph.getAllNodes()) {
            if (node.getType().getText() == "NORMAL") viewNode = new VNode(window.getSheetDisplay(), node);
            if (node.getType().getText() == "INCENDIE") viewNode = new VFire(window.getSheetDisplay(), node);
            window.getSheetDisplay().addNode(viewNode);
        }
        for (Edge edge : graph.getAllEdges()) {
            //         VNode viewEdge = new VEdge(window.getSheetDisplay(), graph.findNode(edge.getSource()), edge.getDestination() ,edge);
            //         window.getSheetDisplay().addEdge(viewEdge);
        }

        this.repaint();

    }
}
