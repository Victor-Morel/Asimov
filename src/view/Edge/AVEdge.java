package view.Edge;


import model.graph.Edge;
import view.SheetDisplay;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public abstract class AVEdge implements Observer {


    protected final SheetDisplay sheetDisplay;
    protected Edge edge;

    public AVEdge(SheetDisplay sheetDisplay, Edge edge) {
        this.sheetDisplay = sheetDisplay;
        setT(edge);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        sheetDisplay.repaint();
    }

    public abstract void drawEdge(Graphics graph);

    public abstract boolean contains(int x, int y);

    public Edge getT() {
        return edge;
    }

    public void setT(Edge edge) {
        this.edge = edge;
        this.edge.addObserver(this);
    }
}
