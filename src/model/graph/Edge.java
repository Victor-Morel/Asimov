package model.graph;

public class Edge {

	private int valuation;
	/**
	 * Noeud source de l'arete : equivalent a destination car non-oriente
	 */
	private Node source;
	/**
	 * Noeud destination de l'arete : equivalent a source car non-oriente
	 */
	private Node destination;
	/**
	 * Type d'arete
	 */
	private PossibleStates state;






	/**
	 * Liste des types d'aretes possibles
	 */
	private enum PossibleStates {
		PLAT,
		ESCARPE,
		INONDE
	}


	int nd1, nd2;
	Type type;


	public Edge(int nd1, int nd2, Type type) {
		this.nd1 = nd1;
		this.nd2 = nd2;
		this.type = type;
	}


	public Edge(Node _v1, Node _v2, int _valuation, PossibleStates ps) {
		this.valuation = _valuation;
		this.source = _v1;
		this.destination = _v2;
		this.state = ps;
	}

	/**
	 * 
	 * @return la valeur de l'arete
	 */
	public int getValuation() {
		return valuation;
	}

	/**
	 *
	 * @param valuation
	 */
	public void setValuation(int valuation) {
		this.valuation = valuation;
	}

	/**
	 *
	 * @return le noeud source de l'arete
	 */
	public Node getSource() {
		return source;
	}

	/**
	 *
	 *
	 *            noeud source
	 */
	public void setSource(Node _n) {
		this.source = _n;
	}

	/**
	 *
	 * @return le noeud destination de l'arete
	 */
	public Node getDestination() {
		return destination;
	}

	/**
	 *
	 *
	 *            noeud destination
	 */
	public void setDestination(Node _n) {
		this.destination = _n;
	}

	@Override
	public String toString() {
		return source.getLabel().toString() + " ==> "
				+ destination.getLabel().getLabel() + "(" + valuation + ")";
	}

}
