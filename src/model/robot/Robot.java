package model.robot;

import model.graph.Graph;
import model.graph.Node;

import java.util.HashMap;

/**
 * Created by victor on 20/05/15.
 */
public abstract class Robot {
    public enum TypeRecherche{
        dijkstra, astar
    }

    private TypeRecherche type;
    private Node node;
    private boolean busy;
    private int capacity;
    private Graph g;

    public Robot (TypeRecherche _type, int _capacity){
        this.type = _type;
        this.busy = false;
        this.capacity = _capacity;
    }

    public void getNearFires (){ //doit retourner une liste avec pour chaque noeud en feu, une distance

    }


}
