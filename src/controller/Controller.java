package controller;

import view.GUI;

public class Controller {

    private String path;
    private ControllerAction controlAction;
    private ControllerMouse controlMouse;


    public Controller(String _path) {
        this.path = _path;
        this.controlAction = new ControllerAction(path);
        this.controlMouse = new ControllerMouse(controlAction);


        GUI newGui = new GUI(controlAction, controlMouse);

	}
}
