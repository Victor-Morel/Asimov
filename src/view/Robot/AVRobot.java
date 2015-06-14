package view.Robot;

import model.robot.Robot;
import view.SheetDisplay;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;


public class AVRobot implements Observer {


    protected final SheetDisplay sheetDisplay;

    /**
     * Robot correspondant a la vue
     */
    protected Robot robot;

    /**
     * Conscruit un AVRobot
     * @param sheetDisplay sheetDisplay JPanel
     * @param robot Robot de la vue
     */
    public AVRobot(SheetDisplay sheetDisplay, Robot robot) {
        this.sheetDisplay = sheetDisplay;
        setT(robot);
    }


    /**
     * Mise a jour du robot
     * @param o observable
     * @param arg arg
     */
    @Override
    public void update(Observable o, Object arg) {
        sheetDisplay.repaint();
    }

    /**
     * Dessine un robot
     * @param graph
     */
    public void drawRobot(Graphics graph) {
        graph.fillRect((int) robot.getNode().getX(), (int) robot.getNode().getY(), 10, 10);

    }

    public void setT(Robot robot) {
        this.robot = robot;

    }


}
