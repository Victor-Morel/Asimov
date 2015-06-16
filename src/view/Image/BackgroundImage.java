package view.Image;


import run.config;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundImage {

    private BufferedImage image;

    public BackgroundImage() {
        try {
            image = ImageIO.read(new File(config.pathStyleImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawImage(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }

    public void setImage(File file) {
        try {
            this.image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
