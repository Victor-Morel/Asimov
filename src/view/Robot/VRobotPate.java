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
        graph.setColor(new Color(0, 136, 32));
        super.drawRobot(graph);
    }
}
