package view.Dialog;

import javax.swing.*;

public class JDialog extends JOptionPane {

    int n;

    public JDialog() {
        super();


        Object[] options = {"Oui", "Non", "Annuler"};
        n = JOptionPane.showOptionDialog(null,
                "Voulez-vous enregistrer les modifications que vous avez apportees",
                "",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    public int getN() {
        return n;
    }
}
