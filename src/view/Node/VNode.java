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
    private final int radius = 7;

    public VNode(SheetDisplay sheetDisplay, Node node) {
        this.sheetDisplay = sheetDisplay;
        this.node = node;
        this.node.addObserver(this);
        setColorBorder(0);
    }

    /**
     * Mise à jour du Noeud
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
        g2d.fillOval((int) node.getX() - radius, (int) node.getY() - radius, 2 * radius, 2 * radius);

        graph.setColor(colorBorder);
        ((Graphics2D) graph).setStroke(new BasicStroke(2));
        graph.drawOval((int) node.getX() - radius, (int) node.getY() - radius, 2 * radius, 2 * radius);
    }

    private void setColorInside() {
        if(node.getIntensity() >= Node.FIRE_DEFAULT_TEMPERATURE) {
            colorInside = Color.RED;
        }
        else if(node.getIntensity() > ( 2 * Node.FIRE_DEFAULT_TEMPERATURE / 3) ){
            colorInside = new Color(255, 121, 4);
        }
        else if(node.getIntensity() > (Node.FIRE_DEFAULT_TEMPERATURE / 3) ) {
            colorInside = new Color(255, 223, 42);
        }
        else if(node.getIntensity() > 0){
            colorInside =  new Color(255, 253, 152);
        } 
        else{
            colorInside = Color.WHITE;
        }
    }

    public void setColorBorder(int ind) {
        switch (ind) {
            case 0 :
                colorBorder = Color.BLACK;
                break;
            case 1:
                colorBorder = Color.BLUE;
                break;
            case 2:
                colorBorder = Color.GREEN;
                break;
            default : colorBorder = Color.WHITE;
        }
    }

    public Node getNode() {
        return node;
    }
}

