package view.Dialog;

import run.config;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * Created by TD on 6/15/2015.
 */
public class ChooseFileImage extends JFileChooser {

    /**
     * Construit un ChooseFileImage
     */
    public ChooseFileImage() {
        super(config.pathData);
        this.setDialogTitle("Charger une image");
    }

    /**
     * Selectionner le fichier
     *
     * @return fichier selectionne ou null si aucun fichier selectionne
     */
    public File selectFile() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier Image", "jpg");
        this.setFileFilter(filter);
        int returnVal = this.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return this.getSelectedFile();
        }
        return null;
    }
}

