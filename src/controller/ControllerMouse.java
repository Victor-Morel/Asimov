package controller;

import model.graph.Node;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControllerMouse implements MouseListener {



    private ControllerAction controlAction;

    public ControllerMouse(ControllerAction _controlAction) {
        super();
        this.controlAction = _controlAction;

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (addNode()) {
            controlAction.addNode(e.getX(), e.getY());

        }
        if (addEdge()) {
            Node n;
            n = controlAction.selectCurrentNode(e.getX(), e.getY());
            controlAction.addEdge(n);
        }

        if (addRobot()) {
            Node n;
            n = controlAction.selectCurrentNode(e.getX(), e.getY());
            controlAction.addRobot(n);
        }
        controlAction.selectCurrentNode(e.getX(), e.getY());
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


    public boolean addNode() {
        return controlAction.fire || controlAction.node;
    }

    public boolean addEdge() {
        return controlAction.escarpe || controlAction.inonde || controlAction.plat;
    }

    private boolean addRobot() {
        return controlAction.pate || controlAction.chenille || controlAction.terrain;
    }


}
