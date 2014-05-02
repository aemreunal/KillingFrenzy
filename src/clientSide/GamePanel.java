package clientSide;

import java.awt.Cursor;
import java.awt.Graphics;

import javax.swing.JPanel;

import clientSide.attributes.Settings;
import clientSide.attributes.player.Direction;
import clientSide.controllerHandlers.BlankCursor;
import clientSide.controllerHandlers.MouseHandler;
import clientSide.graphics.Crosshair;
import clientSide.graphics.Player;
import clientSide.graphics.WorldGround;

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
        paintGround(g);
        paintPlayer(g);
        paintCrosshair(g);
    }
    
    private void paintGround(Graphics g) {
        WorldGround.paint(g);
    }
    
    private void paintCrosshair(Graphics g) {
        Crosshair.paint(g, mouseX, mouseY);
    }
    
    private void paintPlayer(Graphics g) {
        Player.paint(g, playerX, playerY);
    }
}