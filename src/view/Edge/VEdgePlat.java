package view.Edge;

import model.graph.Edge;

import java.awt.*;

public class VEdgePlat extends AVEdge {

    public VEdgePlat(Edge edge) {
        super(edge);
    }

    @Override
    public void drawEdge(Graphics graph) {
        graph.setColor(Color.black);
        super.drawEdge(graph);
    }

}
