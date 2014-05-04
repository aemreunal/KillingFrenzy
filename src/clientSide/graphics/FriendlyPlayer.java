package clientSide.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.Timer;
import javax.imageio.ImageIO;

import clientSide.attributes.Settings;

/*
 * This code belongs to:
 * Erdi Gultekin
 * Image rotate code is taken from:
 * https://code.google.com/p/game-engine-for-java/source/browse/src/com/gej/util/ImageTool.java
 */

public class FriendlyPlayer implements ActionListener {
	static BufferedImage img;
	static Timer timer;
	static String standingImage = "images\\FriendlyPlayer\\0.png";
	static String movingImage1 = "images\\FriendlyPlayer\\1.png";	
	static String movingImage2 = "images\\FriendlyPlayer\\2.png";
	static String movingImage3 = "images\\FriendlyPlayer\\3.png";
	static String movingImage4 = "images\\FriendlyPlayer\\4.png";
	static String movingImage5 = "images\\FriendlyPlayer\\5.png";
	static String movingImage6 = "images\\FriendlyPlayer\\6.png";
	static String movingImage7 = "images\\FriendlyPlayer\\7.png";
	static String movingImage8 = "images\\FriendlyPlayer\\8.png";
	
		
	  public static void paintStanding(Graphics g, float playerX, float playerY) throws IOException {
		  	int x = (int) playerX;
	        int y = (int) playerY;
	        img = ImageIO.read(new File(standingImage));
            int width = img.getWidth();
            int height = img.getHeight();
            g.drawImage(img, x, y, width, height, null);    
	  }
	  public static void paintMoving(Graphics g, float playerX, float playerY) throws IOException {
		  	int x = (int) playerX;
	        int y = (int) playerY;
	        
	        img = ImageIO.read(new File(movingImage1));
	        int width = img.getWidth();
	        int height = img.getHeight();
	        g.drawImage(img, x, y, width, height, null);
	  }
	  
	  public static void setAngle(double angle){
		  angle = -angle;
		  img = (BufferedImage) rotate(img, angle);
	  }
	  public void move(){
		  timer = new Timer(Settings.PLAYER_ANIMATION_SPEED, this);
	  }
	  public void actionPerformed(ActionEvent e) {
		 
		}
	  public static Image rotate(Image img, double angle){
	        double sin = Math.abs(Math.sin(Math.toRadians(angle))), cos = Math.abs(Math.cos(Math.toRadians(angle)));
	        int w = img.getWidth(null), h = img.getHeight(null);
	        int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h
	                * cos + w * sin);
	        BufferedImage bimg = toBufferedImage(getEmptyImage(neww, newh));
	        Graphics2D g = bimg.createGraphics();
	        g.translate((neww - w) / 2, (newh - h) / 2);
	        g.rotate(Math.toRadians(angle), w / 2, h / 2);
	        g.drawRenderedImage(toBufferedImage(img), null);
	        g.dispose();
	        return toImage(bimg);
	    }
	  public static Image toImage(BufferedImage bimage){
	        // Casting is enough to convert from BufferedImage to Image
	        Image img = (Image) bimage;
	        return img;
	    }
	  public static BufferedImage toBufferedImage(Image img){
	        if (img instanceof BufferedImage) {
	            return (BufferedImage) img;
	        }
	        // Create a buffered image with transparency
	        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	        // Draw the image on to the buffered image
	        Graphics2D bGr = bimage.createGraphics();
	        bGr.drawImage(img, 0, 0, null);
	        bGr.dispose();
	        // Return the buffered image
	        return bimage;
	    }
	  public static Image getEmptyImage(int width, int height){
	        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	        return toImage(img);
	    }
}
