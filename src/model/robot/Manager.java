package model.robot;

import model.graph.Graph;
import model.graph.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 20/05/15.
 */
public class Manager implements Runnable {

    /**
     * liste des robots geres par le manageur
     */
    private List<Robot> bots;
    /**
     * le graphe sur lequel le manageur travaille
     */
    private Graph graph;

    /**
     * Constructeur par defaut d'un Manager
     */
    public Manager() {
        this.bots = new ArrayList<>();
        this.graph = new Graph();
    }

    /**
     * Méthode qui retourne le noeud en feu le plus proche du robot passe en parametre
     * @param r le robot dont on cherche le noeud en feu le plus proche
     * @return le noeud en feu le plus proche du robot passe en parametre
     */
    public Node bestFire(Robot r) {
        int bestDistance = Integer.MAX_VALUE;
        Node bestFire = null;
        for (Node inFlames : graph.getAllFireNodes()) {
            if (inFlames.isSupported()) {
                continue;
            }
            if (r.getDistance(inFlames) < bestDistance) {
                bestFire = inFlames;
                bestDistance = r.getDistance(bestFire);
            }
        }
        return bestFire;
    }

    /**
     * Affecte a chaque robot oisif un feu a traiter
     */
    public void decide() {
        for (Robot r : bots) {
            if (r.isBusy()) {
                continue;
            }
            Node inFlames = bestFire(r);
            if (null == inFlames) {
                break;
            }
            r.setExtinction(inFlames, r.getDistance(inFlames));
            new Thread(r).start();
            r.setBusy(true);
            inFlames.setSupported(true);
        }
    }

    /**
     * Methode pour lancer le manageur
     */
    @Override
    public void run() {
        decide();
    }

    /**
     * @return le graphe sur lequel le manageur travaille
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * Set un nouveau graphe sur lequel le manageur doit travailler
     * @param graph le nouveau graphe de travaille
     */
    public void setGraph(Graph graph) {
        this.graph = graph;
        for (Robot r : this.bots) {
            r.generateSubGraph(graph);
        }
    }

    /**
     * @return la liste des robots geres par le manageur
     */
    public List<Robot> getBots() {
        return bots;
    }
}