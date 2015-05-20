package model.graph;

/**
 * Created by victor on 20/05/15.
 */
public class FireNode extends Node {

	private int intensity;
	
	public FireNode(Label _label) {
		super(_label);
	}
	
	public FireNode(Label _label, int intensity) {
		super(_label);
		this.intensity = intensity;
	}

	/**
	 * @return the intensity
	 */
	public int getIntensity() {
		return intensity;
	}

	/**
	 * @param intensity the intensity to set
	 */
	public void setIntensity(int intensity) {
		this.intensity = intensity;
	}
}
