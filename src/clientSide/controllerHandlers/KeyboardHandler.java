package clientSide.controllerHandlers;

import clientSide.Client;
import clientSide.graphics.GraphicsProcessor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import packets.KeyPressPacket;
import packets.KeyReleasePacket;

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
                client.sendPacket(new KeyPressPacket(e.getKeyCode()));
                break;
            case KeyEvent.VK_D:
                graphicsProcessor.setMovingEast(true);
                client.sendPacket(new KeyPressPacket(e.getKeyCode()));
                break;
            case KeyEvent.VK_S:
                graphicsProcessor.setMovingSouth(true);
                client.sendPacket(new KeyPressPacket(e.getKeyCode()));
                break;
            case KeyEvent.VK_A:
                graphicsProcessor.setMovingWest(true);
                client.sendPacket(new KeyPressPacket(e.getKeyCode()));
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
                client.sendPacket(new KeyReleasePacket(e.getKeyCode()));
                break;
            case KeyEvent.VK_D:
                graphicsProcessor.setMovingEast(false);
                client.sendPacket(new KeyReleasePacket(e.getKeyCode()));
                break;
            case KeyEvent.VK_S:
                graphicsProcessor.setMovingSouth(false);
                client.sendPacket(new KeyReleasePacket(e.getKeyCode()));
                break;
            case KeyEvent.VK_A:
                graphicsProcessor.setMovingWest(false);
                client.sendPacket(new KeyReleasePacket(e.getKeyCode()));
                break;
        }
    }
}
