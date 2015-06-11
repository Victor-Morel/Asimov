package model.graph;

import java.util.Observable;

public class Edge extends Observable {

    private double valuation;
    /**
     * Noeud source de l'arete : equivalent a destination car non-oriente
     */
    private Node source;
    /**
     * Noeud destination de l'arete : equivalent a source car non-oriente
     */
    private Node destination;

    private TypeEdge type;


    public Edge(Node _v1, Node _v2, double _valuation, TypeEdge type) {
        this.valuation = _valuation;
        this.source = _v1;
        this.destination = _v2;
        this.setType(type);

    }



    /**
     * @return la valeur de l'arete
     */
    public double getValuation() {
        return valuation;
    }

    /**
     * @param valuation
     */
    public void setValuation(int valuation) {
        this.valuation = valuation;
    }

    /**
     * @return le noeud source de l'arete
     */
    public Node getSource() {
        return source;
    }

    /**
     * noeud source
     */
    public void setSource(Node _n) {
        this.source = _n;
    }

    /**
     * @return le noeud destination de l'arete
     */
    public Node getDestination() {
        return destination;
    }

    /**
     * noeud destination
     */
    public void setDestination(Node _n) {
        this.destination = _n;
    }

    /*
    @Override
    public String toString() {
        return source.getLabel().toString() + " ==> "
                + destination.getLabel().getLabel() + "(" + valuation + ")";
    }*/

    public TypeEdge getType() {
        return type;
    }

    public void setType(TypeEdge type) {
        this.type = type;
    }
}
