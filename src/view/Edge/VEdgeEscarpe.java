package view.Edge;

import model.graph.Edge;

import java.awt.*;

public class VEdgeEscarpe extends AVEdge {

    public VEdgeEscarpe(Edge edge) {
        super(edge);
    }

    @Override
    public void drawEdge(Graphics graph) {
        graph.setColor(new Color(0, 29,255));
        super.drawEdge(graph);
    }
}
