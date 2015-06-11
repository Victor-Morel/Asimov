package testing;

import javax.swing.UIManager;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Rectangle;

/**
  * Copyright 2000 Webmind Inc.
  * TestGenerator: <p>
  *
  * This the is the starting point for the GUI application.
  *
  * @author Jason Torres (jtorres@webmind.com)
  * @version 1.0
  */
public class TestGenerator {

  boolean packFrame = false;

  /**
   * Construct the application
   */
  public TestGenerator() {

    GeneratorGUI frame = new GeneratorGUI();
    //Validate frames that have preset sizes
    //Pack frames that have useful preferred size info, e.g. from their layout
    if (packFrame) {
      frame.pack();
    }
    else {
      frame.validate();
    }

    Splash mySplash = new Splash(frame, "splash.gif");

    try{
    Thread.sleep(2000);
    }catch(InterruptedException e) {}

    centerScreen(frame);
    frame.setVisible(true);

  }

  /**
   * Centers the frame
   */
  private void centerScreen(Component com) {
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    Rectangle abounds = com.getBounds();
    com.setLocation((dim.width - abounds.width) / 2,
       (dim.height - abounds.height) / 2);
    com.requestFocus();
  }

  /**
   * Main method
   *
   */
  public static void main(String[] args) {

    try {
      //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    new TestGenerator();
  }
}