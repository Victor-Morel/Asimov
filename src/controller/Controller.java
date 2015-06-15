package controller;

import model.graph.Graph;
import model.graph.Node;
import model.robot.Manager;
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
     *  ajouter un noeud
     * @param node
     */
    public void addNode(Node node) {
        getGraph().addNode(node);
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
     * Reinitialiser le programme
     */
    public void reset() {
        try {
            getGraph().finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }


    public Manager getManager() {
        return manager;
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
}
