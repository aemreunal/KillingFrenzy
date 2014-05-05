package clientSide.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


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
}