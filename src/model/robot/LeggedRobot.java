package model.robot;

import model.graph.Graph;
import model.graph.Node;

/**
 * Created by victor on 20/05/15.
 */
public class LeggedRobot extends Robot {

	public LeggedRobot(TypeRecherche _type, int _capacity, Graph _g, Node _node) {
		super(_type, _capacity);
		this.g = _g.getSubGraph(3);
		this.node = _node;
		// TODO Auto-generated constructor stub
	}
}
