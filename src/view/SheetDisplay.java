package view;

import controller.ControllerMouse;
import view.Edge.AVEdge;
import view.Node.AVNode;
import view.Robot.AVRobot;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class SheetDisplay extends JPanel {

    private ArrayList<AVNode> nodes; // la liste des nodes enregistrees
    private ArrayList<AVEdge> edges; // la liste des nodes enregistrees
    private ArrayList<AVRobot> robots; // la liste des nodes enregistrees

    public SheetDisplay(ControllerMouse controlMouse) {
        super();
        initialization();
        addMouseListener(controlMouse);
    }

    private void initialization() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        robots = new ArrayList<>();
    }


    public void reset() {
        initialization();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        showNodes(g);
        showEdges(g);
        showRobots(g);
    }

    private void showRobots(Graphics g) {
        for (AVRobot avRobot : robots) {
            avRobot.drawRobot(g);
        }
    }

    private void showNodes(Graphics g) {
        for (AVNode avNode : nodes) {
            avNode.drawNode(g);
        }
    }

    private void showEdges(Graphics graphics) {
        for (AVEdge avEdge : edges) {
            avEdge.drawEdge(graphics);
        }
    }

    public void addEdge(AVEdge edge) {
        edges.add(edge);
    }

    public void addNode(AVNode node) {
        nodes.add(node);
    }

    public void addRobot(AVRobot robot) {
        robots.add(robot);
    }

}
