package view.Node;

import model.graph.Node;
import view.SheetDisplay;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;


public abstract class AVNode implements Observer {

    protected final SheetDisplay sheetDisplay;
    protected Node node;

    public AVNode(SheetDisplay sheetDisplay, Node node) {
        this.sheetDisplay = sheetDisplay;
        setT(node);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        sheetDisplay.repaint();
    }

    public void drawNode(Graphics graph) {
        int rayon;
        Graphics2D g2d;
        rayon = 5;
        g2d = (Graphics2D) graph;
        g2d.fillOval((int) node.getX() - rayon, (int) node.getY() - rayon, 2 * rayon, 2 * rayon);
    }

    public void setT(Node node) {
        this.node = node;
        this.node.addObserver(this);
    }

}
