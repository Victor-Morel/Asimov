package model.graph;

public class Edge {


    /**
     * Poids de l'arête
     */
    private double valuation;

    /**
     * Noeud source de l'arête (équivalent a destination car graphe non-orienté)
     */
    private Node source;

    /**
     * Noeud destination de l'arête  (équivalent à source car graphe non-orienté)
     */
    private Node destination;

    /**
     * Type d'arête (PLAT, INONDE, ESCARPE)
     */
    private TypeEdge type;

    /**
     * Construit une arête entre 2 noeuds, avec un poids et un type
     *
     * @param _v1 premier noeud de l'arête
     * @param _v2 deuxième noeud de l'arête
     * @param _valuation poids de l'arête
     * @param type type d'arête
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
     * Set la valeur d'une arête
     * @param valuation valeur de l'arête
     */
    public void setValuation(int valuation) {
        this.valuation = valuation;
    }

    /**
     * @return le noeud source de l'arête
     */
    public Node getSource() {
        return source;
    }

    /**
     * Set le noeud source de l'arête
     * @param _n noeud source
     */
    public void setSource(Node _n) {
        this.source = _n;
    }

    /**
     * @return le noeud destination de l'arête
     */
    public Node getDestination() {
        return destination;
    }

    /**
     * Set le noeud destination de l'arête
     * @param _n noeud destination
     */
    public void setDestination(Node _n) {
        this.destination = _n;
    }

    /**
     * @return un String correspondant à l'arète     */
    @Override
    public String toString() {
        return source.getID() + " => "
                + destination.getID() + "(" + valuation + ")";
    }

    /**
     * @return le type d'arète     */
    public TypeEdge getType() {
        return type;
    }

    /**
     *  Set le type d'arète     * @param type type d'arète     */
    public void setType(TypeEdge type) {
        this.type = type;
    }

    /**
     * Destructeur d'arète     * @throws Throwable
     */
    public void reset() throws Throwable {
        this.finalize();
    }
}
