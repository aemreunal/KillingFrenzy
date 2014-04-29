package clientSide;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

public class KeyboardListener implements KeyListener {
    private GamePanel panel;
    
    public KeyboardListener(GamePanel gamePanel) {
        panel = gamePanel;
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
    public void keyTyped(KeyEvent e) {
        // Do nothing
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        // Do nothing
    }
}
