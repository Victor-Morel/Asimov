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

    public Map<Node,Double> d;
    public List<Node> viewed;
    public List<Edge> path;

    public Dijkstra(Graph g, Node n_start, Node n_dest) {
        this.destination = n_dest;
        this.source = n_start;
        this.graph = g;
        this.d = new HashMap<Node,Double>();
        this.viewed = new ArrayList<Node>();
        this.path = new ArrayList<Edge>();
        this.generateBestPath();
    }


    @Override
    public void generateBestPath() {
        Node currentNode = source;

        initialization(graph,source);
        viewed.add(source);
        for(Node n : graph.getNextNodes(source)) {
            updateDistance(graph, source, n);
        }

        while(!currentNode.equals(destination)) {
            Double minDist = d.get(0);
            Node bestNode = null;
            for (Node keyNode : d.keySet()) {
                if(d.get(keyNode) <= minDist && !viewed.contains(keyNode)) {
                    minDist = d.get(keyNode);
                    bestNode = keyNode;
                }
            }
            if(null != bestNode) {
                path.add(graph.getEdge(currentNode, bestNode));
                currentNode = bestNode;
                viewed.add(currentNode);
                for(Node nextNode : graph.getNextNodes(currentNode)) {
                    updateDistance(graph, currentNode, nextNode);
                }
            }
            else {
                System.out.println("Erreur dijkstra");
            }
        }

        this.resultGraph = new Graph();
        while(!currentNode.equals(source)) {
            Double bestValue = Double.MAX_VALUE;
            Edge bestEdge = null;
            for (Edge e : path) {
                if (e.getDestination().equals(currentNode) && e.getValuation() < bestValue) {
                    bestValue = e.getValuation();
                    bestEdge = e;
                }
            }
            this.getResultGraph().addNode(currentNode);
            this.getResultGraph().addEdge(bestEdge);
            currentNode = bestEdge.getSource();
        }
    }

    public void initialization(Graph g, Node n_start) {

        for(Node n : g.getAllNodes()) {
            d.put(n, Double.MAX_VALUE);
        }
        d.put(n_start, 0.0);
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
