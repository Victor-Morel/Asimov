package model.graph;

/**
 * Created by Logan Paul on 04/06/2015.
 */
public class SearchNode {
    private Node node;
    private SearchNode parent;
    private double value;

    public SearchNode(Node node, SearchNode parent, double value) {
        this.setNode(node);
        this.setParent(parent);
        this.setValue(value);
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public SearchNode getParent() {
        return parent;
    }

    public void setParent(SearchNode parent) {
        this.parent = parent;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

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

    @Override
    public String toString() {
        return "[" + node.toString() + "| p : " + parent.getNode().getID() + "]";
    }
}
