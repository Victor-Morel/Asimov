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

    public List<Robot> bots;
    private Graph graph;
    public HashMap<Integer, HashMap<Integer, Double>> listDistance = new HashMap<>();
    public HashMap<Node, Pair<Robot, Integer>> bestBotForFire = new HashMap<>(); //key idFireNode, value : idBot/distance

    public Manager() {
        this.bots = new ArrayList<>();
        this.bestBotForFire = new HashMap<>();
        this.graph = new Graph();
    }

    /**
     * La méthode getBestDistance va regarder pour chaque noeud enflammé quel robot non occupé il serait judicieux de placer
     */
    public void getBestDistance() {
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

    public void setAction(Robot bot, Node inFlames) { //envoie le robot sur le noeud
        bot.goTo(inFlames);
    }

    public void chooseRobot() {//décide quel robot fait quoi
        for (Node inFlames : graph.getAllFireNodes()) {
            getBestDistance();
            if ((!bestBotForFire.isEmpty())&&(!bestBotForFire.get(inFlames).getFirst().isBusy())) {
                setAction(bestBotForFire.get(inFlames).getFirst(), inFlames);
                bestBotForFire.get(inFlames).getFirst().setBusy(true);
                inFlames.setSupported(true);
                clearList(inFlames, bestBotForFire.get(inFlames).getFirst());
            }
        }
    }

    public void clearList(Node inFlames, Robot bot){
        bestBotForFire.remove(inFlames);
        for (Node fire : graph.getAllFireNodes()){
            if ((bestBotForFire.get(fire) != null)&&(bot==bestBotForFire.get(fire).getFirst())){
                bestBotForFire.remove(fire);
            }
        }
    }


    //à appeler pour que le manager gère les robots
    public void decide() {
        chooseRobot();
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
    }
}

