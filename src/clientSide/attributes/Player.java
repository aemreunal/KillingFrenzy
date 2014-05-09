package clientSide.attributes;
/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

import clientSide.Settings;
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

    public static Player createPlayer(boolean isMine) {
        if (isMine) {
            Player player = new Player(Settings.FRIENDLY_PLAYER_STANDING_IMAGE_FILE_PATH, Settings.FRIENDLY_PLAYER_MOVING_IMAGE_FILE_PATH);
            World.setThisPlayer(player);
            return player;
        } else {
            return new Player(Settings.ENEMY_PLAYER_STANDING_IMAGE_FILE_PATH, Settings.ENEMY_PLAYER_MOVING_IMAGE_FILE_PATH);
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

    public void move(Direction dir) {
        float playerX = physAttr.getxCoor();
        float playerY = physAttr.getyCoor();
        float imageCenterX = playerX + (Settings.movingImageWidth / 2);
        float imageCenterY = playerY + (Settings.movingImageHeight / 2);
        switch (dir) {
            case NORTH:
                if (imageCenterY > 0) {
                    physAttr.setyCoor(playerY - Settings.PLAYER_LOC_UPDATE_AMOUNT);
                }
                break;
            case EAST:
                if (imageCenterX < Settings.GAME_WINDOW_WIDTH) {
                    physAttr.setxCoor(playerX + Settings.PLAYER_LOC_UPDATE_AMOUNT);
                }
                break;
            case SOUTH:
                if (imageCenterY < Settings.GAME_WINDOW_HEIGHT) {
                    physAttr.setyCoor(playerY + Settings.PLAYER_LOC_UPDATE_AMOUNT);
                }
                break;
            case WEST:
                if (imageCenterX > 0) {
                    physAttr.setxCoor(playerX - Settings.PLAYER_LOC_UPDATE_AMOUNT);
                }
                break;
        }
    }

    public void paint(Graphics g) {
        BufferedImage playerImage = physAttr.isMoving() ? movingImages[currentMovingImage] : standingImage;
        g.drawImage(GraphicsProcessor.rotate(playerImage, physAttr.getAngle(), movingImageWidth, movingImageHeight), (int) physAttr.getxCoor(), (int) physAttr.getyCoor(), null);
    }

    public float getAngle() {
        return physAttr.getAngle();
    }
}
