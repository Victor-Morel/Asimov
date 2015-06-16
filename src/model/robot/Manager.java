package model.robot;

import model.graph.Graph;
import model.graph.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by victor on 20/05/15.
 */
public class Manager implements Runnable {

    private List<Robot> bots;
    private Graph graph;
    /**
     * Key : un noeud enflamme
     * Value : une paire robot/distance entre le noeud enflamme et le noeud du robot
     */
    public HashMap<Node, Pair<Robot, Integer>> bestBotForFire = new HashMap<>();
    private Boolean allRobotsBusy;

    public Manager() {
        this.bots = new ArrayList<>();
        this.bestBotForFire = new HashMap<>();
        this.graph = new Graph();
        this.allRobotsBusy = false;
    }

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

    @Override
    public void run() {
        decide();
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
        for (Robot r : this.bots) {
            r.generateSubGraph(graph);
        }
    }

    public List<Robot> getBots() {
        return bots;
    }
}