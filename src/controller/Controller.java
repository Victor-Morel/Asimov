package controller;

import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.graph.TypeEdge;
import model.robot.Manager;
import model.robot.Robot;
import view.Edge.AVEdge;
import view.Edge.VEdgeEscarpe;
import view.Edge.VEdgeInonde;
import view.Edge.VEdgePlat;
import view.GUI;
import view.Node.AVNode;
import view.Node.VNodeFire;
import view.Node.VNodeNormal;
import view.Robot.AVRobot;
import view.Robot.VRobotAllTerain;

public class Controller {

    private String path;
    private ControllerAction controlAction;
    private ControllerMouse controlMouse;

    private Graph graph;
    private GUI window;
    private Manager manager;

    public Controller(String _path) {
        this.path = _path;
        this.controlAction = new ControllerAction(path, this);
        this.controlMouse = new ControllerMouse(controlAction);

        this.setGraph(new Graph());
        this.manager = new Manager();

        window = new GUI(controlAction, controlMouse);
    }


    public void run() {
        manager.run();

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
        AVNode viewNode;
        for (Node node : graph.getAllNodes()) {
            if (node.getIntensity() == 0)
                viewNode = new VNodeNormal(window.getSheetDisplay(), node);
            else
                viewNode = new VNodeFire(window.getSheetDisplay(), node);
            window.getSheetDisplay().addNode(viewNode);
        }
        AVEdge viewEdge = null;
        for (Edge edge : graph.getAllEdges()) {
            if (edge.getType().equals(TypeEdge.PLAT))
                viewEdge = new VEdgePlat(window.getSheetDisplay(), edge);
            if (edge.getType().equals(TypeEdge.ESCARPE))
                viewEdge = new VEdgeEscarpe(window.getSheetDisplay(), edge);
            window.getSheetDisplay().addEdge(viewEdge);
        }
        this.repaint();
    }


    // ajouter un noeud
    public void addNode(Node node) {

        AVNode viewNode;

        //addNode to graph
        getGraph().addNode(node);

        //addNode to sheet
        if (node.getIntensity() == 0) viewNode = new VNodeNormal(window.getSheetDisplay(), node);
        else viewNode = new VNodeFire(window.getSheetDisplay(), node);

        window.getSheetDisplay().addNode(viewNode);

        this.repaint();
    }


    public void addEdge(Edge edge) {

        AVEdge viewEdge = null;

        //addNode to graph
        getGraph().addEdge(edge);


        //addNode to sheet
        if (edge.getType().equals(TypeEdge.PLAT))
            viewEdge = new VEdgePlat(window.getSheetDisplay(), edge);
        else if (edge.getType().equals(TypeEdge.INONDE))
            viewEdge = new VEdgeInonde(window.getSheetDisplay(), edge);
        else if (edge.getType().equals(TypeEdge.ESCARPE))
            viewEdge = new VEdgeEscarpe(window.getSheetDisplay(), edge);

        window.getSheetDisplay().addEdge(viewEdge);

        this.repaint();
    }

    public void addRobot(Robot robot) {
        AVRobot viewRobot;

        //add Robot to manager
        //manager.addRobot(robot);


        viewRobot = new VRobotAllTerain(window.getSheetDisplay(), robot);
        //viewEdge = new VEdgeInonde(window.getSheetDisplay(), edge);
        //viewEdge = new VEdgePlat(window.getSheetDisplay(), edge);

        window.getSheetDisplay().addRobot(viewRobot);

        this.repaint();
    }

    public void reset() {
        try {
            getGraph().finalize();
            window.getSheetDisplay().reset();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
