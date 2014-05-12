package clientSide.controllerHandlers;

import clientSide.Client;
import clientSide.Settings;
import clientSide.attributes.World;
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

    public KeyboardHandler(Client client) {
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
