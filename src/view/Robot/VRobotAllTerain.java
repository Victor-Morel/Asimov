package view.Robot;

import view.SheetDisplay;

import java.awt.*;

/**
 * Created by TD on 6/13/2015.
 */
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
