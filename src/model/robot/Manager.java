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

    public void getPositions (){
        //doit retourner pour chaque robot sa distance aux noeuds en feu, ou pour chaque feu sa distance des robots
        //ça peut être pas mal de retourner pour chaque robot sa distance aux noeuds en feu (car plus simple), mais pour choisir
        //c'est peut être plus judicieux de savoir pour chaque feu quel robot est le plus proche
        HashMap<Integer,HashMap<Integer,Double>> listDistance = new HashMap<>(null);
        for (Robot bot : bots){
          //  HashMap<bot.getId(), bot.getNearFires()>;
        }

    }

    public void setAction(Robot bot){ //doit envoyer un ordre à un robot

    }

    public void chooseRobot(){//décide quel robot fait quoi
        for (Robot bot : bots){
            setAction(bot);
        }
    }

    public void decide(){
        getPositions();
        chooseRobot();
    }
}

