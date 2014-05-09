package clientSide.attributes.player;
/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

import clientSide.Settings;
import clientSide.attributes.Entity;
import clientSide.processors.GraphicsProcessor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {
    protected BufferedImage standingImage;
    protected BufferedImage[] movingImages = new BufferedImage[Settings.NUM_CHAR_ANIMATION_IMAGES];
    protected int movingImageWidth;
    protected int movingImageHeight;

    protected int currentMovingImage = 0;
    protected Timer animationTimer = new Timer(Settings.PLAYER_ANIMATION_SPEED, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMovingImage = (currentMovingImage + 1) % Settings.NUM_CHAR_ANIMATION_IMAGES;
        }
    });

    public static Player createPlayer(boolean isFriendly) {
        if (isFriendly) {
            return new Player(Settings.FRIENDLY_PLAYER_STANDING_IMAGE_FILE_PATH, Settings.FRIENDLY_PLAYER_MOVING_IMAGE_FILE_PATH);
        } else {
            return null;
        }
    }

    private Player(String standingImageFilePath, String movingImageFilePath) {
        try {
            standingImage = ImageIO.read(new File(standingImageFilePath));
            for (int i = 0; i < Settings.NUM_CHAR_ANIMATION_IMAGES; i++) {
                movingImages[i] = ImageIO.read(new File(movingImageFilePath + i + Settings.MOVING_IMAGE_FILE_EXTENSION));
            }
            movingImageWidth = standingImage.getWidth();
            movingImageHeight = standingImage.getHeight();
            animationTimer.start();
        } catch (IOException e) {
            System.err.println("Unable to read standing/moving animation images!");
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        if (physicalAttributes.isMoving()) {
            g.drawImage(GraphicsProcessor.rotate(movingImages[currentMovingImage],
                            physicalAttributes.getAngle(),
                            movingImageWidth, movingImageHeight),
                    (int) physicalAttributes.getxCoor(), (int) physicalAttributes.getyCoor(), null
            );
        } else {
            g.drawImage(GraphicsProcessor.rotate(standingImage,
                            physicalAttributes.getAngle(),
                            movingImageWidth, movingImageHeight),
                    (int) physicalAttributes.getxCoor(), (int) physicalAttributes.getyCoor(), null
            );
        }
    }
}
