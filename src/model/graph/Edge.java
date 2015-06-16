package model.graph;

import utils.EuclidianDistance;

public class Edge {


    /**
     * Poids de l'arete
     */
    private double valuation;

    /**
     * Noeud source de l'arete (equivalent a destination car graphe non-oriente)
     */
    private Node source;

    /**
     * Noeud destination de l'arete  (equivalent a source car graphe non-oriente)
     */
    private Node destination;

    /**
     * Type d'arete (PLAT, INONDE, ESCARPE)
     */
    private TypeEdge type;

    /**
     * Construit une arete entre 2 noeuds, avec un poids et un type
     *
     * @param _n1        premier noeud de l'arete
     * @param _n2        deuxieme noeud de l'arete
     * @param _valuation poids de l'arete
     * @param type       type d'arete
     */
    public Edge(Node _n1, Node _n2, double _valuation, TypeEdge type) {
        this.valuation = _valuation;
        this.source = _n1;
        this.destination = _n2;
        this.setType(type);
    }

    /**
     * Construit une arete entre 2 noeuds, avec un poids correspondant a la distance entre les noeuds, et un type
     *
     * @param _n1  premier noeud de l'arete
     * @param _n2  deuxieme noeud de l'arete
     * @param type type d'arete
     */
    public Edge(Node _n1, Node _n2, TypeEdge type) {
        this(_n1, _n2, EuclidianDistance.getDistance(_n1, _n2), type);
    }

    /**
     * @return la valeur de l'arete
     */
    public double getValuation() {
        return valuation;
    }

    /**
     * Set la valeur d'une arete
     *
     * @param valuation valeur de l'arete
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
     * Set le noeud source de l'arete
     *
     * @param _n noeud source
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
     * Set le noeud destination de l'arete
     *
     * @param _n noeud destination
     */
    public void setDestination(Node _n) {
        this.destination = _n;
    }

    /**
     * @return un String correspondant a l'arete
     */
    @Override
    public String toString() {
        return source.getID() + " => "
                + destination.getID() + "(" + valuation + ")";
    }

    /**
     * @return le type d'arete
     */
    public TypeEdge getType() {
        return type;
    }

    /**
     * Set le type d'arete
     *
     * @param type type d'arete
     */
    public void setType(TypeEdge type) {
        this.type = type;
    }

    /**
     * Destructeur d'arete
     *
     * @throws Throwable
     */
    public void reset() throws Throwable {
        this.finalize();
    }
}
