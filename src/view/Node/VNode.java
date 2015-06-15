package view.Node;

import model.graph.Node;
import view.SheetDisplay;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;


public class VNode implements Observer {

    protected final SheetDisplay sheetDisplay;

    /**
     * Noeud correspondant a la vue
     */
    protected Node node;
    Graphics2D g2d;

    /**
     * Rayon du cercle
     */
    private final int rayon = 7;

    public VNode(SheetDisplay sheetDisplay, Node node) {
        this.sheetDisplay = sheetDisplay;
        this.node = node;
        this.node.addObserver(this);
    }

    /**
     * Mise à jour du Noeud
     *
     * @param arg0
     * @param arg1
     */
    @Override
    public void update(Observable arg0, Object arg1) {
        sheetDisplay.repaint();
    }

    /**
     * Dessine le Noeud
     *
     * @param graph
     */
    public void drawNode(Graphics graph) {

        if (node.getIntensity() == 0) {
            graph.setColor(Color.white);
        } else {
            graph.setColor(Color.RED);
        }
        g2d = (Graphics2D) graph;
        g2d.fillOval((int) node.getX() - rayon, (int) node.getY() - rayon, 2 * rayon, 2 * rayon);
        graph.setColor(Color.BLACK);
        graph.drawOval((int) node.getX() - rayon, (int) node.getY() - rayon, 2 * rayon, 2 * rayon);
        if (node.isCurrentNode()) {
            graph.setColor(Color.blue);
            graph.drawOval((int) node.getX() - rayon, (int) node.getY() - rayon, 2 * rayon, 2 * rayon);
        }
    }
}
