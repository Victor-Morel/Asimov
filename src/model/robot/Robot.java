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

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }



    private ResearchType researchType;
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

    public Robot (ResearchType _type, double _capacity){
        this.researchType = _type;
        this.setBusy(false);
        this.capacity = _capacity;
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

    public void extinguish (Node inFlames){
        inFlames.cooling(capacity);
    }

    @Override
    public void run(){
        try {
            Thread.sleep(distance * 20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("fin trajet");
        this.setNode(inFlames);
        try {
            Thread.sleep((long) (this.capacity * inFlames.getIntensity()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("fin incendie");
        inFlames.setIntensity(0);
        this.setBusy(false);
        setChanged();
        notifyObservers();
    }

    public void goTo(Node inFlames, int distance){
        //envoie un robot sur le noeud en question
        Thread waiting = new Thread();
        try {
            waiting.sleep(distance * 20);
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
        setChanged();
        notifyObservers();
    }


    public ResearchType getType() {
        return researchType;
    }
}
