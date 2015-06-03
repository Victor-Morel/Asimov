package view.VNode;

import model.graph.Node;
import view.SheetDisplay;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;


public abstract class AVNode implements Observer {

    protected final SheetDisplay sheetDisplay;
    protected Node node;

    public AVNode(SheetDisplay sheetDisplay, Node Node) {
        this.sheetDisplay = sheetDisplay;
        setT(Node);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        sheetDisplay.repaint();
    }

    public abstract void drawNode(Graphics graph);

    public abstract boolean contains(int x, int y);

    public Node getT() {
        return node;
    }

    public void setT(Node node) {
        this.node = node;
        this.node.addObserver(this);
    }
}
