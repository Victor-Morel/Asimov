package view.Node;

import model.graph.Node;
import view.SheetDisplay;

import java.awt.*;

public class VNode extends AVNode {


    protected static final int rp = 10, rb = 5;

    protected Polygon arrow;
    protected Point point;

    public VNode(SheetDisplay sheetDisplay, Node n) {
        super(sheetDisplay, n);
    }

    @Override
    public boolean contains(int x, int y) {
        if (arrow == null)
            return false;
        return arrow.contains(x, y);
    }

    @Override
    public void drawNode(Graphics graph) {

        double alpha, r;
        Point p2;

        if (graph == null) return;

        point = new Point((int) node.getX(), (int) node.getY());
        arrow = new Polygon();

        alpha = Math.atan((float) rb / (float) rp);
        r = Math.sqrt(rp * rp + rb * rb);


        p2 = new Point((int) Math.round(point.x + r * Math.cos(0.5)),
                (int) Math.round(point.y - r * Math.sin(0.5))
        );

        arrow.addPoint(p2.x, p2.y);
        arrow.addPoint((int) Math.round(p2.x - r * Math.cos(0.5 + alpha)),
                (int) Math.round(p2.y + r * Math.sin(0.5 + alpha))
        );
        arrow.addPoint((int) Math.round(p2.x - r * Math.cos(0.5 - alpha)),
                (int) Math.round(p2.y + r * Math.sin(0.5 - alpha))
        );
        arrow.addPoint(p2.x, p2.y);

        graph.setColor(Color.WHITE);
        graph.fillPolygon(arrow);

    }
}
