package view.Robot;


import model.robot.Robot;
import view.SheetDisplay;

import java.awt.*;


public class VRobotChenille extends AVRobot {

    public VRobotChenille(SheetDisplay sheetDisplay, Robot r) {
        super(sheetDisplay, r);
    }

    @Override
    public void drawRobot(Graphics graph) {
        graph.setColor(new Color(0, 29,255));
        super.drawRobot(graph);
    }
}
