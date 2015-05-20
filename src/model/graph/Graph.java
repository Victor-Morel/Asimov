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
		this.nodes = new HashSet<Node>();
		this.edges = new HashSet<Edge>();
	}
	
	public void addNode(Node _node) {
		this.nodes.add(_node);		
	}

	public Set<Node> getAllNodes() {
		return this.nodes;
	}

	public int getNbNodes() {
		return this.nodes.size();
	}

	public List<Node> getNextNodes(Node _n) {
		List<Node> destNodes = new ArrayList<Node>();
		for(Edge e : this.edges) {
			if(e.getSource().equals(_n)) {
				destNodes.add(e.getDestination());
			}
		}
		return destNodes;
	}

	public boolean hasArc(Node _n1, Node _n2) {
		for(Edge e : this.edges) {
			if(e.getSource().equals(_n1) && e.getDestination().equals(_n2)) {
				return true;
			}
		}
		return false;
	}

	public void addEdge(Edge _edge) {
		this.edges.add(_edge);
	}

	public List<Edge> getEdge(Node _n) {
		List<Edge> requiredEdges = new ArrayList<Edge>();
		for(Edge e : this.edges) {
			if(e.getSource().equals(_n)) {
				requiredEdges.add(e);
			}
		}
		return requiredEdges;
	}
	
	public String toString() {
		String s = "";
		for(Node n : this.nodes) {
			s = s + "[ noeud=" + n.getLabel() + " : ";
			s = s + this.getEdge(n).toString();
			s = s + " ]\n";
		}
		return s;
	}
}
