package utils;

import model.graph.Node;

public class EuclidianDistance {

    public static double getDistance(Node a, Node b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }
}
