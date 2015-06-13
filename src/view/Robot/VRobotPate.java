package view.Robot;

import view.SheetDisplay;

import java.awt.*;

/**
 * Created by TD on 6/13/2015.
 */
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
