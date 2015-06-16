package controller;

import utils.FileManager;
import view.Dialog.ChooseFileImage;
import view.Dialog.ChooseFileXml;
import view.Dialog.JDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ControllerActionWindows implements ActionListener {

    private Controller control;

    public ControllerActionWindows(Controller control) {
        this.control = control;
    }

    /**
     * Save File
     */
    private void saveFile() {
        ChooseFileXml chooseFile = new ChooseFileXml("Sauvegarder");
        File file = chooseFile.selectFile();
        if (null != file) {
            FileManager.saveFileManager(file, control.getGraph());
        }
    }

    /**
     * Load File XML
     */
    private void loadFileXML() {
        ChooseFileXml chooseFile = new ChooseFileXml("Charger");
        File file = chooseFile.selectFile();
        if (null != file) {
            control.setGraph(FileManager.loadFileManager(file));
            control.displayGraph();
        }
    }

    /**
     * Load File JPG
     */
    private void loadFileImage() {
        ChooseFileImage chooseFile = new ChooseFileImage();
        File file = chooseFile.selectFile();
        if (null != file) {
            control.getWindow().getSheetDisplay().getImage().setImage(file);
            control.repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().matches("Sauvegarder")) {
            saveFile();
        } else if (e.getActionCommand().matches("Charger XML")) {
            if (0 == control.getGraph().getNbNodes()) {
                loadFileXML();
            } else {
                JDialog jdialog = new JDialog();
                switch (jdialog.getElementChoose()) {
                    case 0:
                        saveFile();
                        control.reset();
                        loadFileXML();
                        break;
                    case 1:
                        control.reset();
                        loadFileXML();
                        break;
                    default:
                        break;
                }
            }
        } else if (e.getActionCommand().matches("Charger Image")) {
            loadFileImage();
        } else if (e.getActionCommand().matches("Nouveau")) {
            JDialog jdialog = new JDialog();
            switch (jdialog.getElementChoose()) {
                case 0:
                    saveFile();
                    control.reset();
                    break;
                case 1:
                    control.reset();
                    break;
                default:
                    break;
            }
        } else if (e.getActionCommand().matches("Lancer Simulation")) {
            control.launchSimulation();
        } else if (e.getActionCommand().matches("Stop Simulation")){

        } else if (e.getActionCommand().matches("Mode Pyromane")){

        }else if (e.getActionCommand().matches("Strategie AStar")){
            control.getWindow().getDijkstra().setSelected(false);

        }else if (e.getActionCommand().matches("Strategie Dijkstra")){
            control.getWindow().getAStar().setSelected(false);
        }
    }

}
