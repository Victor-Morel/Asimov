package utils;

import java.awt.*;

/**
 * Created by Logan Paul on 02/06/2015.
 */
public class EuclidianDistance {

    public static double getDistance(Point a, Point b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }
}
