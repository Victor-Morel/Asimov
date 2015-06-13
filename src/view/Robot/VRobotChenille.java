package view.Robot;

import view.SheetDisplay;

import java.awt.*;

/**
 * Created by TD on 6/13/2015.
 */
public class VRobotChenille extends AVRobot {

    public VRobotChenille(SheetDisplay sheetDisplay, Robot r) {
        super(sheetDisplay, r);
    }

    @Override
    public void drawRobot(Graphics graph) {
        graph.setColor(Color.RED);
        super.drawRobot(graph);
    }
}
