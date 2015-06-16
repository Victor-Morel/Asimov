package controller;

import model.graph.Node;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControllerMouse implements MouseListener {


    private Controller controlAction;
    private Node node1, node2, currentNode;

    /**
     * Construit un controleur pour la souris
     *
     * @param _controlAction controleur des actions sur la fenetre
     */
    public ControllerMouse(Controller _controlAction) {
        super();
        this.controlAction = _controlAction;
        initialization();
    }

    private void initialization() {
        currentNode = null;
        node1 = null;
        node2 = null;
    }

    /**
     * Si la souris a clique
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        controlAction.getControlNode().checkCurrentNode(e.getX(), e.getY());
        if (checkAddNode()) {
            controlAction.getControlNode().addNode(e.getX(), e.getY());
            initialization();

        }
        if (checkAddEdge()) {
            currentNode = controlAction.getControlNode().checkCurrentNodeAndAddEdge(e.getX(), e.getY());
            if (null != currentNode && node1 == null) {
                node1 = currentNode;
            } else if (null != currentNode && node2 == null) {
                node2 = currentNode;
                controlAction.getControlEdge().addEdge(node1, node2);
                initialization();
            }
        }

        if (checkAddRobot()) {
            currentNode = controlAction.getControlNode().checkCurrentNode(e.getX(), e.getY());
            if (null != currentNode) {
                controlAction.getControlRobot().addRobot(currentNode);
                initialization();
            }
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Verifie si button Node ou Fire actionne
     *
     * @return si button noeud actionne VRAI sinon FAUX
     */
    private boolean checkAddNode() {
        return controlAction.getControlNode().fire ||
                controlAction.getControlNode().node;
    }

    /**
     * Verifie si button Escarpe ou Inonde ou Plat actionner
     *
     * @return si button arc actionne VRAI sinon FAUX
     */
    private boolean checkAddEdge() {
        return controlAction.getControlEdge().escarpe ||
                controlAction.getControlEdge().inonde ||
                controlAction.getControlEdge().plat;
    }

    /**
     * Verifie si button Pate ou Chenille ou  Terrain actionner
     *
     * @return si button robot actionne VRAI sinon FAUX
     */
    private boolean checkAddRobot() {
        return controlAction.getControlRobot().leg ||
                controlAction.getControlRobot().caterpillar ||
                controlAction.getControlRobot().allTerrain;
    }


}
