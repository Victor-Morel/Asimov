package model.graph;

/**
 * Created by Logan Paul on 04/06/2015.
 */
public class SearchNode {
    private Node node;
    private Node parent;
    private int value;

    public SearchNode(Node node, Node parent, int value) {
        this.node = node;
        this.parent = parent;
        this.value = value;
    }
}
