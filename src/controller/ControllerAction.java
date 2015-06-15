package controller;

import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.graph.TypeEdge;
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

import java.io.File;

public class ControllerAction {

    private ControllerActionNode controlNode;
    private ControllerActionEdge controlEdge;
    private ControllerActionRobot controlRobot;
    private ControllerActionWindows controlWindows;

    private ControllerMouse controlMouse;

    private GUI window;

    /**
     * Controlleur
     */
    Controller control;

    /**
     * Consctruit un controlleur des actions
     *
     * @param control
     */
    public ControllerAction(Controller control) {
        super();
        this.control = control;
        this.controlNode = new ControllerActionNode(this);
        this.controlEdge = new ControllerActionEdge(this);
        this.controlRobot = new ControllerActionRobot(this);
        this.controlWindows = new ControllerActionWindows(this);

        this.controlMouse = new ControllerMouse(this);
        window = new GUI(this, controlMouse);
    }

    /**
     * ajouter un noeud
     *
     * @param node
     */
    public void addNode(Node node) {

        control.addNode(node);

        AVNode viewNode;
        //addNode to sheet
        if (node.getIntensity() == 0) viewNode = new VNodeNormal(window.getSheetDisplay(), node);
        else viewNode = new VNodeFire(window.getSheetDisplay(), node);

        window.getSheetDisplay().addNode(viewNode);
        repaint();
    }

    /**
     * Ajouter un arc
     *
     * @param edge
     */
    public void addEdge(Edge edge) {
        control.addEdge(edge);

        AVEdge viewEdge = null;
        //addNode to sheet
        if (edge.getType().equals(TypeEdge.PLAT))
            viewEdge = new VEdgePlat(edge);
        else if (edge.getType().equals(TypeEdge.INONDE))
            viewEdge = new VEdgeInonde(edge);
        else if (edge.getType().equals(TypeEdge.ESCARPE))
            viewEdge = new VEdgeEscarpe(edge);

        window.getSheetDisplay().addEdge(viewEdge);
        repaint();
    }


    /**
     * Ajouter un robot
     *
     * @param robot
     */
    public void addRobot(Robot robot) {
        AVRobot viewRobot;


        viewRobot = new VRobotAllTerain(window.getSheetDisplay(), robot);
        //viewEdge = new VEdgeInonde(window.getSheetDisplay(), edge);
        //viewEdge = new VEdgePlat(window.getSheetDisplay(), edge);

        window.getSheetDisplay().addRobot(viewRobot);
        repaint();
    }


    /**
     * Redessiner les elements
     */
    public void repaint() {
        window.getSheetDisplay().repaint();
    }

    public void changePicture(File file) {
        window.getSheetDisplay().getImage().setImage(file);
        repaint();
    }

    /**
     * Réinitialiser le programme
     */
    public void reset() {
        control.reset();
        window.getSheetDisplay().reset();
    }


    /**
     * Affiche un Graphe
     */
    protected void displayGraphe() {
        AVNode viewNode;
        for (Node node : control.getGraph().getAllNodes()) {
            if (node.getIntensity() == 0)
                viewNode = new VNodeNormal(window.getSheetDisplay(), node);
            else
                viewNode = new VNodeFire(window.getSheetDisplay(), node);
            window.getSheetDisplay().addNode(viewNode);
        }
        AVEdge viewEdge = null;
        for (Edge edge : control.getGraph().getAllEdges()) {
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
        control.launchSimulation();
    }

    public void setGraph(Graph graph) {
        control.setGraph(graph);
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
        return control.getGraph();
    }


}


