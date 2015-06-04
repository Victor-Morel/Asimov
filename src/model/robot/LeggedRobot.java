package model.robot;

/**
 * Created by victor on 20/05/15.
 */
public class LeggedRobot extends Robot {

	public LeggedRobot(TypeRecherche _type, int _capacity) {
		super(_type, _capacity);
		this.g = g.getSubGraph(3);
		// TODO Auto-generated constructor stub
	}
}
