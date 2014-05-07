package clientSide;

import clientSide.attributes.player.Direction;
import clientSide.controllerHandlers.BlankCursor;
import clientSide.controllerHandlers.MouseHandler;
import clientSide.graphics.Crosshair;
import clientSide.graphics.FriendlyPlayer;
import clientSide.graphics.WorldGround;

import javax.swing.*;
import java.awt.*;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

public class GamePanel extends JPanel {
    private float playerX = 100;
    private float playerY = 100;
    private float mouseX = 100;
    private float mouseY = 100;

    public GamePanel() {
        setSize(Settings.GAME_WINDOW_WIDTH, Settings.GAME_WINDOW_HEIGHT);
        addListeners();
    }

    private void addListeners() {
        MouseHandler mouseHandler = new MouseHandler(this);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    public void hideCursor() {
        setCursor(BlankCursor.getBlankCursor());
    }

    public void showCursor() {
        setCursor(Cursor.getDefaultCursor());
    }

    public void move(Direction dir) {
        float imageCenterX = playerX + (Settings.movingImageWidth / 2);
        float imageCenterY = playerY + (Settings.movingImageHeight / 2);
        switch (dir) {
            case NORTH:
                if (imageCenterY > 0) {
                    playerY -= Settings.PLAYER_LOC_UPDATE_AMOUNT;
                }
                break;
            case EAST:
                if (imageCenterX < Settings.GAME_WINDOW_WIDTH) {
                    playerX += Settings.PLAYER_LOC_UPDATE_AMOUNT;
                }
                break;
            case SOUTH:
                if (imageCenterY < Settings.GAME_WINDOW_HEIGHT) {
                    playerY += Settings.PLAYER_LOC_UPDATE_AMOUNT;
                }
                break;
            case WEST:
                if (imageCenterX > 0) {
                    playerX -= Settings.PLAYER_LOC_UPDATE_AMOUNT;
                }
                break;
        }
    }

    public void updateCrosshair(float x, float y) {
        mouseX = x;
        mouseY = y;
    }

    public void updatePlayer(float x, float y) {
        playerX = x;
        playerY = y;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        WorldGround.paint(g);
        FriendlyPlayer.paintMoving(g, playerX, playerY, getPlayerAngle());
        Crosshair.paint(g, mouseX, mouseY);
    }

    public float getPlayerAngle() {
        float imageCenterX = playerX + (Settings.movingImageWidth / 2);
        float imageCenterY = playerY + (Settings.movingImageHeight / 2);
        return (float) Math.atan2(mouseY - imageCenterY, mouseX - imageCenterX);
    }
}
