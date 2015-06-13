package model.robot;

import model.graph.Graph;
import model.graph.Node;
import model.research.AStar;
import model.research.Dijkstra;
import model.research.Strategy;

/**
 * Created by victor on 20/05/15.
 */
public abstract class Robot {

    public int getId() {
        return id;
    }

    public TypeRecherche getType() {
        return type;
    }

    public void setType(TypeRecherche type) {
        this.type = type;
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

    public enum TypeRecherche{
        dijkstra, astar
    }

    private TypeRecherche type;
    private int id;
    private Node node;
    private boolean busy;
    private double capacity;
    private Strategy strat;
    protected Graph g;

    public Robot (TypeRecherche _type, double _capacity){
        this.setType(_type);
        this.setBusy(false);
        this.capacity = _capacity;
    }

    public int getDistance(Node inFlames) {
        switch (this.getType()){
            case dijkstra:
                this.setStrat(new Dijkstra(g, this.getNode(), inFlames));
                break;
            case astar:
                this.setStrat(new AStar(g, this.getNode(), inFlames));
                break;
        }
        return this.getStrat().getDistanceValue();
    }

    public void extinguish (Node inFlames){
        inFlames.cooling(capacity);
    }

    public void goTo(Node inFlames){
        //envoie un robot sur le noeud en question
    }



}
