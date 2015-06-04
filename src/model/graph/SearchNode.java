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
}
