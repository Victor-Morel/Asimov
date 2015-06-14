package view;

import controller.ControllerMouse;
import view.Edge.AVEdge;
import view.Node.AVNode;
import view.Robot.AVRobot;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class SheetDisplay extends JPanel {

    /**
     * Liste des diffentes vues des noeuds
     */
    private ArrayList<AVNode> nodes; // la liste des nodes enregistrees
    /**
     * Liste des diffentes vues des arcs
     */
    private ArrayList<AVEdge> edges; // la liste des nodes enregistrees
    /**
     * Liste des diffentes vues des robots
     */
    private ArrayList<AVRobot> robots; // la liste des nodes enregistrees

    /**
     * Conscruit un sheetDisplay
     * @param controlMouse Controle de la souris
     */
    public SheetDisplay(ControllerMouse controlMouse) {
        super();
        initialization();
        addMouseListener(controlMouse);
    }

    /**
     * Initialise les listes
     */
    private void initialization() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        robots = new ArrayList<>();
    }

    /**
     * Réinitialise les listes et redessine le JPanel vide
     *
     */
    public void reset(){
        initialization();
        repaint();
    }

    /**
     * Dessine les elements du JPanel
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        showNodes(g);
        showEdges(g);
        showRobots(g);
    }

    /**
     * Dessine les robots
     * @param g
     */
    private void showRobots(Graphics g) {
        for (AVRobot avRobot : robots) {
            avRobot.drawRobot(g);
        }
    }

    /**
     * Dessine les Noeuds
     * @param g
     */
    private void showNodes(Graphics g) {
        for (AVNode avNode : nodes) {
            avNode.drawNode(g);
        }
    }

    /**
     * Dessine les arcs
     * @param graphics
     */
    private void showEdges(Graphics graphics) {
        for (AVEdge avEdge : edges) {
            avEdge.drawEdge(graphics);
        }
    }

    /**
     * Ajouter un arc
     * @param edge
     */
    public void addEdge(AVEdge edge) {
        edges.add(edge);
    }

    /**
     * Ajouter un noeud
     * @param node
     */
    public void addNode(AVNode node) {
        nodes.add(node);
    }

    /**
     * Ajouter un robot
     * @param robot
     */
    public void addRobot(AVRobot robot) {
        robots.add(robot);
    }

}
