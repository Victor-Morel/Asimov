package model.graph;

import java.util.Observable;

public class Node extends Observable {

	/**
	 * coordonnée x d'un noeud
	 */
	private double x;

	/**
	 * coordonnée y d'un noeud
	 */
	private double y;

	/**
	 * etiquette du noeud
	 */
    private Label label;
    /**
     * l'intensite du noeud en feu. 0 correspond a un noeud non-enflamme
     */
    private int intensity;
    /**
     * ID unique du noeud
     */
    protected int uniqueID;
    /**
     * nombre total d'instances de Noeud
     */
    private static int nodesNumber = 0;

	private TypeNode type;

	/**
	 * Construit un noeud avec une etiquette et une intensite
	 * @param _id identifiant du noeud
	 * @param _x coordonnée x du noeud
	 * @param _y coordonnée y du noeud
	 * @param _type type du noeud
	 */
	public Node(int _id, double _x, double _y, TypeNode _type) {
		this.uniqueID = _id;
		this.x = _x;
		this.y = _y;
		this.type = _type;
		if (_type.getText() == "NORMAL") this.intensity = 0;
		if (_type.getText() == "INCENDIE") this.intensity = 100;
	}

	public Node(int _id, double _x, double _y, int intensity) {
		this.uniqueID = _id;
		this.x = _x;
		this.y = _y;
		this.intensity = intensity;
	}


	/**
	 * Construit un noeud avec une etiquette et une intensite
	 * @param _id identifiant du noeud
	 * @param _x coordonnée x du noeud
	 * @param _y coordonnée y du noeud
	 */
	public Node(int _id, double _x, double _y) {
		this(_id, _x, _y, TypeNode.NORMAL);
	}
    
    
    /**
     * Fonction de refroidissement d'un noeud
     */
    public void cooling(double capacity) {
    	this.intensity = (int)(this.intensity * (1-capacity)); 
    }
    
    /**
     * 
     * @return l'unique ID du noeud
     */
    public int getID() { return uniqueID ;} 
    /**
     * Specifie l etiquette du noeud
     * @param _label
     */
    public void setLabel(Label _label) { this.label = _label; }
    /**
     * 
     * @return  l etiquette du noeud
     */
    public Label getLabel() { return label; }
    
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
    
	@Override
	public String toString() {
		return ""+getID();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		return uniqueID == other.uniqueID;
	}


	public void reset() {
		//TODO destroy instance
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public TypeNode getType() {
		return type;
	}

	public void setType(TypeNode type) {
		this.type = type;
	}
}
