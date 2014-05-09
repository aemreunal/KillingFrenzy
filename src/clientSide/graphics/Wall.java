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
    private static boolean errorOccurred = false;
    
    public Wall() {
        try {
            img = ImageIO.read(new File(Settings.WALL_IMAGE_FILE_PATH));
        } catch (IOException e) {
            System.err.println("Unable to load wall image!");
            System.err.println("Loading plain black for walls instead.");
            errorOccurred = true;
        }
        
    }

    public static void paint(Graphics g, float wallX, float wallY) {
       if(errorOccurred){
           g.setColor(Settings.DEFAULT_WALL_COLOR);
       }else{
        int width = img.getWidth();
        int height = img.getHeight();
        g.drawImage(img, (int)wallX, (int)wallY, width, height, null);
       }
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
