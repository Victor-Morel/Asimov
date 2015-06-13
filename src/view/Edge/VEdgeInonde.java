package view.Edge;

import model.graph.Edge;
import view.SheetDisplay;

import java.awt.*;

/**
 * Created by TD on 6/13/2015.
 */
public class VEdgeInonde extends AVEdge {

    public VEdgeInonde(SheetDisplay sheetDisplay, Edge edge) {
        super(sheetDisplay, edge);
    }

    @Override
    public void drawEdge(Graphics graph) {
        graph.setColor(Color.CYAN);
        super.drawEdge(graph);
    }
}
