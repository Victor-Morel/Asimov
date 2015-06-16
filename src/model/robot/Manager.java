package model.robot;

import model.graph.Graph;
import model.graph.Node;
import utils.EuclidianDistance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by victor on 20/05/15.
 */
public class Manager implements Runnable {

    private List<Robot> bots;
    private Graph graph;
    public HashMap<Node, Pair<Robot, Integer>> bestBotForFire = new HashMap<>(); //key idFireNode, value : idBot/distance
    private Boolean allRobotsBusy;

    public Manager() {
        this.bots = new ArrayList<>();
        this.bestBotForFire = new HashMap<>();
        this.graph = new Graph();
        this.allRobotsBusy = false;
    }

    /**
     * La méthode getBestDistance va regarder pour chaque noeud enflammé quel robot non occupé il serait judicieux de placer
     */
    public synchronized void getBestDistance() {
        for (Node inFlames : graph.getAllFireNodes()) {
            if (!inFlames.isSupported()) {
                for (Robot bot : bots) {
                    if (!bot.isBusy()) {
                        if (bestBotForFire.get(inFlames) != null) {
                            if (bot.getDistance(inFlames) < bestBotForFire.get(inFlames).getSecond()) {
                                Pair<Robot, Integer> best = new Pair<>(bot, bot.getDistance(inFlames));
                                bestBotForFire.put(inFlames, best);
                            }
                        } else {
                            Pair<Robot, Integer> best = new Pair<>(bot, bot.getDistance(inFlames));
                            bestBotForFire.put(inFlames, best);
                        }
                    }
                }
            }
        }
    }

    //doit retourner pour chaque robot sa distance aux noeuds en feu, ou pour chaque feu sa distance des robots
    //ça peut être pas mal de retourner pour chaque robot sa distance aux noeuds en feu (car plus simple), mais pour choisir
    //c'est peut être plus judicieux de savoir pour chaque feu quel robot est le plus proche

    public synchronized void setAction(Robot bot, Node inFlames, int distance) { //envoie le robot sur le noeud
        bot.setExtinction(inFlames, distance);
        new Thread(bot).start();
    }

    public synchronized void chooseRobot() {//décide quel robot fait quoi
        try {
            for (Node inFlames : graph.getAllFireNodes()) {
                getBestDistance();
                if ((!bestBotForFire.isEmpty()) && (!bestBotForFire.get(inFlames).getFirst().isBusy())) {
                    setAction(bestBotForFire.get(inFlames).getFirst(), inFlames, bestBotForFire.get(inFlames).getSecond());
                    bestBotForFire.get(inFlames).getFirst().setBusy(true);
                    inFlames.setSupported(true);
                    clearList(inFlames, bestBotForFire.get(inFlames).getFirst());
                }
            }
        } catch (NullPointerException e) {
           System.out.print("Caught the NullPointerException");
        }
    }

    public synchronized void clearList(Node inFlames, Robot bot) {
        bestBotForFire.remove(inFlames);
        for (Node fire : graph.getAllFireNodes()) {
            if ((bestBotForFire.get(fire) != null) && (bot == bestBotForFire.get(fire).getFirst())) {
                bestBotForFire.remove(fire);
            }
        }
    }

    public Robot bestRobot(Node inFlames) {
        int bestDistance = Integer.MAX_VALUE;
        Robot bestRobot = null;
        for(Robot bot : bots) {
            if(bot.isBusy()) {
                continue;
            }
            if(bot.getDistance(inFlames) < bestDistance) {
                bestRobot = bot;
                bestDistance = bestRobot.getDistance(inFlames);
            }
        }
        return bestRobot;
    }

    //à appeler pour que le manager gère les robots
    public void decide() {
        //chooseRobot();
        for (Node inFlames : graph.getAllFireNodes()) {
            if(inFlames.isSupported()) {
                continue;
            }
            Robot r = bestRobot(inFlames);
            if(null == r) {
                allRobotsBusy = true;
                break;
            }
            allRobotsBusy = false;
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

