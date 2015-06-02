package utils;

import model.graph.Node;
/**
 * Created by Logan Paul on 02/06/2015.
 */
public class EuclidianDistance {

    public static double getDistance(Node a, Node b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }
}
