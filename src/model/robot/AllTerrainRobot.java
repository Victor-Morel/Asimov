package model.robot;

import model.graph.Graph;
import model.graph.Node;

/**
 * Sous classe de robot
 */
public class AllTerrainRobot extends Robot {

	public AllTerrainRobot(Node _node, ResearchType _researchType) {
		super(_node, _researchType);
	}

	public void generateSubGraph(Graph _g) {
		this.g = _g.getSubGraph(1);
	}

}
