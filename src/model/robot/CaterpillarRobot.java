package model.robot;

import model.graph.Graph;
import model.graph.Node;

/**
 * Sous classe de robot
 */
public class CaterpillarRobot extends Robot {

	public CaterpillarRobot(Node _node, ResearchType _researchType) {
		super(_node, _researchType);
		// TODO Auto-generated constructor stub
	}

	public void generateSubGraph(Graph _g) {
		this.g = _g.getSubGraph(2);
	}
}
