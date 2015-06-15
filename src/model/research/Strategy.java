package model.research;

import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;

/**
 * Created by victor on 20/05/15.
 */
public abstract class Strategy {
	protected Node source;
	protected Node destination;
	protected Graph graph;
	protected Graph resultGraph;
	protected int distanceValue;

	/**
	 * Méthode de calcul de la distance totale à parcourir à travers le meilleur chemin
	 * @return la distance à parcourir, soit la somme des valeurs des arètes à parcourir
	 */
	public int getDistanceValue() {
		distanceValue = 0;
		for(Edge e : getResultGraph().getAllEdges()){
			distanceValue+=e.getValuation();
		}
		return distanceValue;
	}

	/**
	 * Génère un sous-graphe contenant le meilleur chemin trouvé entre le noeud source et le noeud destination
	 * Le sous-graphe ne contient que les arètes et les noeuds empruntés par le meilleur chemin
	 * Le sous-graphe généré est stocké dans resultGraph
	 */
	public abstract void generateBestPath();

	/**
	 * @return le sous-graphe correspondant au meilleur chemin
	 */
	public Graph getResultGraph() {
		return resultGraph;
	}
}
