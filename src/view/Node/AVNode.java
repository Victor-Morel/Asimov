package view.Node;

import model.graph.Node;
import view.SheetDisplay;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;


public abstract class AVNode implements Observer {

    protected final SheetDisplay sheetDisplay;
    protected Node node;
    Graphics2D g2d;

    private final int rayon = 7;

    public AVNode(SheetDisplay sheetDisplay, Node node) {
        this.sheetDisplay = sheetDisplay;
        this.node = node;
        this.node.addObserver(this);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        sheetDisplay.repaint();
    }

    public void drawNode(Graphics graph) {
        //Graphics2D g2d;
        g2d = (Graphics2D) graph;
        g2d.fillOval((int) node.getX() - rayon, (int) node.getY() - rayon, 2 * rayon, 2 * rayon);
        if (node.isCurrentNode()) {
            graph.setColor(Color.blue);
            graph.drawOval((int) node.getX() - rayon, (int) node.getY() - rayon, 2 * rayon, 2 * rayon);
        }
    }

}
