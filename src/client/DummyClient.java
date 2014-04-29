package client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

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
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void addPanel() {
        GamePanel panel = new GamePanel();
        add(panel);
        addKeyListener(new KeyEventListener(panel));
    }
}

class GamePanel extends JPanel {
    private final int SPEED = 5;
    
    private int x = 100;
    private int y = 100;
    private final int SIZE = 10;
    
    public void move(Direction dir) {
        switch (dir) {
        case NORTH:
            y -= SPEED;
            break;
        case EAST:
            x += SPEED;
            break;
        case SOUTH:
            y += SPEED;
            break;
        case WEST:
            x -= SPEED;
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

class KeyEventListener implements KeyListener {
    private GamePanel panel;
    
    public KeyEventListener(GamePanel gamePanel) {
        panel = gamePanel;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // Do nothing
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_W:
            panel.move(Direction.NORTH);
            break;
        case KeyEvent.VK_D:
            panel.move(Direction.EAST);
            break;
        case KeyEvent.VK_S:
            panel.move(Direction.SOUTH);
            break;
        case KeyEvent.VK_A:
            panel.move(Direction.WEST);
            break;
        default:
            break;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        // Do nothing
    }
}

enum Direction {
    NORTH, WEST, SOUTH, EAST
}