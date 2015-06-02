package model.research;

import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by victor on 20/05/15.
 */
public class Dijkstra extends Strategy {

    public Map<Node,Integer> d;
    public List<Node> viewed;
    public List<Edge> path;

    public Dijkstra(Graph g, Map weight, Node n_start, Node n_dest) {
        this.d = new HashMap<Node,Integer>();
        this.viewed = new ArrayList<Node>();
        this.path = new ArrayList<Edge>();
        Node currentNode = n_start;

        initialization(g,n_start);
        viewed.add(n_start);
        for(Node n : graph.getNextNodes(n_start)) {
            updateDistance(g, n_start, n);
        }

        while(!currentNode.equals(n_dest)) {
            int minDist = d.get(0);
            Node bestNode = null;
            for (Node keyNode : d.keySet()) {
                if(d.get(keyNode) <= minDist && !viewed.contains(keyNode)) {
                    minDist = d.get(keyNode);
                    bestNode = keyNode;
                }
            }
            if(null != bestNode) {
                path.add(g.getEdge(currentNode,bestNode));
                currentNode = bestNode;
                viewed.add(currentNode);
                for(Node nextNode : graph.getNextNodes(currentNode)) {
                    updateDistance(g, currentNode, nextNode);
                }
            }
            else {
                System.out.println("Erreur dijkstra");
            }
        }

        this.resultGraph = new Graph();
        while(!currentNode.equals(n_start)) {
            int bestValue = Integer.MAX_VALUE;
            Edge bestEdge = null;
            for (Edge e : path) {
                if (e.getDestination().equals(currentNode) && e.getValuation() < bestValue) {
                    bestValue = e.getValuation();
                    bestEdge = e;
                }
            }
            this.resultGraph.addNode(currentNode);
            this.resultGraph.addEdge(bestEdge);
            currentNode = bestEdge.getSource();
        }
    }

    public void initialization(Graph g, Node n_start) {

        for(Node n : g.getAllNodes()) {
            d.put(n, Integer.MAX_VALUE);
        }
        d.put(n_start, 0);
    }

    public void updateDistance(Graph g, Node n1, Node n2) {
        if(g.hasEdge(n1,n2)) {
            if (d.get(n2) > d.get(n1) + g.getEdge(n1, n2).getValuation()) {
                d.put(n2, d.get(n1) + g.getEdge(n1, n2).getValuation());
            }
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
