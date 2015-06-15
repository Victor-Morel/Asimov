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
	 * M�thode de calcul de la distance totale � parcourir � travers le meilleur chemin
	 * @return la distance � parcourir, soit la somme des valeurs des ar�tes � parcourir
	 */
	public int getDistanceValue() {
		distanceValue = 0;
		for(Edge e : getResultGraph().getAllEdges()){
			distanceValue+=e.getValuation();
		}
		return distanceValue;
	}

	/**
	 * G�n�re un sous-graphe contenant le meilleur chemin trouv� entre le noeud source et le noeud destination
	 * Le sous-graphe ne contient que les ar�tes et les noeuds emprunt�s par le meilleur chemin
	 * Le sous-graphe g�n�r� est stock� dans resultGraph
	 */
	public abstract void generateBestPath();

	/**
	 * @return le sous-graphe correspondant au meilleur chemin
	 */
	public Graph getResultGraph() {
		return resultGraph;
	}
}
