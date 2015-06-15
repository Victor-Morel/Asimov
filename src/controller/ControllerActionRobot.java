package controller;

import model.graph.Node;
import model.robot.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by TD on 6/15/2015.
 */
public class ControllerActionRobot implements ActionListener {

    protected Boolean terrain, patte, chenille;
    private ControllerAction control;

    public ControllerActionRobot(ControllerAction control) {
        this.control = control;
        this.initialization();
    }

    public void initialization() {
        terrain = false;
        patte = false;
        chenille = false;
    }

    /**
     * Ajouter un robot
     *
     * @param currentNode noeud ou le robot sera instanci?
     */
    public void addRobot(Node currentNode) {
        int _capacity = 10;
        Robot bot;
        TypeRecherche type = TypeRecherche.ASTAR;
        if (terrain) {
            bot = new AllTerrainRobot(type, _capacity, control.getGraph(), currentNode);
            control.addRobot(bot);
        } else if (chenille) {
            control.addRobot(new CaterpillarRobot(type, _capacity, control.getGraph(), currentNode));
        } else if (patte) {
            control.addRobot(new LeggedRobot(type, _capacity, control.getGraph(), currentNode));
        }
        this.initialization();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().matches("Tout Terrain")) {
            this.initialization();
            terrain = true;
        } else if (e.getActionCommand().matches("Chenille")) {
            this.initialization();
            chenille = true;
        } else if (e.getActionCommand().matches("Pates")) {
            this.initialization();
            patte = true;
        }
    }
}
