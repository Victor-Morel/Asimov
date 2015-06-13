package view.Node;

import model.graph.Node;
import view.SheetDisplay;

import java.awt.*;

public class VNodeNormal extends AVNode {

    public VNodeNormal(SheetDisplay sheetDisplay, Node n) {

        super(sheetDisplay, n);
    }

    public void drawNode(Graphics graph) {
        graph.setColor(Color.WHITE);
        super.drawNode(graph);
    }
}
