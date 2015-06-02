package controller;

import model.graph.Graph;
import view.GUI;

public class Controller {

    private String path;
    private ControllerAction controlAction;
    private ControllerMouse controlMouse;
    public Graph graph;


    public Controller(String _path) {
        this.path = _path;
        graph = new Graph();
        this.controlAction = new ControllerAction(path, graph);
        this.controlMouse = new ControllerMouse(controlAction);

        GUI newGui = new GUI(controlAction, controlMouse);
	}


    public void run() {

    }
}
