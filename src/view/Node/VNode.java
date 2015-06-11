package view.Node;

import model.graph.Node;
import view.SheetDisplay;

import java.awt.*;

public class VNode extends AVNode {


    protected static final int rp = 10, rb = 5;

    protected Polygon arrow;
    protected Point point;

    public VNode(SheetDisplay sheetDisplay, Node n) {
        super(sheetDisplay, n);
    }


    @Override
    public void drawNode(Graphics graph) {

        int rayon;
        Graphics2D g2d;

        rayon = 5;
        g2d = (Graphics2D) graph;
        g2d.setColor(Color.WHITE);
        g2d.fillOval((int) node.getX() - rayon, (int) node.getY() - rayon, 2 * rayon, 2 * rayon);

    }
}
