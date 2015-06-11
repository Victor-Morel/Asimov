package testing;

import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
  * Copyright 2000 Webmind Inc.
  * GeneratorGUI: <p>
  *
  * This is the GUI Frame for the TestGenerator program.
  *
  * @author Jason Torres (jtorres@webmind.com)
  * @version 1.2
  */
public class GeneratorGUI extends JFrame implements Runnable {

  Thread selfThread = null;
  JPanel contentPane;
  JScrollPane jScrollPane = new JScrollPane();

  BorderLayout borderLayout1 = new BorderLayout();

  //for menu
  JMenuBar jMenuBar = new JMenuBar();
  JMenu jTestMenu = new JMenu();
  JMenuItem jFileMenuItem = new JMenuItem();
  JMenu jOptionMenu = new JMenu();
  JMenuItem jSettingsMenuItem = new JMenuItem();
  JMenu jHelpMenu = new JMenu();
  JMenuItem jAboutMenuItem = new JMenuItem();
  JMenuItem jExitMenuItem = new JMenuItem();

  JProgressBar jProgressBar1 = new JProgressBar();
  JFileChooser jFileChooser = new JFileChooser("c:\\");
  JTextArea jDisplayTextArea = new JTextArea();

  //for about dialog
  JDialog about = new JDialog(GeneratorGUI.this, "About Dialog", true);
  JButton jAboutOKButton = new JButton();
  JLabel AboutPicture = new JLabel();

  //for settings new
  SettingsDialog settings = new SettingsDialog(this);

  //for toolbar
  JToolBar jToolBar1 = new JToolBar();
  JButton tButtonStop = new JButton();
  JButton tButtonSettings = new JButton();
  JButton tButtonTest = new JButton();
  JButton jButtonDummy2 = new JButton();

  JButton jButtonTExit = new JButton();

  //for questions dialog
  JDialog questions = new JDialog(GeneratorGUI.this, "", true);
  JButton jButtonYes = new JButton();
  JLabel jLabelQuestion = new JLabel();
  JButton jButtonYesAll = new JButton();
  JButton jButtonNo = new JButton();
  JButton jButtonNoAll = new JButton();
  JLabel jLabelFileName = new JLabel();

  boolean threadStarted = false;
  private int questionID = 0;

  /**
    * Construct the Generator GUI frame
    */
  public GeneratorGUI() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  /**
    * Component initialization
    */
  private void jbInit() throws Exception  {
    about.setResizable(false);
    ImageIcon image = new ImageIcon("icon.gif");

    setIconImage( image.getImage() );

    jHelpMenu.setMnemonic('H');
    jOptionMenu.setMnemonic('O');
    jTestMenu.setMnemonic('T');

    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(borderLayout1);
    contentPane.setBackground(SystemColor.desktop);
    this.getContentPane().setBackground(UIManager.getColor("Desktop.background"));
    this.setDefaultCloseOperation(3);
    this.setJMenuBar(jMenuBar);
    this.setResizable(false);
    this.setSize(new Dimension(400, 300));
    this.setTitle("Test Generator");
    jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    jFileChooser.setBackground(UIManager.getColor("Desktop.background"));
    jFileChooser.setFileFilter(new ExtFilter());

    constructMenu();//constructs the menu
    constructAbout();//constructs about dialog
    constructToolBar();//constructs the toolbar
    constructQuestionDialog();//constructs the questions dialog

    //adding the scroll pane and text area
    jProgressBar1.setFont(new java.awt.Font("Dialog", 0, 10));
    jProgressBar1.setForeground(UIManager.getColor("Desktop.background"));
    jProgressBar1.setMinimumSize(new Dimension(10, 15));
    jProgressBar1.setPreferredSize(new Dimension(150, 20));
    jScrollPane.setBorder(null);

    jDisplayTextArea.setEditable(false);

    jLabelFileName.setBounds(new Rectangle(104, 34, 146, 21));
    contentPane.add(jScrollPane, BorderLayout.CENTER);
    contentPane.add(jProgressBar1, BorderLayout.SOUTH);
    contentPane.add(jToolBar1, BorderLayout.NORTH);

    jScrollPane.getViewport().add(jDisplayTextArea, null);

    //adding menu objects
    jMenuBar.add(jTestMenu);
    jMenuBar.add(jOptionMenu);
    jMenuBar.add(jHelpMenu);
    jTestMenu.add(jFileMenuItem);
    jTestMenu.add(jExitMenuItem);
    jOptionMenu.add(jSettingsMenuItem);
    jHelpMenu.add(jAboutMenuItem);

    jProgressBar1.setStringPainted(true);
  }

  /**
   * Constructs the menu's settings, also adds action listeners for each event
   */
  private void constructMenu() {
    //test menu
    jTestMenu.setText("Test");
    jFileMenuItem.setMnemonic('F');
    jFileMenuItem.setText("File/Dir");
    jFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jFileMenuItem_actionPerformed(e);
        GeneratorGUI.this.repaint();
      }
    });

    //exit menuItem in the test menu
    jExitMenuItem.setMnemonic('E');
    jExitMenuItem.setText("Exit");
    jExitMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jExitMenuItem_actionPerformed(e);
        GeneratorGUI.this.repaint();
      }
    });

    //options menu
    jOptionMenu.setText("Options");
    jSettingsMenuItem.setMnemonic('S');
    jSettingsMenuItem.setText("Settings");
    jSettingsMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jSettingsMenuItem_actionPerformed(e);
        GeneratorGUI.this.repaint();
      }
    });

    //help menu
    jHelpMenu.setText("Help");
    jAboutMenuItem.setMnemonic('A');
    jAboutMenuItem.setText("About");
    jAboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jAboutMenuItem_actionPerformed(e);
        GeneratorGUI.this.repaint();
      }
    });
  }

  /**
   * Constructs the about dialog, also adds action listeners for each event
   */
  private void constructAbout() {

    about.getContentPane().setBackground(UIManager.getColor("Desktop.background"));
    ImageIcon image2 = new ImageIcon("about.gif");
    about.getContentPane().setLayout(null);
    about.getRootPane().setDefaultButton(jAboutOKButton);
    about.setSize(new Dimension(355, 470));

    jAboutOKButton.setBackground(Color.gray);
    jAboutOKButton.setToolTipText("Click OK");
    jAboutOKButton.setMnemonic('O');
    jAboutOKButton.setText("OK");
    jAboutOKButton.setBounds(new Rectangle(128, 415, 99, 27));

    jAboutOKButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jAboutOKButton_actionPerformed(e);
        GeneratorGUI.this.repaint();
      }
    });

    AboutPicture.setIcon(image2);
    AboutPicture.setBounds(new Rectangle(24, 8, 306, 409));
    AboutPicture.setSize(300, 400);

    about.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        about_keyPressed(e);
        GeneratorGUI.this.repaint();
      }
    });
    about.getContentPane().add(AboutPicture, null);
    about.getContentPane().add(jAboutOKButton, null);

  }

  /**
   * Constructs the Questions dialog, also adds action listeners for each event
   */
  private void constructQuestionDialog() {

    questions.setDefaultCloseOperation(0);
    questions.setResizable(false);
    questions.getContentPane().setBackground(UIManager.getColor("Desktop.background"));
    questions.getContentPane().setLayout(null);
    jButtonYes.setText("Yes");
    jButtonYes.setBounds(new Rectangle(13, 65, 79, 27));
    jButtonYes.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonYes_actionPerformed(e);
        GeneratorGUI.this.repaint();
      }
    });
    jLabelQuestion.setBackground(Color.black);
    jLabelQuestion.setBounds(new Rectangle(40, 7, 274, 25));
    jButtonYesAll.setText("Yes all");
    jButtonYesAll.setBounds(new Rectangle(92, 65, 79, 27));
    jButtonYesAll.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonYesAll_actionPerformed(e);
        GeneratorGUI.this.repaint();
      }
    });
    jButtonNo.setText("No");
    jButtonNo.setBounds(new Rectangle(247, 65, 79, 27));
    jButtonNo.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonNo_actionPerformed(e);
        GeneratorGUI.this.repaint();
      }
    });
    jButtonNoAll.setText("No All");
    jButtonNoAll.setBounds(new Rectangle(170, 65, 79, 27));
    jButtonNoAll.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonNoAll_actionPerformed(e);
        GeneratorGUI.this.repaint();
      }
    });

    questions.getContentPane().add(jLabelQuestion, null);
    questions.getContentPane().add(jLabelFileName, null);
    questions.getContentPane().add(jButtonYesAll, null);
    questions.getContentPane().add(jButtonNoAll, null);
    questions.getContentPane().add(jButtonYes, null);
    questions.getContentPane().add(jButtonNo, null);

  }

  /**
   * When the Questions dialog is requested this method will pop it up and listen
   * for a response.
   * @param ask
   */
  private void showQuestionDialog(String ask, String name) {
    ImageIcon qIcon = new ImageIcon("question1.gif");
    questions.setSize(350, 130);
    centerScreen(questions);

    //if id equal 1 then the caller asking to exit application so do this
    if(questionID == 1) {
      questions.setTitle("Exit?");
      jLabelQuestion.setIcon(qIcon);
      jLabelQuestion.setText(ask);
      jLabelFileName.setText("");
      //jLabelFileName.setIcon(webLogo);
      jButtonYes.setVisible(true);
      questions.getRootPane().setDefaultButton(jButtonYes);
      jButtonNo.setVisible(true);
      jButtonYesAll.setVisible(false);
      jButtonNoAll.setVisible(false);
    }
    else if(questionID == 2) {
      questions.setTitle("Overwrite?");
      questions.getRootPane().setDefaultButton(jButtonYesAll);
      jLabelQuestion.setIcon(qIcon);
      jLabelQuestion.setText(ask);
      jLabelFileName.setText(name + " ?");
      jButtonYes.setVisible(true);
      jButtonNo.setVisible(true);
      jButtonYesAll.setVisible(true);
      jButtonNoAll.setVisible(true);

    }
    else questionID = 0;
    questions.show();

  }

  /**
   * Constructs the toolbar, also adds action listeners for each event
   */
   private void constructToolBar() {

    ImageIcon iconOpen = new ImageIcon("Open24.gif");
    ImageIcon iconStop = new ImageIcon("Stop24.gif");
    ImageIcon iconSettings = new ImageIcon("Preferences24.gif");
    ImageIcon iconExit = new ImageIcon("door_exit.gif");

    tButtonSettings.setFont(new java.awt.Font("Dialog", 0, 10));
    tButtonSettings.setToolTipText("Settings");
    tButtonSettings.setIcon(iconSettings);
    tButtonSettings.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        tButtonSettings_actionPerformed(e);
        GeneratorGUI.this.repaint();
      }
    });
    tButtonTest.setFont(new java.awt.Font("Dialog", 0, 10));
    tButtonTest.setToolTipText("Test");
    tButtonTest.setIcon(iconOpen);
    tButtonTest.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        tButtonTest_actionPerformed(e);
        GeneratorGUI.this.repaint();
      }
    });
    tButtonStop.setFont(new java.awt.Font("Dialog", 0, 10));
    tButtonStop.setToolTipText("Stop");
    tButtonStop.setIcon(iconStop);
    tButtonStop.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        tButtonStop_actionPerformed(e);
        GeneratorGUI.this.repaint();
      }
    });
    jButtonTExit.setFont(new java.awt.Font("Dialog", 0, 10));
    jButtonTExit.setToolTipText("Exit?");
    jButtonTExit.setIcon(iconExit);
    jButtonTExit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonTExit_actionPerformed(e);
      }
    });

    jToolBar1.setFloatable(false);

    jButtonDummy2.setBorder(null);
    jButtonDummy2.setPreferredSize(new Dimension(350, 11));
    jButtonDummy2.setSize(100, 24);
    jButtonDummy2.setEnabled(false);

    jToolBar1.add(tButtonTest, null);
    jToolBar1.add(tButtonSettings, null);
    jToolBar1.add(jButtonDummy2, null);
    jToolBar1.add(tButtonStop, null);
    jToolBar1.add(jButtonTExit, null);

   }

  /**
   * visitClassFiles is being called in a thread so the window can listen for window events
   * during the recursion and generation of the test files
   */
  public void run() {
    threadStarted = true;
    try {
      selfThread.sleep(100);
    }
    catch(InterruptedException e) { System.err.println("Interrupted"); }

    GeneratorGUI.this.repaint();
    endPosition = getCount(file);
    count = 0;
    jProgressBar1.setValue(count);
    jProgressBar1.setMaximum(endPosition-1);

    visitClassFiles(file);

    jDisplayTextArea.setCaretPosition(jDisplayTextArea.getText().length());
    jDisplayTextArea.append("Total test generated: " + totalGenerated
      + "." + newline);//call my class and then generate results
    threadStarted = false;
    selfThread = null;
    endPosition = 0;
    count = 0;
  }

  /**
    * Recurses through the directories searching for .class files to generate tests on.
    * @param file
    */
  private void visitClassFiles(File file) {
    Generator test = null;
    File[] list = null;

    if (file.isDirectory()) {
      list = file.listFiles();
      for (int i = 0; i < list.length; i++) {
        visitClassFiles(list[i]);
      }
    }
    if(!overwriteNoAll) {
      if (file.getName().endsWith(".class")) {
        if((file.getName().indexOf('$')) == -1) {
          try{
            test = new Generator( file, settings.getDestination(), settings.getAuthor(),
              settings.getEmail(), settings.getCompany());
          }catch(ClassNotFoundException e) { e.printStackTrace();}

          if (test != null) {
            if(!test.doesItExist()) {//checking if file already exists
              if ( test.createTest() ){
                jDisplayTextArea.scrollRectToVisible(new Rectangle(new Point(this.getWidth(), jDisplayTextArea.getHeight() )));
                jDisplayTextArea.append(test.fileDone + " was generated ..." + newline);
                totalGenerated++;
              }
              if(count != endPosition)
                jProgressBar1.setValue(count++);
              else jProgressBar1.setValue(endPosition);

            }
            else {
              questionID = 2;
                if(!overwriteYesAll)
                  showQuestionDialog("Overwrite existing test file for ", file.getName());
                if(overwriteOK || overwriteYesAll) {
                  if ( test.createTest() ){
                    jDisplayTextArea.scrollRectToVisible(new Rectangle(new Point(this.getWidth(), jDisplayTextArea.getHeight() )));
                    jDisplayTextArea.append(test.fileDone + " was generated ..." + newline);
                    totalGenerated++;
                  }

                  if(count != endPosition)
                    jProgressBar1.setValue(count++);
                  else jProgressBar1.setValue(endPosition);


                }//overwriteOK
                else {
                  if(count != endPosition)
                    jProgressBar1.setValue(count++);
                  else jProgressBar1.setValue(endPosition);
                }
            }
          }//end if test!= null

        }//end if $
      }//end if.class
    }
    else
      jProgressBar1.setValue(jProgressBar1.getMaximum());

  }

  /**
   * Will retrieve the count of the number of files expected to be tested,
   * This count is being used in the progress bar
   * @param fl
   * @return count
   */
  private int getCount(File fl) {
    File [] l = null;

    if (fl.isDirectory()) {
      l = fl.listFiles();
      if(l != null)
        for (int i = 0; i < l.length; i++) {
          getCount(l[i]);
        }
      else selfThread = null;
    }

    if (fl.getName().endsWith(".class"))
      if((fl.getName().indexOf('$')) == -1)
        count++;

    return count;
  }

  /**
    * Overridden so we can exit when window is closed
    * @param e
    */
  protected void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      questionID = 1;
      showQuestionDialog("Do you want to exit the application?", "");
      GeneratorGUI.this.repaint();
    }
    if(questionID != 1)
      super.processWindowEvent(e);
  }

  /**
    * Action Perform for the Test menu File/Dir Menu Item selection
    * @param e
    */
  private void jFileMenuItem_actionPerformed(ActionEvent e) {
    overwriteOK = false;
    overwriteYesAll = false;
    overwriteNoAll = false;

    jFileChooser.setApproveButtonMnemonic('e');

    int returnVal = jFileChooser.showDialog(GeneratorGUI.this, "Test");

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      file = jFileChooser.getSelectedFile();
      totalGenerated = 0;
      jDisplayTextArea.setText("");
      jDisplayTextArea.append("Test generation started on: " + file.getPath() + newline);

      if(selfThread == null) {
        selfThread = new Thread(GeneratorGUI.this);
        selfThread.start();
      }
      jFileChooser.cancelSelection();
    }

  }

  /**
   * Action Perform for the Test menu Exit Menu Item selection
   * @param e
   */
  private void jExitMenuItem_actionPerformed(ActionEvent e) {
    questionID = 1;
    showQuestionDialog("Do you want to exit the application?", "");
    GeneratorGUI.this.repaint();
  }

  /**
    * Action Perform for the Options menu Settings Menu Item selection
    * @param e
    */
  private void jSettingsMenuItem_actionPerformed(ActionEvent e) {
    centerScreen(settings);
    settings.show();
  }

  /**
   * Action Perform for the Help menu About Menu Item selection
   * @param e
   */
  private void jAboutMenuItem_actionPerformed(ActionEvent e) {
    centerScreen(about);
    about.show();
  }

  /**
   * Action Perform for the OK button in the settings dialog
   * @param e
   */
  private void jAboutOKButton_actionPerformed(ActionEvent e) {
    about.hide();
    GeneratorGUI.this.repaint();
  }

  /**
   * When the enter key is pressed in the about box it will automatically close it.
   * @param e
   */
  private void about_keyPressed(KeyEvent e) {
    if(e.getKeyCode() == KeyEvent.VK_ENTER) {
      about.hide();
      GeneratorGUI.this.repaint();
    }
  }

  /**
   * When the toolbar test button is pressed it will perform the same operation
   * as jFileMenuItem.
   * @param e
   */
  private void tButtonTest_actionPerformed(ActionEvent e) {
    jFileMenuItem_actionPerformed(e);
    GeneratorGUI.this.repaint();
  }

  /**
   * When the toolbar settings button is pressed it will perform the same operation
   * as jSettingsMenuItem.
   * @param e
   */
  private void tButtonSettings_actionPerformed(ActionEvent e) {
    jSettingsMenuItem_actionPerformed(e);
    GeneratorGUI.this.repaint();
  }

  /**
   * When the toolbar Stop button is pressed it will stop the thread of excution
   * @param e
   */
  private void tButtonStop_actionPerformed(ActionEvent e) {
    overwriteNoAll = true;
    GeneratorGUI.this.repaint();
  }

  /**
   *
   */
  private void jButtonTExit_actionPerformed(ActionEvent e) {
    questionID = 1;
    showQuestionDialog("Do you want to exit the application?", "");
    GeneratorGUI.this.repaint();
  }

  /**
   * When the yes button in the questions dialog is pressed it will perform the
   * following actions.
   * @param e
   */
  private void jButtonYes_actionPerformed(ActionEvent e) {
    if(questionID == 1)
      System.exit(0);
    if(questionID == 2) {
      overwriteOK = true;
      questions.hide();
      GeneratorGUI.this.repaint();
    }
  }

  /**
   * When the yes all button in the questions dialog is pressed it will perform the
   * following actions.
   * @param e
   */
  private void jButtonYesAll_actionPerformed(ActionEvent e) {
    if(questionID == 2) {
      overwriteYesAll = true;
      questions.hide();
    }
    GeneratorGUI.this.repaint();
  }

  /**
   * When the no all button in the questions dialog is pressed it will perform the
   * following actions.
   * @param e
   */
  private void jButtonNoAll_actionPerformed(ActionEvent e) {
    if(questionID == 2) {
      overwriteNoAll = true;
      questions.hide();
    }
    GeneratorGUI.this.repaint();
  }

  /**
   * When the no button in the questions dialog is pressed it will perform the
   * following actions.
   * @param e
   */
  private void jButtonNo_actionPerformed(ActionEvent e) {
    if(questionID == 1)
      questions.hide();
    if(questionID == 2) {
      overwriteOK = false;
      questions.hide();
    }
    GeneratorGUI.this.repaint();
  }

  /**
   * Centers the component send to this method
   * @param com
   */
  private void centerScreen(Component com) {
    Dimension dim = getToolkit().getScreenSize();
    Rectangle abounds = com.getBounds();
    com.setLocation((dim.width - abounds.width) / 2,
       (dim.height - abounds.height) / 2);
    com.requestFocus();
  }

  private final static String fs = System.getProperty("file.separator");
  private String newline = System.getProperty("line.separator");
//  private String Destination = "Default";
  private static int totalGenerated = 0;
  private int endPosition = 0;
  private int count = 0;
  private File file = null;
  boolean overwriteOK = false;
  boolean overwriteYesAll = false;
  boolean overwriteNoAll = false;

}