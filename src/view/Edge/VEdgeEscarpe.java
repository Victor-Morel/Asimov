package view.Edge;

import model.graph.Edge;
import view.SheetDisplay;

import java.awt.*;

public class VEdgeEscarpe extends AVEdge {

    public VEdgeEscarpe(SheetDisplay sheetDisplay, Edge edge) {
        super(sheetDisplay, edge);
    }

    @Override
    public void drawEdge(Graphics graph) {
        graph.setColor(Color.magenta);
        super.drawEdge(graph);
    }
}
