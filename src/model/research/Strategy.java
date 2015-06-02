package model.research;

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
}
