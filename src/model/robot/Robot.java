package model.robot;

import model.graph.Graph;
import model.graph.Node;
import model.research.AStar;
import model.research.Dijkstra;
import model.research.Strategy;

import java.util.Observable;


public abstract class Robot extends Observable implements Runnable {

    public int getId() {
        return id;
    }

    public Strategy getStrat() {
        return strat;
    }

    public void setStrat(Strategy strat) {
        this.strat = strat;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Graph getPath() {
        return path;
    }

    public void setPath(Graph path) {
        this.path = path;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }


    private static final int ROBOT_MOBILITY_SPEED = 30;
    private static final int ROBOT_EXTINGUISH_SPEED = 3;
    private ResearchType researchType;
    private int id;
    protected Node node;
    private boolean busy;
    private double capacity;
    private Strategy strat;
    private Graph path;
    protected Graph g;
    private Node inFlames;
    private int distance;


    public Robot(Node _node, ResearchType _researchType){
        this.setBusy(false);
        //TODO Calculate _capacity
        double capacity = 0.3;
        this.capacity = capacity;
        this.node = _node;
        this.researchType = _researchType;
    }

    public void setExtinction(Node _inFlames, int _distance){
        this.inFlames = _inFlames;
        this.distance = _distance;
    }



    public int getDistance(Node inFlames) {
        switch (researchType) {
            case DIJKSTRA:
                this.setStrat(new Dijkstra(g, this.getNode(), inFlames));
                break;
            case ASTAR:
                this.setStrat(new AStar(g, this.getNode(), inFlames));
                break;
        }
        this.setPath(strat.getResultGraph());
        return this.getStrat().getDistanceValue();
    }

    public int extinguish (Node inFlames){
        int timeNeeded = 0;
        while(inFlames.getIntensity() > 0) {
            inFlames.cooling(capacity);
            timeNeeded += ROBOT_EXTINGUISH_SPEED;
        }
        return timeNeeded;
    }

    @Override
    public void run(){
        while(!path.getAllEdges().isEmpty()) {
            try {
                Thread.sleep((int)(path.getEdges(node).get(0).getValuation() + 1) * ROBOT_MOBILITY_SPEED);
                System.out.println("Noeud actuel " + node);
                Node previousNode = this.node;
                if(path.getEdges(node).get(0).getDestination().equals(node)) {
                    this.node = path.getEdges(node).get(0).getSource();
                }
                else {
                    this.node = path.getEdges(node).get(0).getDestination();
                }
                path.removeEdge(path.getEdges(previousNode).get(0));

                //FORCER LA VUE A RAFRAICHIR L'AFFICHAGE ICI
                setChanged();
                notifyObservers();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("fin trajet");
        //this.setNode(inFlames);
        try {
            Thread.sleep( (long)(extinguish(node)) );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("fin incendie");
        this.setBusy(false);
    }

    public void goTo(Node inFlames, int distance){
        //envoie un robot sur le noeud en question
        Thread waiting = new Thread();
        try {
            waiting.sleep(distance * ROBOT_MOBILITY_SPEED);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.setNode(inFlames);
        try {
            waiting.sleep((long) (this.capacity * inFlames.getIntensity()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        inFlames.setIntensity(0);
        this.setBusy(false);

    }

    public ResearchType getType() {
        return researchType;
    }

    public abstract void generateSubGraph(Graph _g);
}
