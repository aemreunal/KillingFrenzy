package clientSide.attributes;

import clientSide.sounds.Death;
import global.Settings;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * Eren Sezener
 * Deniz Sokmen
 * Erdi Gultekin
 */

public class Player extends Entity {
    protected BufferedImage standingImage;
    protected BufferedImage[] movingImages = new BufferedImage[Settings.NUM_PLAYER_ANIMATION_IMAGES];
    protected int imageWidth;
    protected int imageHeight;
    protected int currentMovingImage = 0;
    protected Timer animationTimer = new Timer(Settings.PLAYER_ANIMATION_SPEED, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMovingImage = (currentMovingImage + 1) % Settings.NUM_PLAYER_ANIMATION_IMAGES;
        }
    });
    private float health;
    private int score;

    private Player(String standingImageFilePath, String movingImageFilePath, float xCoor, float yCoor, float angle) {
        super(xCoor, yCoor, angle);
        health = Settings.PLAYER_MAX_HEALTH;
        score = 0;
        loadImages(standingImageFilePath, movingImageFilePath);

    }

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

    private void loadImages(String standingImageFilePath, String movingImageFilePath) {
        try {
            standingImage = ImageIO.read(new File(standingImageFilePath));
            for (int i = 0; i < Settings.NUM_PLAYER_ANIMATION_IMAGES; i++) {
                movingImages[i] = ImageIO.read(new File(movingImageFilePath + i + Settings.MOVING_IMAGE_FILE_EXTENSION));
            }
            imageWidth = standingImage.getWidth();
            imageHeight = standingImage.getHeight();
            animationTimer.start();
        } catch (IOException e) {
            System.err.println("Unable to read standing/moving animation images!");
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        BufferedImage playerImage = physAttr.isMoving() ? movingImages[currentMovingImage] : standingImage;
        g.drawImage(rotate(playerImage, physAttr.getAngle(), imageWidth, imageHeight), (int) physAttr.getxCoor(), (int) physAttr.getyCoor(), null);
    }

    public float getAngle() {
        return physAttr.getAngle();
    }

    public float getX() {
        return physAttr.getxCoor();
    }

    public float getY() {
        return physAttr.getyCoor();
    }

    public void updateAngle(float mouseX, float mouseY) {
        float imageCenterX = physAttr.getxCoor() + (imageWidth >> 1);
        float imageCenterY = physAttr.getyCoor() + (imageHeight >> 1);
        physAttr.setAngle((float) Math.atan2(mouseY - imageCenterY, mouseX - imageCenterX));
    }

    public float[] getTipOfGun() {
        float imageCenterX = getX() + (imageWidth >> 1);
        float imageCenterY = getY() + (imageHeight >> 1);
        float angle = getAngle();
        float heightOffset = (float) (imageWidth * Math.sin(angle));
        float widthOffset = (float) (imageWidth * Math.cos(angle));
        return new float[] { imageCenterX + widthOffset, imageCenterY + heightOffset, angle };
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if (this.score + Settings.DYING_PENALTY == score) {
            // This means that the player died
            Death.playSound();
        }
        this.score = score;
    }
}
