package model.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by victor on 20/05/15.
 */
public class Graph {
    private Set<Node> nodes;
    private Set<Edge> edges;

    public Graph() {
        this.setNodes(new HashSet<Node>());
        this.setEdges(new HashSet<Edge>());
    }

    public void addNode(Node _node) {
        this.nodes.add(_node);
    }

    public Node findNode(int id) {
        for (Node n : nodes) {
            if (id == n.getID()) {
                return n;
            }
        }
        return null;
    }

    public Set<Node> getAllNodes() {
        return this.nodes;
    }

    public Set<Node> getAllFireNodes() {
        Set<Node> fireNodes = new HashSet<Node>();
        for (Node n : this.nodes) {
            if (n.getIntensity() > 0) {
                fireNodes.add(n);
            }
        }
        return fireNodes;
    }

    public int getNbNodes() {
        return this.nodes.size();
    }

    public List<Node> getNextNodes(Node _n) {
        List<Node> destNodes = new ArrayList<Node>();
        for (Edge e : this.getEdges(_n)) {
            if (e.getDestination().equals(_n)) {
                destNodes.add(e.getSource());
            } else {
                destNodes.add(e.getDestination());
            }
        }
        return destNodes;
    }

    public boolean hasEdge(Node _n1, Node _n2) {
        for (Edge e : this.getAllEdges()) {
            if ((e.getSource().equals(_n1) && e.getDestination().equals(_n2)) ||
                    (e.getSource().equals(_n2) && e.getDestination().equals(_n1)) ) {
                return true;
            }
        }
        return false;
    }

    public void addEdge(Edge _edge) {
        this.getAllEdges().add(_edge);
    }

    public List<Edge> getEdges(Node _n) {
        List<Edge> requiredEdges = new ArrayList<Edge>();
        for (Edge e : this.getAllEdges()) {
            if (e.getSource().equals(_n) || e.getDestination().equals(_n)) {
                //System.out.println(e.getSource().toString()+"   ____________    "+e.getDestination().toString());
                requiredEdges.add(e);
            }
        }
        return requiredEdges;
    }

    public Edge getEdge(Node _n1, Node _n2) {
        if (hasEdge(_n1, _n2)) {
            for (Edge e : this.getAllEdges()) {
                if (e.getSource().equals(_n1) && e.getDestination().equals(_n2) ||
                        e.getSource().equals(_n2) && e.getDestination().equals(_n1)) {
                    return e;
                }
            }
        }
        return null;
    }

    public String toString() {
        String s = "";
        for (Node n : this.getAllNodes()) {
            s = s + "[ noeud=" + n.getLabel() + " : ";
            s = s + this.getEdges(n).toString();
            s = s + " ]\n";
        }
        return s;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public Set<Edge> getAllEdges() {
        return edges;
    }

    public void setEdges(Set<Edge> edges) {
        this.edges = edges;
    }

    public Graph getSubGraph(int robotType) {
        /**
         * type 1 : allterrain
         * type 2 : caterpillar
         * type 3 : legged
         */
        Graph subgraph = new Graph();
        subgraph.setNodes(this.getAllNodes());
        for (Edge e : this.edges) {
            if (edgeAuthorized(e, robotType)) {
                subgraph.addEdge(e);
            }
        }
        return subgraph;
    }

    public Boolean edgeAuthorized(Edge e, int robotType) {
        switch (robotType) {
            case 1:
                return true;
            case 2:
                if ((e.getType() == TypeEdge.PLAT) || (e.getType() == TypeEdge.INONDE)) {
                    return true;
                }
                break;
            case 3:
                if ((e.getType() == TypeEdge.PLAT) || (e.getType() == TypeEdge.ESCARPE)) {
                    return true;
                }
                break;

            default:
                break;
        }
        return false;
    }
}
