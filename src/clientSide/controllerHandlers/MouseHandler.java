package clientSide.controllerHandlers;

import clientSide.Client;
import clientSide.GamePanel;
import clientSide.attributes.Player;
import clientSide.attributes.World;
import clientSide.sounds.Gun;
import packets.BulletShotPacket;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

public class MouseHandler implements MouseListener, MouseMotionListener {
    private Client client;
    private GamePanel gamePanel;

    private Player player;

    public MouseHandler(Client client, GamePanel gamePanel) {
        this.client = client;
        this.gamePanel = gamePanel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        gamePanel.hideCursor();
        gamePanel.updateCrosshair(e.getX(), e.getY());
        shootBullet();
        Gun gun = new Gun();
        gun.playSound();
    }

    private void shootBullet() {
        client.sendPacket(new BulletShotPacket(getPlayer().getTipOfGun()));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        gamePanel.hideCursor();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        gamePanel.showCursor();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        gamePanel.hideCursor();
        gamePanel.updateCrosshair(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        gamePanel.hideCursor();
        gamePanel.updateCrosshair(e.getX(), e.getY());
        World.getThisPlayer().updateAngle(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Do nothing
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Do nothing
    }

    private Player getPlayer() {
        if (player == null) {
            player = World.getThisPlayer();
        }
        return player;
    }

}
