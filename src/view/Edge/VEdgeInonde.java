package view.Edge;

import model.graph.Edge;

import java.awt.*;

public class VEdgeInonde extends AVEdge {

    public VEdgeInonde(Edge edge) {
        super(edge);
    }

    @Override
    public void drawEdge(Graphics graph) {
        graph.setColor(Color.CYAN);
        super.drawEdge(graph);
    }
}
