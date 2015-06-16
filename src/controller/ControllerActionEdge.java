package controller;

import model.graph.Edge;
import model.graph.Node;
import model.graph.TypeEdge;
import view.Edge.AVEdge;
import view.Edge.VEdgeEscarpe;
import view.Edge.VEdgeInonde;
import view.Edge.VEdgePlat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerActionEdge implements ActionListener {

    protected boolean plat, escarpe, inonde;
    private Controller control;

    public ControllerActionEdge(Controller controller) {
        this.control = controller;
        this.initialization();
    }

    public void initialization() {
        plat = false;
        escarpe = false;
        inonde = false;
    }

    /**
     * Ajouter une arete
     *
     * @param node1 noeud 1 de la nouvelle arete
     * @param node2 noeud 2 de la nouvelle arete
     */
    public void addEdge(Node node1, Node node2) {
        Edge edge;
        AVEdge viewEdge;

        edge = null;
        viewEdge = null;

        if (plat) {
            edge = new Edge(node1, node2, TypeEdge.PLAT);
            viewEdge = new VEdgePlat(edge);
        } else if (escarpe) {
            edge = new Edge(node1, node2, TypeEdge.ESCARPE);
            viewEdge = new VEdgeEscarpe(edge);
        } else if (inonde) {
            edge = new Edge(node1, node2, TypeEdge.INONDE);
            viewEdge = new VEdgeInonde(edge);
        }

        control.getGraph().addEdge(edge);
        control.getWindow().getSheetDisplay().addEdge(viewEdge);
        control.repaint();

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
