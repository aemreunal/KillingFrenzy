package clientSide;

import handlers.BlankCursor;
import handlers.MouseHandler;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;

import javax.swing.JPanel;

import clientSide.attributes.Settings;
import clientSide.attributes.player.Direction;

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
    private final int SIZE = 10;
    
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
        paintPlayer(g);
        paintCrosshair(g);
    }
    
    private void paintCrosshair(Graphics g) {
        g.setColor(Color.RED);
        // Draw vertical line
        g.drawLine((int) mouseX, (int) (mouseY - (Settings.CROSSHAIR_SIZE >> 1)), (int) mouseX, (int) (mouseY + (Settings.CROSSHAIR_SIZE >> 1)));
        // Draw horizontal line
        g.drawLine((int) (mouseX - (Settings.CROSSHAIR_SIZE >> 1)), (int) mouseY, (int) (mouseX + (Settings.CROSSHAIR_SIZE >> 1)), (int) mouseY);
        // Draw oval
        g.drawOval((int) (mouseX - (Settings.CROSSHAIR_SIZE >> 1)), (int) (mouseY - (Settings.CROSSHAIR_SIZE >> 1)), Settings.CROSSHAIR_SIZE, Settings.CROSSHAIR_SIZE);
    }
    
    private void paintPlayer(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect((int) playerX, (int) playerY, SIZE, SIZE);
    }
}