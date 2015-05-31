package controller;

import utils.FileManager;
import view.ChooseFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControllerAction implements ActionListener {

    String path;

    public ControllerAction(String _path) {
        super();
        this.path = _path;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().matches("Noeud")) {

        }
        if (e.getActionCommand().matches("Feu")) {

        }
        if (e.getActionCommand().matches("Plat")) {

        }
        if (e.getActionCommand().matches("Escarpe")) {

        }
        if (e.getActionCommand().matches("Inonde")) {

        }
        if (e.getActionCommand().matches("Save")) {

        }
        if (e.getActionCommand().matches("Load")) {
            ChooseFile chooseFile = new ChooseFile();
            new FileManager(chooseFile.selectFile(this.path));
        }

    }
}


