package model.robot;

import model.graph.Graph;

import java.util.Observable;

/**
 * Classe qui lance une simulation d'extinction d'incendie par des robots pompiers
 */
public class Simulation extends Observable implements Runnable {

    private Manager manager;
    private Graph g;
    public Boolean pyromanMode;

    public Simulation(Manager _manager, Graph _graph, Boolean _pyromanMode) {
        this.manager = _manager;
        this.g = _graph;
        this.pyromanMode = _pyromanMode;
    }

    @Override
    public void run() {
        while (true) {
            if(pyromanMode) {
                g.igniteNodes();
            }
            manager.decide();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void sleep() throws InterruptedException {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
