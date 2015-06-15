package model.graph;

/**
 * Created by Logan Paul on 04/06/2015.
 */
public class SearchNode {
    /**
     * Noeud que l'on transorme en noeud de recherche
     */
    private Node node;

    /**
     * Parent du noeud
     */
    private SearchNode parent;

    /**
     * Valeur donn�e au noeud
     */
    private double value;

    /**
     * Construit un noeud de recherche � partir d'un noeud, de son parent, et d'une valeur donn�e
     * @param node noeud � transformer en noeud de recherche
     * @param parent parent du noeud � transformer
     * @param value valeur � donner au noeud
     */
    public SearchNode(Node node, SearchNode parent, double value) {
        this.setNode(node);
        this.setParent(parent);
        this.setValue(value);
    }

    /**
     * Permet de r�cup�rer le noeud
     * @return le noeud
     */
    public Node getNode() {
        return node;
    }

    /**
     * Permet de setter le noeud
     * @param node noeud � setter
     */
    public void setNode(Node node) {
        this.node = node;
    }

    /**
     * Permet de r�cup�rer le noeud parent
     * @return le noeud parent
     */
    public SearchNode getParent() {
        return parent;
    }

    /**
     * Permet de setter le noeud parent
     * @param parent le noeud parent � setter
     */
    public void setParent(SearchNode parent) {
        this.parent = parent;
    }

    /**
     * Permet de r�cup�rer la valeur du noeud
     * @return la valeur du noeud
     */
    public double getValue() {
        return value;
    }

    /**
     * Permet de setter la valeur du noeud
     * @param value valeur � setter au noeud
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * @param o le noeud � comparer � this
     * @return true si les noeuds sont identiques, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchNode that = (SearchNode) o;

        if (Double.compare(that.value, value) != 0) return false;
        if (node != null ? !node.equals(that.node) : that.node != null) return false;
        return !(parent != null ? !parent.equals(that.parent) : that.parent != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = node != null ? node.hashCode() : 0;
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * @return un string correspondant au noeud de recherche
     */
    @Override
    public String toString() {
        return "[" + node.toString() + "| p : " + parent.getNode().getID() + "]";
    }
}
