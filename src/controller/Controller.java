package controller;

import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.graph.TypeEdge;
import model.robot.Manager;
import model.robot.Simulation;
import view.Edge.AVEdge;
import view.Edge.VEdgeEscarpe;
import view.Edge.VEdgeInonde;
import view.Edge.VEdgePlat;
import view.GUI;
import view.Node.VNode;

public class Controller {

    private Graph graph;
    private Manager manager;

    private ControllerActionNode controlNode;
    private ControllerActionEdge controlEdge;
    private ControllerActionRobot controlRobot;
    private ControllerActionWindows controlWindows;

    private ControllerMouse controlMouse;

    private GUI window;


    /**
     * Construit un controleur des actions
     */
    public Controller() {
        super();
        this.controlNode = new ControllerActionNode(this);
        this.controlEdge = new ControllerActionEdge(this);
        this.controlRobot = new ControllerActionRobot(this);
        this.controlWindows = new ControllerActionWindows(this);

        this.controlMouse = new ControllerMouse(this);
        window = new GUI(this, controlMouse);

        this.setGraph(new Graph());
        this.manager = new Manager();
    }


    /**
     * Redessiner les elements
     */
    public void repaint() {
        window.getSheetDisplay().repaint();
    }


    /**
     * Réinitialiser le programme
     */
    public void reset() {
        try {
            getGraph().finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        window.getSheetDisplay().reset();
    }


    /**
     * Affiche un Graphe
     */
    protected void displayGraph() {
        VNode viewNode;
        for (Node node : getGraph().getAllNodes()) {
                viewNode = new VNode(window.getSheetDisplay(), node);
            window.getSheetDisplay().addNode(viewNode);
        }
        AVEdge viewEdge = null;
        for (Edge edge : getGraph().getAllEdges()) {
            if (edge.getType().equals(TypeEdge.PLAT))
                viewEdge = new VEdgePlat(edge);
            if (edge.getType().equals(TypeEdge.ESCARPE))
                viewEdge = new VEdgeEscarpe(edge);
            if (edge.getType().equals(TypeEdge.INONDE))
                viewEdge = new VEdgeInonde(edge);
            window.getSheetDisplay().addEdge(viewEdge);
        }
        this.repaint();
    }

    public void launchSimulation() {
        manager.setGraph(graph);
        Simulation simulation = new Simulation(manager, graph);
        new Thread(simulation).start();


    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public ControllerActionNode getControlNode() {
        return controlNode;
    }

    public ControllerActionEdge getControlEdge() {
        return controlEdge;
    }

    public ControllerActionWindows getControlWindows() {
        return controlWindows;
    }

    public ControllerActionRobot getControlRobot() {
        return controlRobot;
    }

    public Graph getGraph() {
        return graph;
    }

    public GUI getWindow() {
        return window;
    }

    public Manager getManager() {
        return manager;
    }
}
