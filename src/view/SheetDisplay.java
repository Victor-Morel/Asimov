package view;

import controller.ControllerMouse;
import model.graph.Node;
import view.Edge.AVEdge;
import view.Image.BackgroundImage;
import view.Node.VNode;
import view.Robot.AVRobot;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class SheetDisplay extends JPanel {

    private BackgroundImage image;

    private ArrayList<VNode> nodes; // la liste des nodes enregistrees
    /**
     * Liste des diffentes vues des arcs
     */
    private ArrayList<AVEdge> edges; // la liste des nodes enregistrees
    /**
     * Liste des diffentes vues des robots
     */
    private ArrayList<AVRobot> robots; // la liste des nodes enregistrees

    /**
     * Construit un sheetDisplay
     *
     * @param controlMouse Controle de la souris
     */
    public SheetDisplay(ControllerMouse controlMouse) {
        super();
        initialization();
        addMouseListener(controlMouse);
        image = new BackgroundImage();
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
     * Reinitialise les listes et redessine le JPanel vide
     */
    public void reset() {
        initialization();
        repaint();
    }

    /**
     * Dessine les elements du JPanel
     *
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        image.drawImage(g);
        showEdges(g);
        showNodes(g);
        showRobots(g);
    }

    /**
     * Dessine les robots
     *
     * @param g
     */
    private void showRobots(Graphics g) {
        for (AVRobot avRobot : robots) {
            avRobot.drawRobot(g);
        }
    }

    /**
     * Dessine les Noeuds
     *
     * @param g
     */
    private void showNodes(Graphics g) {
        for (VNode avNode : getNodes()) {
            avNode.drawNode(g);
        }
    }

    /**
     * Dessine les arcs
     *
     * @param graphics
     */
    private void showEdges(Graphics graphics) {
        for (AVEdge avEdge : edges) {
            avEdge.drawEdge(graphics);
        }
    }

    /**
     * Ajouter un arc
     *
     * @param edge
     */
    public void addEdge(AVEdge edge) {
        edges.add(edge);
    }

    /**
     * Ajouter un noeud
     *
     * @param node
     */
    public void addNode(VNode node) {
        getNodes().add(node);
    }

    /**
     * Ajouter un robot
     *
     * @param robot
     */
    public void addRobot(AVRobot robot) {
        robots.add(robot);
    }

    public BackgroundImage getImage() {
        return image;
    }


    /**
     * Liste des diffentes vues des noeuds
     */
    public ArrayList<VNode> getNodes() {
        return nodes;
    }

    public VNode findVNode(Node _node) {
        for (VNode vn : getNodes()) {
            if (vn.getNode().equals(_node)) {
                return vn;
            }
        }
        return null;
    }

}
