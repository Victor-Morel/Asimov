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
        int timer = 15000;
        int pauseTime = 1000;
        while (true) {
            try {
                Thread.sleep(0);
                timer += pauseTime;
               // System.out.println("test");
                manager.decide();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
