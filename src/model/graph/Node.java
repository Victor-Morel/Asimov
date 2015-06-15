package model.graph;

import java.util.Observable;

public class Node extends Observable {

    public static final int FIRE_DEFAULT_TEMPERATURE = 100;

    /**
     * nombre de noeuds instanciés
     */
    private static int maxId;

    /**
     * coordonnée x d'un noeud
     */
    private double x;

    /**
     * coordonnée y d'un noeud
     */
    private double y;

    private boolean currentNode;

    /**
     * l'intensite du noeud en feu. 0 correspond a un noeud non-enflamme
     */
    private int intensity;
    /**
     * ID unique du noeud
     */
    protected int uniqueID;


    public Node(int _id, double _x, double _y, int intensity) {
        this.uniqueID = _id;
        this.x = _x;
        this.y = _y;
        this.intensity = intensity;
        if(maxId < _id) {
            maxId = _id + 1;
        }
    }

    public Node(int _id, double _x, double _y) {
        this(_id,_x,_y,0);
    }

    public Node(double _x, double _y, int intensity) {
        this.uniqueID = Node.maxId;
        this.x = _x;
        this.y = _y;
        this.intensity = intensity;
        Node.maxId++;
    }

    /**
     * Construit un noeud avec une etiquette et une intensite
     *
     * @param _x  coordonnée x du noeud
     * @param _y  coordonnée y du noeud
     */
    public Node(double _x, double _y)
    {
        this(_x, _y, 0);
    }


    /**
     * Fonction de refroidissement d'un noeud
     */
    public void cooling(double capacity) {
        this.intensity = (int) (this.intensity * (1 - capacity));
    }

    /**
     * @return l'unique ID du noeud
     */
    public int getID() {
        return uniqueID;
    }

    /**
     * @return the intensity
     */
    public int getIntensity() {
        return intensity;
    }

    /**
     * @param intensity the intensity to set
     */
    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    @Override
    public String toString() {
        return "n : " + getID();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        return uniqueID == other.uniqueID;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(boolean currentNode) {
        this.currentNode = currentNode;
        setChanged();
        notifyObservers();
    }

    public void reset() throws Throwable {
        this.finalize();
    }
}
