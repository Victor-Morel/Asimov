package model.research;

import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.graph.Couple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by victor on 20/05/15.
 */
public class Dijkstra extends Strategy {

    public Map d;
    public Map weight;
    public List<Node> viewed;


    public Dijkstra(Graph g, Map weight, Node n_start) {
        this.d = new HashMap<Node,Integer>();
        this.weight = new HashMap<Couple,Integer>();
        this.viewed = new ArrayList<Node>();
        List p = new ArrayList();

        initialization(g,n_start);
        viewed.add(n_start);
        for(Node n : graph.getNextNodes(n_start)) {
            updateDistance(n_start, n);
        }
        while(!p.isEmpty()) {
            weight.put(new Couple())
        }
    }

    public void initialization(Graph g, Node n_start) {

        for(Node n : g.getAllNodes()) {
            d.put(n, Double.MAX_VALUE);
        }
        d.put(n_start, 0);
    }

    public void updateDistance(Node n1, Node n2) {
        Couple c = new Couple(n1,n2);
        if( (Integer) d.get(n2) > (Integer) d.get(n1) + (Integer)weight.get(c) ) {
            d.put(n2, (Integer) d.get(n1) + (Integer) weight.get(c));
        }
    }

    public Edge extract_min(List<Edge> p) {
        Edge minEdge = p.get(0);
        for(Edge e : p) {
            if(e.getValuation() < minEdge.getValuation()) {
                minEdge = e;
            }
        }
        p.remove(minEdge);
        return minEdge;
    }
}
