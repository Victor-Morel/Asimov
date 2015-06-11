package testing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Hashtable;

/**
 * SettingsDialog.java
 */
public class SettingsDialog extends JDialog
{
  Settings s = new Settings();
  JTabbedPane tabbedPane = new JTabbedPane();
  JTextField destField = new JTextField();
  JPanel destPanel = new JPanel();
  JPanel infoPanel = new JPanel();
  JTextField infoAuthorField = new JTextField();
  JTextField infoCompanyField = new JTextField();
  JTextField infoEmailField = new JTextField();
  JLabel infoAuthorLabel = new JLabel();
  JLabel infoEmailLabel = new JLabel();
  JLabel infoCompanyLabel = new JLabel();
  JPanel ApplyPanel = new JPanel();
  JButton applyButton = new JButton();
  JButton cancelButton = new JButton();
  BorderLayout borderLayout1 = new BorderLayout();
  JLabel destLabel = new JLabel();
  GridBagLayout gridBagLayout1 = new GridBagLayout();

  String author = "<Your Name>";
  String email = "<e-mail>";
  String company = "<company>";
  String destination = "default";

  public SettingsDialog(JFrame parent) {
    super(parent, "Test Settings", true);
    try {
      jbInit();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception
  {
    setSize( new Dimension(300, 200) );
    Container cp = getContentPane();
    cp.setLayout(borderLayout1 );
    applyButton.setMnemonic('A');
    applyButton.setText("Apply");
    cancelButton.setMnemonic('0');
    cancelButton.setText("Cancel");
    destLabel.setText("Type in destination path:");
    cp.add(tabbedPane, BorderLayout.CENTER);
    cp.add(ApplyPanel, BorderLayout.SOUTH);
    initDestinationTab();
    tabbedPane.add("Destination", destPanel);
    initInfoTab();
    tabbedPane.add("Author Information", infoPanel);
    initFields();
    tabbedPane.setVisible(true);
    ApplyPanel.add(applyButton, null);
    ApplyPanel.add(cancelButton, null);
    SettingsListener setter = new SettingsListener();
    applyButton.addActionListener( setter );
    cancelButton.addActionListener( setter );
    addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          SettingsDialog.this.hide();
          initFields();
        }
      }
    );
  }

  protected void initDestinationTab() {
    destField.setColumns(15);
    destPanel.add(destLabel, null);
    destPanel.add(destField, null);
  }

  protected void initInfoTab() {
    Insets defaultInsets = new Insets(0, 0, 0, 0);
    Insets top5Insets = new Insets(5, 0, 0, 0);

    infoAuthorLabel.setHorizontalAlignment(SwingConstants.CENTER);
    infoAuthorLabel.setHorizontalTextPosition(SwingConstants.CENTER);

    infoEmailLabel.setHorizontalAlignment(SwingConstants.CENTER);
    infoEmailLabel.setHorizontalTextPosition(SwingConstants.CENTER);

    infoCompanyLabel.setHorizontalAlignment(SwingConstants.CENTER);
    infoCompanyLabel.setHorizontalTextPosition(SwingConstants.CENTER);

    infoCompanyField.setHorizontalAlignment(SwingConstants.LEFT);
    infoEmailField.setHorizontalAlignment(SwingConstants.LEFT);
    infoAuthorField.setHorizontalAlignment(SwingConstants.LEFT);

    infoAuthorField.setColumns(15);
    infoCompanyField.setColumns(20);
    infoEmailField.setColumns(15);
    infoPanel.setLayout(gridBagLayout1);
    infoAuthorLabel.setText("Author\'s Name:");
    infoEmailLabel.setText("E-mail:");
    infoCompanyLabel.setText("Company:");

    infoPanel.add(infoAuthorLabel, new GridBagConstraints(0, 0, 1, 1, 0.1, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, defaultInsets, 0, 0));
    infoPanel.add(infoAuthorField, new GridBagConstraints(1, 0, 1, 1, .9, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, top5Insets, 0, 0));
    infoPanel.add(infoEmailLabel, new GridBagConstraints(0, 1, 1, 1, .1, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, defaultInsets, 0, 10));
    infoPanel.add(infoEmailField, new GridBagConstraints(1, 1, 1, 1, .9, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, top5Insets, 0, 0));
    infoPanel.add(infoCompanyLabel, new GridBagConstraints(0, 2, 1, 1,.1, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, defaultInsets, 0, 0));
    infoPanel.add(infoCompanyField, new GridBagConstraints(1, 2, 1, 1, .9, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, top5Insets, 0, 0));
  }

  private void initFields() {
    try{
    s.readSettings();
    }catch(java.io.IOException e){ }

    Hashtable h = s.getSettingsAsHashtable();

    setAuthor((String)h.get("author"));
    setCompany((String)h.get("company"));
    setEmail((String)h.get("email"));
    setDestination((String)h.get("directory"));

    infoAuthorField.setText(author);
    infoCompanyField.setText(company);
    infoEmailField.setText(email);
    destField.setText(destination);
  }

  private void setAuthor(String anAuthor) {
    this.author = anAuthor;
  }

  public String getAuthor() {
    return author;
  }

  private void setEmail(String anEmail) {
    this.email = anEmail;
  }

  public String getEmail() {
    return email;
  }

  private void setCompany(String aCompany) {
    this.company = aCompany;
  }

  public String getCompany() {
    return company;
  }

  private void setDestination(String aDestination) {
    this.destination = aDestination;
  }

  public String getDestination() {
    return destination;
  }

  class SettingsListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      Object source = e.getSource();
      if (source == applyButton) {
        setAuthor(infoAuthorField.getText());
        setEmail(infoEmailField.getText());
        setCompany(infoCompanyField.getText());
        setDestination(destField.getText());
        s.setSettings(getAuthor(), getCompany(), getEmail(), getDestination());
        try{
        s.writeSettings();
        }catch(java.io.IOException ex) { }
        SettingsDialog.this.hide();
      }
      else if (source == cancelButton) {
        SettingsDialog.this.hide();
        initFields();
      }
    }
  }

}