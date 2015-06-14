package view.Dialog;

import javax.swing.*;

public class JDialog extends JOptionPane {

    /**
     * entier correspondant au choix Oui : 0 | Non : 1 | Annuler : 2
     *
     */
    private int elementChoose;

    /**
     * construit un JDialog
     */
    public JDialog() {
        super();
        Object[] options = {"Oui", "Non", "Annuler"};
        elementChoose = JOptionPane.showOptionDialog(null,
                "Voulez-vous enregistrer les modifications que vous avez apportees",
                "",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    /**
     * Acceder a la variable elementChoose
     * @return element choose
     */
    public int getElementChoose() {
        return elementChoose;
    }
}
