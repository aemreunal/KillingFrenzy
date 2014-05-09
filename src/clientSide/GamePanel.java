package clientSide;

import clientSide.attributes.Entity;
import clientSide.attributes.Direction;
import clientSide.attributes.world.World;
import clientSide.controllerHandlers.BlankCursor;
import clientSide.controllerHandlers.MouseHandler;
import clientSide.graphics.Crosshair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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

    private boolean playerMoving = false;

    private ArrayList<Entity> entities;

    public GamePanel() {
        setSize(Settings.GAME_WINDOW_WIDTH, Settings.GAME_WINDOW_HEIGHT);
        entities = new ArrayList<>();
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
        playerMoving = true;
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
        paintWorld(g);
        paintCrosshair(g);
    }

    private void paintCrosshair(Graphics g) {
        Crosshair.paint(g, mouseX, mouseY);
    }

    private void paintWorld(Graphics g) {
        World world = World.getInstance();
        world.getGround().paint(g);
        for (Entity entity : world.getEntities()) {
            entity.paint(g);
        }
    }

//    public float getPlayerAngle() {
//        float imageCenterX = playerX + (Settings.movingImageWidth / 2);
//        float imageCenterY = playerY + (Settings.movingImageHeight / 2);
//        return (float) Math.atan2(mouseY - imageCenterY, mouseX - imageCenterX);
//    }
//
//    public void setPlayerMoving(boolean playerMoving) {
//        this.playerMoving = playerMoving;
//    }
//
//    public void addEntity(Entity entity) {
//        entities.add(entity);
//    }
//
//    public void removeEntity(Entity entity) {
//        entities.remove(entity);
//    }
}
