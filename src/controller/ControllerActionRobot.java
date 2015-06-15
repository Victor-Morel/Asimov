package controller;

import model.graph.Node;
import model.robot.*;
import view.Robot.AVRobot;
import view.Robot.VRobotAllTerain;
import view.Robot.VRobotChenille;
import view.Robot.VRobotPate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerActionRobot implements ActionListener {

    protected Boolean terrain, patte, chenille;
    private Controller control;

    public ControllerActionRobot(Controller control) {
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
        ResearchType type = ResearchType.ASTAR;
        AVRobot viewRobot;

        viewRobot = null;
        bot = null;
        if (terrain) {
            bot = new AllTerrainRobot( _capacity, currentNode);
            viewRobot = new VRobotAllTerain(control.getWindow().getSheetDisplay(), bot);
        } else if (chenille) {
            bot = new CaterpillarRobot(_capacity, currentNode);
            viewRobot = new VRobotChenille(control.getWindow().getSheetDisplay(), bot);
        } else if (patte) {
            bot = new LeggedRobot( _capacity, currentNode);
            viewRobot = new VRobotPate(control.getWindow().getSheetDisplay(), bot);
        }

        control.getManager().bots.add(bot);
        control.getWindow().getSheetDisplay().addRobot(viewRobot);
        control.repaint();

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
        } else if (e.getActionCommand().matches("Pattes")) {
            this.initialization();
            patte = true;
        }
    }
}
