package clientSide;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import clientSide.attributes.Settings;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

@SuppressWarnings("serial")
public class DummyClient extends JFrame {
    public static void main(String[] args) {
        new DummyClient();
    }
    
    public DummyClient() {
        setFrameAttributes();
        addPanel();
        setVisible(true);
    }
    
    private void setFrameAttributes() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void addPanel() {
        GamePanel panel = new GamePanel();
        add(panel);
        setSize(panel.getSize());
        addKeyListener(new KeyboardListener(panel));
    }
}

@SuppressWarnings("serial")
class GamePanel extends JPanel {
    
    private int x = 100;
    private int y = 100;
    private final int SIZE = 10;
    
    public GamePanel() {
        setSize(Settings.GAME_WINDOW_WIDTH, Settings.GAME_WINDOW_HEIGHT);
    }
    
    public void move(Direction dir) {
        switch (dir) {
        case NORTH:
            y -= Settings.PLAYER_SPEED;
            break;
        case EAST:
            x += Settings.PLAYER_SPEED;
            break;
        case SOUTH:
            y += Settings.PLAYER_SPEED;
            break;
        case WEST:
            x -= Settings.PLAYER_SPEED;
            break;
        }
        repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(x, y, SIZE, SIZE);
    }
}

enum Direction {
    NORTH, WEST, SOUTH, EAST
}