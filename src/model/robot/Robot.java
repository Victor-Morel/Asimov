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
        setChanged();
        notifyObservers();
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }


    private static final int ROBOT_MOBILITY_SPEED = 50;
    private static final int ROBOT_EXTINGUISH_SPEED = 3;
    private static ResearchType researchType = ResearchType.ASTAR;
    private int id;
    protected Node node;
    private boolean busy;
    private double capacity;
    private Strategy strat;
    protected Graph g;
    private Node inFlames;
    private int distance;

    public void setExtinction(Node _inFlames, int _distance){
        this.inFlames = _inFlames;
        this.distance = _distance;
    }

    public Robot (double _capacity, Node _node){
        this.setBusy(false);
        this.capacity = _capacity;
        this.node = _node;
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
        try {
            Thread.sleep(distance * ROBOT_MOBILITY_SPEED);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("fin trajet");
        this.setNode(inFlames);
        try {
            Thread.sleep( (long)(extinguish(inFlames)) );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("fin incendie : " + inFlames.getIntensity());
        //inFlames.setIntensity(0);
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
