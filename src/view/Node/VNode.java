package view.Node;

import model.graph.Node;
import view.SheetDisplay;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;


public class VNode implements Observer {

    protected final SheetDisplay sheetDisplay;

    /**
     * Noeud correspondant a la vue
     */
    protected Node node;
    Graphics2D g2d;
    Color colorInside;
    Color colorBorder;


    /**
     * Rayon du cercle
     */
    private final int rayon = 7;

    public VNode(SheetDisplay sheetDisplay, Node node) {
        this.sheetDisplay = sheetDisplay;
        this.node = node;
        this.node.addObserver(this);
    }

    /**
     * Mise à jour du Noeud
     *
     * @param arg0
     * @param arg1
     */
    @Override
    public void update(Observable arg0, Object arg1) {
        sheetDisplay.repaint();
    }

    /**
     * Dessine le Noeud
     *
     * @param graph
     */
    public void drawNode(Graphics graph) {
        setColorInside();
        graph.setColor(colorInside);
        g2d = (Graphics2D) graph;
        g2d.fillOval((int) node.getX() - rayon, (int) node.getY() - rayon, 2 * rayon, 2 * rayon);

        setColorBorder();
        graph.setColor(colorBorder);
        ((Graphics2D) graph).setStroke(new BasicStroke(2));
        graph.drawOval((int) node.getX() - rayon, (int) node.getY() - rayon, 2 * rayon, 2 * rayon);
    }

    private void setColorInside() {
        if(node.getIntensity()==Node.FIRE_DEFAULT_TEMPERATURE) {
            colorInside = Color.RED;
        }
        else if(node.getIntensity() > Node.FIRE_DEFAULT_TEMPERATURE / 2){
            colorInside = Color.ORANGE;
        }
        else if(node.getIntensity() > 0){
            colorInside = Color.YELLOW;
        }
        else{
            colorInside= Color.WHITE;
        }
    }

    public void setColorBorder() {
        switch(node.getTypeBorder()){
            case NORMAL :
                colorBorder = Color.BLACK;
                break;
            case CURRENT:
                colorBorder = Color.BLUE;
                break;
            case CURRENT_ADD_ARC:
                colorBorder = Color.GREEN;
                break;

        }
    }
}

