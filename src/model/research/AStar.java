package model.research;

import model.graph.Graph;
import model.graph.Node;
import model.graph.SearchNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 20/05/15.
 */
public class AStar extends Strategy {
    private List<SearchNode> openList;
    private List<SearchNode> closedList;

    public AStar(Graph g, Node n_start, Node n_dest) {
        openList = new ArrayList<SearchNode>();
        closedList = new ArrayList<SearchNode>();
    }

}
