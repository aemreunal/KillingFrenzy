package clientSide.controllerHandlers;

import clientSide.Client;
import clientSide.processors.GraphicsProcessor;
import packets.KeyPressPacket;
import packets.KeyReleasePacket;

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
                broadcastKeyPressed(e);
                break;
            case KeyEvent.VK_D:
                broadcastKeyPressed(e);
                break;
            case KeyEvent.VK_S:
                broadcastKeyPressed(e);
                break;
            case KeyEvent.VK_A:
                broadcastKeyPressed(e);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                broadcastKeyReleased(e);
                break;
            case KeyEvent.VK_D:
                broadcastKeyReleased(e);
                break;
            case KeyEvent.VK_S:
                broadcastKeyReleased(e);
                break;
            case KeyEvent.VK_A:
                broadcastKeyReleased(e);
                break;
        }
    }

    private void broadcastKeyPressed(KeyEvent e) {
        client.sendPacket(new KeyPressPacket(e.getKeyCode()));
    }

    private void broadcastKeyReleased(KeyEvent e) {
        client.sendPacket(new KeyReleasePacket(e.getKeyCode()));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Do nothing
    }
}
