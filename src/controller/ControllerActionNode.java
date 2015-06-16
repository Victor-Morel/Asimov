package controller;


import model.graph.Node;
import view.Node.VNode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerActionNode implements ActionListener {

    protected Boolean node, fire, ignite;
    private Controller control;

    public ControllerActionNode(Controller control) {
        super();
        this.control = control;
        initialization();
    }

    public void initialization() {
        node = false;
        fire = false;
        ignite = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().matches("Noeud")) {
            this.initialization();
            node = true;
        } else if (e.getActionCommand().matches("Feu")) {
            this.initialization();
            fire = true;
        } else if (e.getActionCommand().matches("Enflammer")){
            this.initialization();
            ignite = true;
        }
    }


    /**
     * Affiche si nouveau noeud courant selectionne
     *
     * @param x parametre x de la souris
     * @param y parametre y de la souris
     * @return nouveau noeud courant ou null
     */
    protected Node checkCurrentNode(int x, int y) {
        Node currentNode;
        currentNode = clickOnNode(x, y);
        if (null != currentNode) {
            control.getWindow().getSheetDisplay().findVNode(currentNode).setColorBorder(1);
            checkOnlyNode(currentNode.getID());
            control.repaint();
        }
        return currentNode;
    }

    /**
     * Affiche si nouveau noeud courant selectionne
     *
     * @param x parametre x de la souris
     * @param y parametre y de la souris
     * @return nouveau noeud courant ou null
     */
    protected Node checkCurrentNodeAndAddEdge(int x, int y) {
        Node currentNode;
        currentNode = clickOnNode(x, y);
        if (null != currentNode) {
            control.getWindow().getSheetDisplay().findVNode(currentNode).setColorBorder(2);
            checkOnlyNode(currentNode.getID());
            control.repaint();
        }
        return currentNode;
    }

    /**
     * Ajouter noeud
     *
     * @param x coordonnee x du noeud
     * @param y coordonnee y du noeud
     */
    public void addNode(int x, int y) {
        VNode viewNode;
        Node n;

        viewNode = null;
        n = null;

        if (node) {
            n = (new Node(x, y));
            viewNode = new VNode(control.getWindow().getSheetDisplay(), n);
        } else if (fire) {
            n = new Node((double) x, (double) y, Node.FIRE_DEFAULT_TEMPERATURE);
            viewNode = new VNode(control.getWindow().getSheetDisplay(), n);
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
     * @param y coordonnee y de la souris
     * @return Node selectionne ou null
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
     * @param idCurrentNode
     */
    public void checkOnlyNode(int idCurrentNode) {
        for (Node n : control.getGraph().getAllNodes()) {
            if (n.getID() != idCurrentNode) {
                control.getWindow().getSheetDisplay().findVNode(n).setColorBorder(0);
            }
        }
    }

    public void setIgnite(Node ignite) {
        ignite.setIntensity(Node.FIRE_DEFAULT_TEMPERATURE);
        this.initialization();
    }
}
