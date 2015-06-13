package view.Robot;

import view.SheetDisplay;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class AVRobot implements Observer {


    protected final SheetDisplay sheetDisplay;
    protected Robot robot;

    public AVRobot(SheetDisplay sheetDisplay, Robot robot) {
        this.sheetDisplay = sheetDisplay;
        setT(robot);
    }


    @Override
    public void update(Observable o, Object arg) {
        sheetDisplay.repaint();
    }

    public void drawRobot(Graphics graph) {
        //   graph.drawRect(robot.get);

    }

    public void setT(Robot robot) {
        this.robot = robot;

    }


}
