package model.graph;

public class Node {

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

	Type type;

	/**
     * Construit un noeud avec une etiquette
     */
	public Node(int id, double _x, double _y, Type _type) {
		this.intensity = 0;
		this.uniqueID = id;
		this.x = _x;
		this.y = _y;
		type = _type;
	}
    
    /**
     * Construit un noeud avec une etiquette et une intensite
     * @param _label etiquette du noeud
     * @param _intensity chaleur du noeud
     */
    public Node(Label _label, int _intensity) {
		this.label = _label;
    	this.intensity = _intensity;
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
		return ""+label;
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
}
