package controller;

import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.robot.Manager;
import view.Edge.AVEdge;
import view.Edge.VEdgeEscarpe;
import view.Edge.VEdgePlat;
import view.GUI;
import view.Node.AVNode;
import view.Node.VNodeFire;
import view.Node.VNodeNormal;
import view.SheetDisplay;

public class Controller {

    private String path;
    private ControllerAction controlAction;
    private ControllerMouse controlMouse;
    private SheetDisplay sheetDisplay;
    private Graph graph;
    private GUI window;
    private Manager manager;

    public Controller(String _path) {
        this.path = _path;
        this.controlAction = new ControllerAction(path, this);
        this.controlMouse = new ControllerMouse(controlAction);

        this.sheetDisplay = new SheetDisplay(controlMouse);

        this.setGraph(new Graph());
        this.manager = new Manager();

        window = new GUI(controlAction, controlMouse);
    }


    public void run() {
        manager.run();

    }

    public void draw() {

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
            if (node.getType().getText() == "NORMAL")
                viewNode = new VNodeNormal(window.getSheetDisplay(), node);
            if (node.getType().getText() == "INCENDIE")
                viewNode = new VNodeFire(window.getSheetDisplay(), node);
            window.getSheetDisplay().addNode(viewNode);
        }
        AVEdge viewEdge = null;
        for (Edge edge : graph.getAllEdges()) {
            if (edge.getType().getText() == "PLAT")
                viewEdge = new VEdgePlat(window.getSheetDisplay(), edge);
            if (edge.getType().getText() == "ESCARPE")
                viewEdge = new VEdgeEscarpe(window.getSheetDisplay(), edge);
            window.getSheetDisplay().addEdge(viewEdge);
        }

        this.repaint();

    }


    // ajouter un noeud
    public void addNode(Node node) {

        AVNode viewNode = null;

        //addNode to graph
        getGraph().addNode(node);

        //addNode to sheet
        if (node.getType().getText() == "NORMAL") viewNode = new VNodeNormal(window.getSheetDisplay(), node);
        if (node.getType().getText() == "INCENDIE") viewNode = new VNodeFire(window.getSheetDisplay(), node);

        window.getSheetDisplay().addNode(viewNode);

        this.repaint();
    }


}
