package model.robot;

import model.graph.Graph;
import model.graph.Node;

/**
 * Created by victor on 20/05/15.
 */
public class CaterpillarRobot extends Robot {

	public CaterpillarRobot(ResearchType _type, int _capacity, Graph _g, Node _node) {
		super(_type, _capacity);
		this.g = _g.getSubGraph(2);
		this.node = _node;
		// TODO Auto-generated constructor stub
	}
}
