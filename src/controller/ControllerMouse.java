package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControllerMouse implements MouseListener {

    private int x, y;

    public ControllerMouse() {
        super();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
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
}
