package clientSide;

import clientSide.attributes.Entity;
import clientSide.attributes.World;
import clientSide.graphics.BlankCursor;
import clientSide.controllerHandlers.MouseHandler;
import clientSide.graphics.Crosshair;

import javax.swing.*;
import java.awt.*;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

public class GamePanel extends JPanel {
    private float mouseX = 100;
    private float mouseY = 100;

    public GamePanel(Client client) {
        setSize(Settings.GAME_WINDOW_WIDTH, Settings.GAME_WINDOW_HEIGHT);
        addListeners(client);
    }

    private void addListeners(Client client) {
        MouseHandler mouseHandler = new MouseHandler(client, this);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    public void hideCursor() {
        setCursor(BlankCursor.getBlankCursor());
    }

    public void showCursor() {
        setCursor(Cursor.getDefaultCursor());
    }

    public void updateCrosshair(float x, float y) {
        mouseX = x;
        mouseY = y;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintWorld(g);
        paintCrosshair(g);
    }

    private void paintCrosshair(Graphics g) {
        Crosshair.paint(g, mouseX, mouseY);
    }

    private void paintWorld(Graphics g) {
        World world = World.getInstance();
        world.getGround().paint(g);
        for (Entity entity : world.getEntities()) {
            entity.paint(g);
        }
    }
}
