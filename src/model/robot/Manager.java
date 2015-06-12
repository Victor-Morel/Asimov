package model.robot;

import model.graph.Graph;
import model.graph.Node;

import java.util.HashMap;
import java.util.List;

/**
 * Created by victor on 20/05/15.
 */
public class Manager {

    private List<Robot> bots;
    private Graph g;
    public HashMap<Integer, HashMap<Integer, Double>> listDistance = new HashMap<>(null);
    public HashMap<Integer, Pair<Robot, Integer>> bestBotForFire = new HashMap<>(null); //key idFireNode, value : idBot/distance

    public void getBestDistance() {
        for (Node inFlames : g.getAllFireNodes()) {
            for (Robot bot : bots) {
                if (!bot.isBusy()) {
                    if ((bot.getDistance(inFlames) < bestBotForFire.get(inFlames.getID()).getSecond())
                            || ((bestBotForFire.get(inFlames.getID()).getSecond()) == null)) {
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
        for (Node inFlames : g.getAllFireNodes()) {
            setAction(bestBotForFire.get(inFlames.getID()).getFirst(), inFlames);
        }
    }


    //à appeler pour que le manager gère les robots
    public void decide() {
        getBestDistance();
        chooseRobot();
    }
}

