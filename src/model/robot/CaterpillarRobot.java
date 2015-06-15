package model.robot;

import model.graph.Graph;
import model.graph.Node;

/**
 * Created by victor on 20/05/15.
 */
public class CaterpillarRobot extends Robot {

	public CaterpillarRobot(double _capacity, Node _node) {
		super(_capacity, _node);
		// TODO Auto-generated constructor stub
	}

	public void generateSubGraph(Graph _g) {
		this.g = _g.getSubGraph(2);
	}
}
