package clientSide.controllerHandlers;

import clientSide.Client;
import clientSide.graphics.GraphicsProcessor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

public class KeyboardHandler implements KeyListener {
    private Client client;
    private GraphicsProcessor graphicsProcessor;
    private boolean usingMac = false;

    public KeyboardHandler(Client client, GraphicsProcessor graphicsProcessor) {
        // If system is Mac, output will be "Mac OS X"
        // If system is Windows, output will be "Windows ..."
        usingMac = (System.getProperty("os.name").charAt(0) == 'M');
        this.graphicsProcessor = graphicsProcessor;
        this.client = client;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                if ((usingMac && e.isMetaDown()) || (!usingMac && e.isControlDown())) {
                    // Close the game
                    break;
                }
                graphicsProcessor.setMovingNorth(true);
                break;
            case KeyEvent.VK_D:
                graphicsProcessor.setMovingEast(true);
                break;
            case KeyEvent.VK_S:
                graphicsProcessor.setMovingSouth(true);
                break;
            case KeyEvent.VK_A:
                graphicsProcessor.setMovingWest(true);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Do nothing
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                graphicsProcessor.setMovingNorth(false);
                break;
            case KeyEvent.VK_D:
                graphicsProcessor.setMovingEast(false);
                break;
            case KeyEvent.VK_S:
                graphicsProcessor.setMovingSouth(false);
                break;
            case KeyEvent.VK_A:
                graphicsProcessor.setMovingWest(false);
                break;
        }
    }
}
