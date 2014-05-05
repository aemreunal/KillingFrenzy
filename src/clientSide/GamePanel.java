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
        FriendlyPlayer.init();
        WorldGround.init();
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
        switch (dir) {
            case NORTH:
                playerY -= Settings.PLAYER_SPEED;
                break;
            case EAST:
                playerX += Settings.PLAYER_SPEED;
                break;
            case SOUTH:
                playerY += Settings.PLAYER_SPEED;
                break;
            case WEST:
                playerX -= Settings.PLAYER_SPEED;
                break;
        }
        repaint();
    }

    public void updateCrosshair(float x, float y) {
        mouseX = x;
        mouseY = y;
        repaint();
    }

    public void updatePlayer(float x, float y) {
        playerX = x;
        playerY = y;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        WorldGround.paint(g);
        FriendlyPlayer.paintMoving(g, playerX, playerY, (float) (Math.PI / 2));
        Crosshair.paint(g, mouseX, mouseY);
    }
}
