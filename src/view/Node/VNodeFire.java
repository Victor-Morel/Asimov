package view.Node;

import model.graph.Node;
import view.SheetDisplay;

import java.awt.*;

public class VNodeFire extends AVNode {

    public VNodeFire(SheetDisplay sheetDisplay, Node n) {
        super(sheetDisplay, n);
    }

    @Override
    public void drawNode(Graphics graph) {
        if(super.node.getIntensity() == 0){
         changeNode();
        }
        else {
            graph.setColor(Color.RED);
            super.drawNode(graph);
        }
    }

    public void changeNode(){
        sheetDisplay.addNode(new VNodeNormal(super.sheetDisplay, super.node));
        sheetDisplay.removeNode(this);

    }
}
