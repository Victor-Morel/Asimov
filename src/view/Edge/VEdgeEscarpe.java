package view.Edge;

import model.graph.Edge;

import java.awt.*;

public class VEdgeEscarpe extends AVEdge {

    public VEdgeEscarpe(Edge edge) {
        super(edge);
    }

    @Override
    public void drawEdge(Graphics graph) {
        graph.setColor(Color.magenta);
        super.drawEdge(graph);
    }
}
