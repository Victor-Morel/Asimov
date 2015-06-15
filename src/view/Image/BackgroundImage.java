package view.Image;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import controller.config;

public class BackgroundImage {

    private BufferedImage image;

    public BackgroundImage(){
        try {
            image = ImageIO.read(new File(config.pathStyleImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawImage(Graphics g){
        g.drawImage(image, 0, 0, null);
    }
}
