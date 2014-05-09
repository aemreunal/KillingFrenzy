package clientSide.graphics;

import javax.imageio.ImageIO;

import clientSide.Settings;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/*
 * This code belongs to:
 * Erdi Gultekin
 */

public class Wall {
    private static BufferedImage img;

    public static void paint(Graphics g, float wallX, float wallY) {
        try {
            img = ImageIO.read(new File("images\\Wall\\wall.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int x = (int) wallX-5;
        int y = (int) wallY-5;
        int width = img.getWidth();
        int height = img.getHeight();
        g.drawImage(img, x, y, width, height, null);
    }
}
