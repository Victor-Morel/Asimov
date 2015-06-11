package testing;

import java.awt.*;
import java.awt.event.*;

/**
  * Copyright 2000 Webmind Inc.
  * Splash: <p>
  *
  * This class creates the splash screen for the GUI Application.
  *
  * @author Jason Torres (jtorres@webmind.com)
  * @version 1.1
  */
public class Splash extends Window {
  private Image splashImage;
  private int imgWidth, imgHeight;
  private String imgName;
  private static final int BORDERSIZE = 5;
  private static final Color BORDERCOLOR = Color.blue;
  Toolkit tk;

  public Splash(Frame f, String imgName) {
    super(f);
    this.imgName = imgName;
    tk = Toolkit.getDefaultToolkit();
    splashImage = loadSplashImage();
    showSplashScreen();
    f.addWindowListener(new WindowListener());
    }
  public Image loadSplashImage() {
    MediaTracker tracker = new MediaTracker(this);
    Image result;
    result = tk.getImage(imgName);
    tracker.addImage(result, 0);
    try {
      tracker.waitForAll();
      }
    catch (Exception e) {
      e.printStackTrace();
      }
    imgWidth = result.getWidth(this);
    imgHeight = result.getHeight(this);
    return (result);
    }

  public void showSplashScreen() {
    Dimension screenSize = tk.getScreenSize();
    setBackground(BORDERCOLOR);
    int w = imgWidth + (BORDERSIZE * 2);
    int h = imgHeight + (BORDERSIZE * 2);
    int x = (screenSize.width - w) /2;
    int y = (screenSize.height - h) /2;
    setBounds(x, y, w, h);
    setVisible(true);
    }

  public void paint(Graphics g) {
    g.drawImage(splashImage, BORDERSIZE, BORDERSIZE,
      imgWidth, imgHeight, this);
    }

  class WindowListener extends WindowAdapter {
    public void windowActivated(WindowEvent we) {
      setVisible(false);
      dispose();
      }
    }
  }