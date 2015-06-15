package model.robot;

import model.graph.Graph;

/**
 * Created by victor on 20/05/15.
 */
public class AllTerrainRobot extends Robot {

	public AllTerrainRobot(TypeRecherche _type, int _capacity, Graph _g) {
		super(_type, _capacity);
		this.g = _g.getSubGraph(1);
		// TODO Auto-generated constructor stub
	}
}
