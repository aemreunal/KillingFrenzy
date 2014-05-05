package clientSide.controllerHandlers;

import clientSide.GamePanel;

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
    private GamePanel panel;

    public MouseHandler(GamePanel gamePanel) {
        panel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        panel.updatePlayer(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Do nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Do nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        panel.hideCursor();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        panel.showCursor();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        panel.hideCursor();
        panel.updateCrosshair(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        panel.hideCursor();
        panel.updateCrosshair(e.getX(), e.getY());
    }

}
