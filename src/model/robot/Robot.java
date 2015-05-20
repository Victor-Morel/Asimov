package model.robot;

import model.graph.Graph;
import model.graph.Node;

import java.util.HashMap;

/**
 * Created by victor on 20/05/15.
 */
public abstract class Robot {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public enum TypeRecherche{
        dijkstra, astar
    }

    private TypeRecherche type;
    private int id;
    private Node node;
    private boolean busy;
    private double capacity;
    private Graph g;

    public Robot (TypeRecherche _type, double _capacity){
        this.type = _type;
        this.busy = false;
        this.capacity = _capacity;
    }

    public HashMap<Integer,Double> getNearFires (){ //doit retourner une liste avec pour chaque noeud en feu, une distance
        HashMap<Integer,Double> fireList = new HashMap<>(null);
        for (Node fn : g.getAllFireNodes()){
            //appel de strategy entre le noeud du robot, le noeud en feu, avec comme paramètre le type de recherche
        }
        return fireList;
    }

    public void extinguish (Node inFlames){
        inFlames.cooling(capacity);
    }

}
