package model.robot;

/**
 * Created by victor on 20/05/15.
 */
public class CaterpillarRobot extends Robot {

	public CaterpillarRobot(TypeRecherche _type, int _capacity) {
		super(_type, _capacity);
		this.g = g.getSubGraph(2);
		// TODO Auto-generated constructor stub
	}
}
