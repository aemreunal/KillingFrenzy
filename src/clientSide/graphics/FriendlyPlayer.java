package clientSide.graphics;

import clientSide.Settings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
 * This code belongs to:
 * Erdi Gultekin
 * Image rotate code is taken from:
 * https://code.google.com/p/game-engine-for-java/source/browse/src/com/gej/util/ImageTool.java
 */

public class FriendlyPlayer {
    private static BufferedImage standingImage;
    private static BufferedImage[] movingImages = new BufferedImage[Settings.NUM_CHAR_ANIMATION_IMAGES];
    private static int currentMovingImage = 0;

    private static Timer animationTimer = new Timer(Settings.PLAYER_ANIMATION_SPEED, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMovingImage = (currentMovingImage + 1) % Settings.NUM_CHAR_ANIMATION_IMAGES;
        }
    });

    public static void init() {
        try {
            standingImage = ImageIO.read(new File(Settings.STANDING_IMAGE_FILE_PATH));
            for(int i = 0; i < Settings.NUM_CHAR_ANIMATION_IMAGES; i++) {
                movingImages[i] = ImageIO.read(new File(Settings.MOVING_IMAGE_FILE_PATH + i + Settings.MOVING_IMAGE_FILE_EXTENSION));
            }
            Settings.movingImageWidth = standingImage.getWidth();
            Settings.movingImageHeight = standingImage.getHeight();
            animationTimer.start();
        } catch (IOException e) {
            System.err.println("Unable to read standing/moving animation images!");
            e.printStackTrace();
        }
    }

    public static void paintStanding(Graphics g, float playerX, float playerY, float angle) {
        g.drawImage(rotate(standingImage, angle), (int) playerX, (int) playerY, null);
    }

    public static void paintMoving(Graphics g, float playerX, float playerY, float angle) {
        g.drawImage(rotate(movingImages[currentMovingImage], angle), (int) playerX, (int) playerY, null);
    }

    public static BufferedImage rotate(BufferedImage img, double angle) {
        double sin = Math.abs(Math.sin(angle));
        double cos = Math.abs(Math.cos(angle));
        int newWidth = (int) Math.floor(Settings.movingImageWidth * cos + Settings.movingImageHeight * sin);
        int newHeight = (int) Math.floor(Settings.movingImageHeight * cos + Settings.movingImageWidth * sin);

        BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = rotatedImage.createGraphics();
        g.rotate(angle, Settings.movingImageWidth / 2, Settings.movingImageHeight / 2);
        g.drawRenderedImage(img, null);
        g.dispose();
        return rotatedImage;
    }
}
