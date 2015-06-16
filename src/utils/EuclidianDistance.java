package utils;

import model.graph.Node;

public class EuclidianDistance {

    /**
     * Retourne la distance euclidienne entre deux noeuds
     * @param a Un noeud
     * @param b Un autre noeud
     * @return la distance entre ces noeuds
     */
    public static double getDistance(Node a, Node b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }
}
