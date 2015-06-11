package view.Edge;

import model.graph.Edge;
import view.SheetDisplay;

import java.awt.*;

public class VEdgePlat extends AVEdge {

    public VEdgePlat(SheetDisplay sheetDisplay, Edge edge) {
        super(sheetDisplay, edge);
    }

    @Override
    public void drawEdge(Graphics graph) {
        graph.setColor(Color.white);
        super.drawEdge(graph);
    }

}
