package clientSide;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import clientSide.attributes.player.Direction;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

public class KeyboardHandler implements KeyListener {
    private GamePanel panel;
    private boolean usingMac = false;
    
    public KeyboardHandler(GamePanel gamePanel) {
        // If system is Mac, output will be "Mac OS X"
        // If system is Windows, output will be "Windows ..."
        usingMac = (System.getProperty("os.name").charAt(0) == 'M');
        panel = gamePanel;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_W:
            if ((usingMac && e.isMetaDown()) || (!usingMac && e.isControlDown())) {
                // Close the game
                break;
            }
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
