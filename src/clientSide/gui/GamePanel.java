package clientSide.gui;

import java.awt.*;

import javax.swing.*;

import clientSide.Client;
import clientSide.attributes.Entity;
import clientSide.attributes.Player;
import clientSide.attributes.World;
import clientSide.controllerHandlers.MouseHandler;
import clientSide.graphics.BlankCursor;
import clientSide.graphics.Crosshair;
import clientSide.graphics.HealthBar;
import clientSide.graphics.ScoreBoard;
import global.Settings;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

public class GamePanel extends JPanel {
    private float mouseX = 100;
    private float mouseY = 100;

    private Player player;

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
        paintHealthBar(g);
        paintScoreBoard(g);
    }

    private void paintWorld(Graphics g) {
        World world = World.getInstance();
        world.getGround().paint(g);
        for (Entity entity : world.getEntities()) {
            entity.paint(g);
        }
    }

    private void paintCrosshair(Graphics g) {
        Crosshair.paint(g, mouseX, mouseY);
    }

    private void paintHealthBar(Graphics g) {
        if (getPlayer() != null) {
            HealthBar.paint(g, getPlayer().getHealth());
        }
    }

    private void paintScoreBoard(Graphics g) {
        if (getPlayer() != null) {
            ScoreBoard.paint(g, getPlayer().getScore());
        }
    }

    private Player getPlayer() {
        if (player == null) {
            player = World.getThisPlayer();
        }
        return player;
    }
}
