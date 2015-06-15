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
    public HashMap<Integer, Pair<Robot, Integer>> bestBotForFire = new HashMap<>(); //key idFireNode, value : idBot/distance

    public Manager() {
        this.bots = new ArrayList<>();
        this.bestBotForFire = new HashMap<>();
        this.graph = new Graph();
    }

    public void getBestDistance() {
        for (Node inFlames : graph.getAllFireNodes()) {
            for (Robot bot : bots) {
                if (!bot.isBusy()) {
                    if (bestBotForFire.get(inFlames.getID()) != null) {
                        if (bot.getDistance(inFlames) < bestBotForFire.get(inFlames.getID()).getSecond()) {
                            Pair<Robot, Integer> best = new Pair<>(bot, bot.getDistance(inFlames));
                            bestBotForFire.put(inFlames.getID(), best);
                        }
                    } else {
                        Pair<Robot, Integer> best = new Pair<>(bot, bot.getDistance(inFlames));
                        bestBotForFire.put(inFlames.getID(), best);
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
            getBestDistance(); //pas optimal du tout pour l'instant, comme la méthode ne prends en compte que le meilleur robot, ça loupe des noeufs enflammés
            if(!bestBotForFire.get(inFlames.getID()).getFirst().isBusy()){
                setAction(bestBotForFire.get(inFlames.getID()).getFirst(), inFlames);
                bestBotForFire.get(inFlames.getID()).getFirst().setBusy(true);
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

