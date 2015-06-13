
package view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class ChooseFile extends JFileChooser {

    public ChooseFile() {
        super();
    }

    public File selectFile(String path) {
        JFileChooser chooser = new JFileChooser(path);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        return null;
    }
}



