package view;

import view.Node.AVNode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;


public class SheetDisplay extends JPanel {

    private ArrayList<AVNode> nodes; // la liste des nodes enregistrees

    public SheetDisplay() {
        super();
        nodes = new ArrayList<AVNode>();
    }


    public void addNode(AVNode node) {
        nodes.add(node);
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
        g.setColor(Color.black);
        g.fillRect(0, 0, dim.width, dim.height);
        g.setColor(c);
        showTurtles(g);
    }

    public void showTurtles(Graphics g) {
        for (Iterator<AVNode> it = nodes.iterator(); it.hasNext(); ) {
            AVNode vt = it.next();
            vt.drawNode(g);
        }
    }

    public ArrayList<AVNode> getNodes() {
        return nodes;
    }
}
