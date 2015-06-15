package model.graph;

public class Edge {


    /**
     * Poids de l'ar�te
     */
    private double valuation;

    /**
     * Noeud source de l'ar�te (�quivalent a destination car graphe non-orient�)
     */
    private Node source;

    /**
     * Noeud destination de l'ar�te  (�quivalent � source car graphe non-orient�)
     */
    private Node destination;

    /**
     * Type d'ar�te (PLAT, INONDE, ESCARPE)
     */
    private TypeEdge type;

    /**
     * Construit une ar�te entre 2 noeuds, avec un poids et un type
     *
     * @param _v1 premier noeud de l'ar�te
     * @param _v2 deuxi�me noeud de l'ar�te
     * @param _valuation poids de l'ar�te
     * @param type type d'ar�te
     */
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
     * Set la valeur d'une ar�te
     * @param valuation valeur de l'ar�te
     */
    public void setValuation(int valuation) {
        this.valuation = valuation;
    }

    /**
     * @return le noeud source de l'ar�te
     */
    public Node getSource() {
        return source;
    }

    /**
     * Set le noeud source de l'ar�te
     * @param _n noeud source
     */
    public void setSource(Node _n) {
        this.source = _n;
    }

    /**
     * @return le noeud destination de l'ar�te
     */
    public Node getDestination() {
        return destination;
    }

    /**
     * Set le noeud destination de l'ar�te
     * @param _n noeud destination
     */
    public void setDestination(Node _n) {
        this.destination = _n;
    }

    /**
     * @return un String correspondant � l'ar�te     */
    @Override
    public String toString() {
        return source.getID() + " => "
                + destination.getID() + "(" + valuation + ")";
    }

    /**
     * @return le type d'ar�te     */
    public TypeEdge getType() {
        return type;
    }

    /**
     *  Set le type d'ar�te     * @param type type d'ar�te     */
    public void setType(TypeEdge type) {
        this.type = type;
    }

    /**
     * Destructeur d'ar�te     * @throws Throwable
     */
    public void reset() throws Throwable {
        this.finalize();
    }
}
