
package view.Dialog;

import controller.config;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;


public class ChooseFileXml extends JFileChooser {

    /**
     * Construit un ChooseFile avec un nom et un chemin
     * @param _name nom de la windows
     */
    public ChooseFileXml(String _name) {
        super(config.pathData);
        this.setDialogTitle(_name + " fichier");
    }

    /**
     * selectionner le fichier
     * @return fichier selectionner ou null si aucun fichier selectionner
     */
    public File selectFile() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
        this.setFileFilter(filter);
        int returnVal = this.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return this.getSelectedFile();
        }
        return null;
    }
}



