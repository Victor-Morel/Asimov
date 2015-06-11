package view.Edge;

import model.graph.Edge;
import view.SheetDisplay;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Created by TD on 6/11/2015.
 */
public class VEdge extends AVEdge {

    protected Point2D.Double p1;
    protected Point2D.Double p2;
    protected Point point1;
    protected Point point2;

    public VEdge(SheetDisplay sheetDisplay, Edge edge) {
        super(sheetDisplay, edge);
    }

    @Override
    public void drawEdge(Graphics graph) {
        Graphics2D graph2 = (Graphics2D) graph;
        graph2.setColor(Color.BLACK);
        graph2.draw(
                new Line2D.Double(
                        edge.getSource().getX(),
                        edge.getSource().getY(),
                        edge.getDestination().getX(),
                        edge.getDestination().getY()
                )
        );

    }

    @Override
    public boolean contains(int x, int y) {
        return false;
    }
}
