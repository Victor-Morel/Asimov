package view.Edge;


import model.graph.Edge;
import view.SheetDisplay;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Observable;
import java.util.Observer;

public abstract class AVEdge implements Observer {


    protected final SheetDisplay sheetDisplay;
    protected Edge edge;

    public AVEdge(SheetDisplay sheetDisplay, Edge edge) {
        this.sheetDisplay = sheetDisplay;
        setT(edge);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        sheetDisplay.repaint();
    }

    public void drawEdge(Graphics graph) {
        Graphics2D graph2 = (Graphics2D) graph;
        graph2.draw(
                new Line2D.Double(
                        edge.getSource().getX(),
                        edge.getSource().getY(),
                        edge.getDestination().getX(),
                        edge.getDestination().getY()
                )
        );
    }

    public void setT(Edge edge) {
        this.edge = edge;
        this.edge.addObserver(this);
    }

}
