package view.Edge;

import model.graph.Edge;

import java.awt.*;

public class VEdgeInonde extends AVEdge {

    public VEdgeInonde(Edge edge) {
        super(edge);
    }

    @Override
    public void drawEdge(Graphics graph) {
        graph.setColor(new Color(0,255, 65));
        super.drawEdge(graph);
    }
}
