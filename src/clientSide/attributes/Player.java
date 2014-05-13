package clientSide.attributes;
/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

import clientSide.Settings;

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
    protected BufferedImage[] movingImages = new BufferedImage[Settings.NUM_PLAYER_ANIMATION_IMAGES];
    protected int movingImageWidth;
    protected int movingImageHeight;

    protected int currentMovingImage = 0;
    protected Timer animationTimer = new Timer(Settings.PLAYER_ANIMATION_SPEED, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMovingImage = (currentMovingImage + 1) % Settings.NUM_PLAYER_ANIMATION_IMAGES;
        }
    });

    public static Player createPlayer(boolean isMine) {
        return createPlayer(isMine, 10, 10, 0);
    }

    public static Player createPlayer(boolean isMine, float xCoor, float yCoor, float angle) {
        if (isMine) {
            Player player = new Player(Settings.FRIENDLY_PLAYER_STANDING_IMAGE_FILE_PATH, Settings.FRIENDLY_PLAYER_MOVING_IMAGE_FILE_PATH, xCoor, yCoor, angle);
            World.setThisPlayer(player);
            return player;
        } else {
            return new Player(Settings.ENEMY_PLAYER_STANDING_IMAGE_FILE_PATH, Settings.ENEMY_PLAYER_MOVING_IMAGE_FILE_PATH, xCoor, yCoor, angle);
        }
    }

    private Player(String standingImageFilePath, String movingImageFilePath, float xCoor, float yCoor, float angle) {
        super(xCoor, yCoor, angle);
        loadImages(standingImageFilePath, movingImageFilePath);

    }

    private void loadImages(String standingImageFilePath, String movingImageFilePath) {
        try {
            standingImage = ImageIO.read(new File(standingImageFilePath));
            for (int i = 0; i < Settings.NUM_PLAYER_ANIMATION_IMAGES; i++) {
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
        BufferedImage playerImage = physAttr.isMoving() ? movingImages[currentMovingImage] : standingImage;
        g.drawImage(rotate(playerImage, physAttr.getAngle(), movingImageWidth, movingImageHeight), (int) physAttr.getxCoor(), (int) physAttr.getyCoor(), null);
    }

    public float getAngle() {
        return physAttr.getAngle();
    }

    public void updateAngle(float mouseX, float mouseY) {
        float imageCenterX = physAttr.getxCoor() + (movingImageWidth >> 1);
        float imageCenterY = physAttr.getyCoor() + (movingImageHeight >> 1);
        physAttr.setAngle((float) Math.atan2(mouseY - imageCenterY, mouseX - imageCenterX));
    }
}
