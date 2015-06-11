package model.robot;

/**
 * Created by victor on 20/05/15.
 */
public class AllTerrainRobot extends Robot {

	public AllTerrainRobot(TypeRecherche _type, int _capacity) {
		super(_type, _capacity);
		this.g = g.getSubGraph(1);
		// TODO Auto-generated constructor stub
	}
}
