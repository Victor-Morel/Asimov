package model.research;

import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;

/**
 * Created by victor on 20/05/15.
 */
public abstract class Strategy {
	protected Node source;
	protected Node destination;
	protected Graph graph;
	protected Graph resultGraph;
	protected int distanceValue;

	public int getDistanceValue() {
		distanceValue = 0;
		for(Edge e : getResultGraph().getAllEdges()){
			distanceValue+=e.getValuation();
		}
		return distanceValue;
	}

	public abstract void generateBestPath();

	public Graph getResultGraph() {
		return resultGraph;
	}
}
