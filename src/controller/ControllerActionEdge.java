package controller;

import model.graph.Edge;
import model.graph.Node;
import model.graph.TypeEdge;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerActionEdge implements ActionListener {

    protected boolean plat, escarpe, inonde;
    private ControllerAction control;

    public ControllerActionEdge(ControllerAction controller) {
        this.control = controller;
        this.initialization();
    }

    public void initialization() {
        plat = false;
        escarpe = false;
        inonde = false;

    }

    /**
     * Ajouter un arc
     *
     * @param node1 noeud 1 du nouveau arc
     * @param node2 noeud 2 du nouveau arc
     */
    public void addEdge(Node node1, Node node2) {
        double valuation = 0;
        if (plat) {
            control.addEdge(new Edge(node1, node2, valuation, TypeEdge.PLAT));
        } else if (escarpe) {
            control.addEdge(new Edge(node1, node2, valuation, TypeEdge.ESCARPE));
        } else if (inonde) {
            control.addEdge(new Edge(node1, node2, valuation, TypeEdge.INONDE));
        }
        this.initialization();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().matches("Plat")) {
            this.initialization();
            plat = true;
        } else if (e.getActionCommand().matches("Escarpe")) {
            this.initialization();
            escarpe = true;
        } else if (e.getActionCommand().matches("Inonde")) {
            this.initialization();
            inonde = true;
        }
    }
}
