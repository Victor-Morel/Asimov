package model.graph;

/**
 * Created by victor on 20/05/15.
 */
public class Node {
	/**
	 * etiquette du noeud
	 */
    private Label label;
    /**
     * ID unique du noeud
     */
    private int uniqueID;
    /**
     * nombre total d'instances de Noeud
     */
    private static int nodesNumber = 0;
    
    /**
     * Construit un noeud avec une etiquette
     * @param _label etiquette du noeud
     */
    public Node(Label _label){
    	this.label = _label;
    	this.uniqueID = nodesNumber;
    	Node.nodesNumber ++;
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
		if (uniqueID != other.uniqueID)
			return false;
		return true;
	}	
}
