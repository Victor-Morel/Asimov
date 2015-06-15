package controller;

import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.robot.Manager;
import model.robot.Robot;
import model.robot.Simulation;

public class Controller {

    private ControllerAction controlAction;
    private Graph graph;
    private Manager manager;

    /**
     * Construit le controlleur
     */
    public Controller() {
        this.controlAction = new ControllerAction(this);
        this.setGraph(new Graph());
        this.manager = new Manager();
    }


    public void run() {
    }

    /**
     * Muter le Graph
     * @param graph
     */
    public void setGraph(Graph graph) {
        this.graph = graph;
    }



    /**
     * Acceder au graph
     * @return Graph
     */
    public Graph getGraph() {
        return graph;
    }



    /**
     *  ajouter un noeud
     * @param node
     */
    public void addNode(Node node) {
        getGraph().addNode(node);
    }

    /**
     * Ajouter un arc
     * @param edge
     */
    public void addEdge(Edge edge) {
        //addNode to graph
        getGraph().addEdge(edge);
    }

    /**
     * Lance la simulation
     */

    public void launchSimulation(){
        manager.setGraph(graph);
        Simulation simulation = new Simulation(manager, graph);
        new Thread(simulation).start();
    }

    /**
     * Ajouter un robot
     * @param robot
     */
    public void addRobot(Robot robot) {
        manager.bots.add(robot);
    }


    /**
     * R?initialiser le programme
     */
    public void reset() {
        try {
            getGraph().finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }





}
