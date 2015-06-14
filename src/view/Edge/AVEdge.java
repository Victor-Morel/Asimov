package view.Edge;


import model.graph.Edge;

import java.awt.*;
import java.awt.geom.Line2D;

public abstract class AVEdge {


    /**
     * arc correspondant a la vue
     */
    protected Edge edge;

    /**
     * Construit un AVEdge avec un sheetDisplay et un edge
     * @param edge Arc de la vue
     */
    public AVEdge(Edge edge) {
        this.edge = edge;
    }

    /**
     * Dessine un arc
     * @param graph graphics
     */
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



}
