package view.Robot;

import model.robot.Robot;
import view.SheetDisplay;

import java.awt.*;


public class VRobotAllTerain extends AVRobot {

    public VRobotAllTerain(SheetDisplay sheetDisplay, Robot r) {
        super(sheetDisplay, r);
    }

    @Override
    public void drawRobot(Graphics graph) {
        graph.setColor(Color.BLACK);
        super.drawRobot(graph);
    }
}
