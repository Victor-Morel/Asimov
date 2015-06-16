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
     * Methode de calcul de la distance totale a parcourir a travers le meilleur chemin
     *
     * @return la distance a parcourir, soit la somme des valeurs des aretes a parcourir
     */
    public int getDistanceValue() {
        distanceValue = 0;
        for (Edge e : getResultGraph().getAllEdges()) {
            distanceValue += e.getValuation();
        }
        return distanceValue;
    }

    /**
     * Genere un sous-graphe contenant le meilleur chemin trouve entre le noeud source et le noeud destination
     * Le sous-graphe ne contient que les aretes et les noeuds empruntes par le meilleur chemin
     * Le sous-graphe genere est stocke dans resultGraph
     */
    public abstract void generateBestPath();

    /**
     * @return le sous-graphe correspondant au meilleur chemin
     */
    public Graph getResultGraph() {
        return resultGraph;
    }
}
