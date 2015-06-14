package controller;

import model.graph.Node;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControllerMouse implements MouseListener {


    private ControllerAction controlAction;
    private Node node1, node2, currentNode;

    /**
     * Conscruit un controllerMouse
     *
     * @param _controlAction controller des actions sur la fenetre
     */
    public ControllerMouse(ControllerAction _controlAction) {
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
     * Si la souris a clické
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        if (checkAddNode()) {
            controlAction.addNode(e.getX(), e.getY());
            initialization();

        }
        if (checkAddEdge()) {
            currentNode = controlAction.checkCurrentNode(e.getX(), e.getY());
            if (null != currentNode && node1 == null) {
                node1 = currentNode;
            } else if (null != currentNode && node2 == null) {
                node2 = currentNode;
                controlAction.addEdge(node1, node2);
                initialization();
            }
        }

        if (checkAddRobot()) {
            currentNode = controlAction.checkCurrentNode(e.getX(), e.getY());
            if (null != currentNode) {
                controlAction.addRobot(currentNode);
                initialization();
            }
        }
        controlAction.checkCurrentNode(e.getX(), e.getY());
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
     * Verifié si button Node ou Fire actionné
     * @return si button noeud actionner VRAI sinon FAUX
     */
    private boolean checkAddNode() {
        return controlAction.fire || controlAction.node;
    }

    /**
     * Verifié si button Escarpe ou Inonde ou Plat actionner
     * @return si button arc actionner VRAI sinon FAUX
     */
    private boolean checkAddEdge() {
        return controlAction.escarpe || controlAction.inonde || controlAction.plat;
    }

    /**
     * Verifié si button Pate ou Chenille ou  Terrain actionner
     * @return si button robot actionner VRAI sinon FAUX
     */
    private boolean checkAddRobot() {
        return controlAction.pate || controlAction.chenille || controlAction.terrain;
    }


}
