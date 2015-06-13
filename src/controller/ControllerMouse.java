package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControllerMouse implements MouseListener {

    private int x, y;
    private boolean stat;

    private ControllerAction controlAction;

    public ControllerMouse(ControllerAction _controlAction) {
        super();

        this.controlAction = _controlAction;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (check()) {
            controlAction.addElement(e.getX(), e.getY());
            stat = false;
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


    public boolean check() {
        return controlAction.escarpe || controlAction.inonder || controlAction.plat ||
                controlAction.fire || controlAction.node;
    }
}
