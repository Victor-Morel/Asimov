package model.graph;

import java.util.Observable;

public class Node extends Observable {


    /**
     * Constante correspondant à la température par défaut d'un noeud en feu
     */
    public static final int FIRE_DEFAULT_TEMPERATURE = 100;

    /**
     * Constante correspondant à la probabilité qu'un noeud normal s'enflamme de lui-même
     */
    public static final double IGNITE_PROBABILITY = 0.0001;

    /**
     * plus grand id parmi tous les ids des noeuds instanciés
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

    /**
     * l'intensite du noeud en feu. 0 correspond a un noeud non-enflammé
     */
    private int intensity;
    /**
     * ID unique du noeud
     */
    protected int uniqueID;

    private boolean supported;


    /**
     * Construit un noeud enflammé avec un id donné
     *
     * @param _id        id du noeud
     * @param _x         coordonnée x du noeud
     * @param _y         coordonnée y du noeud
     * @param _intensity intensité du noeud enflammé
     */
    public Node(int _id, double _x, double _y, int _intensity) {
        this.uniqueID = _id;
        this.x = _x;
        this.y = _y;
        this.intensity = _intensity;
        if (maxId < _id) {
            maxId = _id + 1;
        }
        this.supported = false;
    }

    /**
     * Construit un noeud normal avec un id donné
     *
     * @param _id id du noeud
     * @param _x  coordonnée x du noeud
     * @param _y  coordonnée y du noeud
     */
    public Node(int _id, double _x, double _y) {
        this(_id, _x, _y, 0);
    }

    /**
     * Construit un noeud enflammé
     *
     * @param _x         coordonnée x du noeud
     * @param _y         coordonnée y du noeud
     * @param _intensity intensité du noeud enflammé
     */
    public Node(double _x, double _y, int _intensity) {
        this.uniqueID = Node.maxId;
        this.x = _x;
        this.y = _y;
        this.intensity = _intensity;
        Node.maxId++;
    }

    /**
     * Construit un noeud normal
     *
     * @param _x coordonnée x du noeud
     * @param _y coordonnée y du noeud
     */
    public Node(double _x, double _y) {
        this(_x, _y, 0);
    }


    /**
     * Fonction de refroidissement d'un noeud
     *
     * @param capacity correspond à l'efficacité de refroidissement [0;1]
     */
    public void cooling(double capacity) {
        setIntensity((int) (this.intensity * (1 - capacity)));
    }

    /**
     * @return l'unique ID du noeud
     */
    public int getID() {
        return uniqueID;
    }

    /**
     * @return l'intensité d'un noeud
     */
    public int getIntensity() {
        return intensity;
    }

    /**
     * @param intensity intensité du feu à setter
     */
    public void setIntensity(int intensity) {
        this.intensity = intensity;
        setChanged();
        notifyObservers();
    }

    /**
     * @return un String correspond au noeud
     */
    @Override
    public String toString() {
        return "n : " + getID();
    }


    /**
     * @param obj le noeud a comparer à this
     * @return true si les noeuds sont égaux, false sinon
     */
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

    /**
     * Destructeur de noeud
     *
     * @throws Throwable
     */
    public void reset() throws Throwable {
        this.finalize();
    }

    /**
     * Indique si le noeud est pris en charge
     */
    public boolean isSupported() {
        return supported;
    }

    public void setSupported(boolean supported) {
        this.supported = supported;
    }
}
