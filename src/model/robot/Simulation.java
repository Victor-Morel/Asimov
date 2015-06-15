package model.robot;

import model.graph.Graph;

import java.util.Observable;

/**
 * Created by victor on 15/06/15.
 */
public class Simulation extends Observable implements Runnable {

    private Manager manager;
    private Graph g;

    public Simulation(Manager _manager, Graph _graph) {
        this.manager = _manager;
        this.g = _graph;
    }

    @Override
    public void run() {
        int compteurTemps = 15000;
        int tempsPause = 1000;
        while (true) {
            try {
                Thread.sleep(0);
                compteurTemps += tempsPause;
                System.out.println("test");
                manager.decide();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
