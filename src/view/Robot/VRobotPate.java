package view.Robot;

import model.robot.Robot;
import view.SheetDisplay;

import java.awt.*;


public class VRobotPate extends AVRobot {

    public VRobotPate(SheetDisplay sheetDisplay, Robot r) {
        super(sheetDisplay, r);
    }

    @Override
    public void drawRobot(Graphics graph) {
        graph.setColor(Color.GREEN);
        super.drawRobot(graph);
    }
}
