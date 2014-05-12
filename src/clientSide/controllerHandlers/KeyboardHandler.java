package clientSide.controllerHandlers;

import clientSide.Client;
import clientSide.Settings;
import clientSide.attributes.World;
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
            case Settings.KEY_NORTH:
            case Settings.KEY_EAST:
            case Settings.KEY_SOUTH:
            case Settings.KEY_WEST:
                startMovementAnimation(true);
                broadcastKeyPressed(e);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case Settings.KEY_NORTH:
            case Settings.KEY_EAST:
            case Settings.KEY_SOUTH:
            case Settings.KEY_WEST:
                startMovementAnimation(false);
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

    private void startMovementAnimation(boolean value) {
        World.getThisPlayer().getPhysAttr().setMoving(value);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Do nothing
    }
}
