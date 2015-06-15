package model.robot;

import model.graph.Graph;
import model.graph.Node;


public class AllTerrainRobot extends Robot {

	public AllTerrainRobot(int _capacity, Node _node) {
		super(_capacity, _node);
	}

	public void generateSubGraph(Graph _g) {
		this.g = _g.getSubGraph(1);
	}

}
