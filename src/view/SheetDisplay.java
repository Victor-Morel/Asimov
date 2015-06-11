package view;

import controller.ControllerMouse;
import view.Edge.AVEdge;
import view.Node.AVNode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class SheetDisplay extends JPanel {

    private ArrayList<AVNode> nodes; // la liste des nodes enregistrees
    private ArrayList<AVEdge> edges; // la liste des nodes enregistrees

    public SheetDisplay(ControllerMouse controlMouse) {
        super();
        nodes = new ArrayList<>();
        edges = new ArrayList<>();

        addMouseListener(controlMouse);
    }


    public void reset() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        showNodes(g);
        showEdges(g);
    }

    public void showNodes(Graphics g) {
        for (AVNode avNode : nodes) {
            avNode.drawNode(g);
        }
    }

    public void showEdges(Graphics g) {
        for (AVEdge avEdge : edges) {
            avEdge.drawEdge(g);
        }
    }


    public void addEdge(AVEdge edge) {
        edges.add(edge);
    }

    public void addNode(AVNode node) {
        nodes.add(node);
    }
}
