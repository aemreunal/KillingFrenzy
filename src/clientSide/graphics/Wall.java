package clientSide.graphics;

import javax.imageio.ImageIO;
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
        int x = (int) wallX;
        int y = (int) wallY;
        int width = img.getWidth();
        int height = img.getHeight();
        g.drawImage(img, x, y, width, height, null);
    }
    public static void paintWalls(Graphics g, float startX, float startY, boolean isVertical, int size){
        for(int i=0; i<size; i++){
            if(isVertical){
                Wall.paint(g, startX, startY);
                startY = startY+10;
            }else{
                Wall.paint(g, startX, startY);
               startX = startX+10;
            }
        }
    }
}
