package view.Node;

import model.graph.Node;
import view.SheetDisplay;

import java.awt.*;

/**
 * Created by TD on 6/11/2015.
 */
public class VFire extends AVNode {


    public VFire(SheetDisplay sheetDisplay, Node n) {
        super(sheetDisplay, n);
    }


    @Override
    public void drawNode(Graphics graph) {

        int rayon;
        Graphics2D g2d;

        rayon = 5;
        g2d = (Graphics2D) graph;
        g2d.setColor(Color.RED);
        g2d.fillOval((int) node.getX() - rayon, (int) node.getY() - rayon, 2 * rayon, 2 * rayon);
    }
}
