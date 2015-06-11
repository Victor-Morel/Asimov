package view;

import view.Edge.AVEdge;
import view.Node.AVNode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;


public class SheetDisplay extends JPanel {

    private ArrayList<AVNode> nodes; // la liste des nodes enregistrees
    private ArrayList<AVEdge> edges; // la liste des nodes enregistrees

    public SheetDisplay() {
        super();
        nodes = new ArrayList<AVNode>();
        edges = new ArrayList<AVEdge>();
    }


    public void reset() {
        for (Iterator<AVNode> it = nodes.iterator(); it.hasNext(); ) {
            AVNode vt = it.next();
            vt.getT().reset();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color c = g.getColor();
        Dimension dim = getSize();
        g.setColor(Color.gray);
        g.fillRect(0, 0, dim.width, dim.height);
        g.setColor(c);
        showNodes(g);
        showEdges(g);
    }

    public void showNodes(Graphics g) {
        for (Iterator<AVNode> it = nodes.iterator(); it.hasNext(); ) {
            AVNode vt = it.next();
            vt.drawNode(g);
        }
    }

    public void showEdges(Graphics g) {
        for (Iterator<AVEdge> it = edges.iterator(); it.hasNext(); ) {
            AVEdge vt = it.next();
            vt.drawEdge(g);
        }
    }


    public void addEdge(AVEdge edge) {
        edges.add(edge);
    }

    public void addNode(AVNode node) {
        nodes.add(node);
    }
}
