package controller;


import model.graph.Node;
import view.Node.AVNode;
import view.Node.VNodeFire;
import view.Node.VNodeNormal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerActionNode implements ActionListener {

    protected Boolean node, fire;
    private Controller2 control;

    public ControllerActionNode(Controller2 control) {
        super();
        this.control = control;
        initialization();
    }

    private void initialization() {
        node = false;
        fire = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().matches("Noeud")) {
            this.initialization();
            node = true;
        } else if (e.getActionCommand().matches("Feu")) {
            this.initialization();
            fire = true;
        }
    }


    /**
     * Affiche si nouveau noeud courant selectionner
     *
     * @param x parametre x de la souris
     * @param y parametre y de la souris
     * @return nouveau noeud courant ou null
     */
    protected Node checkCurrentNode(int x, int y) {
        Node currentNode;
        currentNode = clickOnNode(x, y);
        if (null != currentNode) {
            currentNode.setCurrentNode(true);
            checkOnlyNode(currentNode.getID());
        }
        return currentNode;
    }

    /**
     * Ajouter noeud
     *
     * @param x cordonnee x du noeud
     * @param y cordonnee y du noeud
     */
    public void addNode(int x, int y) {
        AVNode viewNode;
        Node n;

        viewNode =null;
        n = null;

        if (node) {
            n = (new Node(x, y));
            viewNode = new VNodeNormal(control.getWindow().getSheetDisplay(), n);
        } else if (fire) {
            n = new Node((double)x, (double)y, Node.FIRE_DEFAULT_TEMPERATURE);
            viewNode = new VNodeFire(control.getWindow().getSheetDisplay(), n);
        }

        control.getGraph().addNode(n);
        control.getWindow().getSheetDisplay().addNode(viewNode);
        control.repaint();

        this.initialization();
    }

    /**
     * Verifie si dans il existe un noeud au coordonnee x et y
     *
     * @param x coordonnee x de la souris
     * @param y coordonnee y
     * @return Node selectioner ou null
     */
    protected Node clickOnNode(int x, int y) {
        for (Node n : control.getGraph().getAllNodes()) {
            for (int i = -10; i < 10; i++) {
                for (int j = -10; j < 10; j++) {
                    if ((x == (int) n.getX() + i) && (y == (int) n.getY() + j)) {
                        return n;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Verifie qu'il y a un seul noeud courant
     *
     * @param currentNode
     */
    public void checkOnlyNode(int currentNode) {
        for (Node n : control.getGraph().getAllNodes()) {
            if (n.getID() != currentNode && n.isCurrentNode()) {
                n.setCurrentNode(false);
            }
        }
    }

}
