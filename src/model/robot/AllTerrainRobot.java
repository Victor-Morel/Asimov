package model.robot;

import model.graph.Graph;
import model.graph.Node;


public class AllTerrainRobot extends Robot {

	public AllTerrainRobot(int _capacity, Graph _g, Node _node) {
		super(_capacity);
		this.g = _g.getSubGraph(1);
		this.node = _node;
	}

}
