package controller;

import utils.FileManager;
import view.ChooseFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControllerAction implements ActionListener {

    String path;
    Boolean node, fire, plat, inonder, escarpe;

    public ControllerAction(String _path) {
        super();
        this.path = _path;

        initialization();
    }

    private void initialization() {
        node = false;
        fire = false;
        plat = false;
        inonder = false;
        escarpe = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().matches("Noeud")) {
            node = true;

        }
        if (e.getActionCommand().matches("Feu")) {
            fire = true;
        }
        if (e.getActionCommand().matches("Plat")) {
            plat = true;
        }
        if (e.getActionCommand().matches("Escarpe")) {
            inonder = true;
        }
        if (e.getActionCommand().matches("Inonde")) {
            escarpe = true;
        }
        if (e.getActionCommand().matches("Save")) {

        }
        if (e.getActionCommand().matches("Load")) {
            ChooseFile chooseFile = new ChooseFile();
            new FileManager(chooseFile.selectFile(this.path));
        }

    }

    public void addElement(int x, int y) {
        if (node) {

            //TODO create new node
            System.out.println("Create new Node");
            node = false;
        }
        if (fire) {

            fire = false;
        }
        if (plat) {

            plat = false;
        }
        if (escarpe) {

            escarpe = false;
        }
        if (inonder) {

            inonder = false;
        }
    }
}


