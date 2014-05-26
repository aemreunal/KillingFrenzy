package clientSide.gui;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

import clientSide.Client;
import clientSide.controllerHandlers.KeyboardHandler;
import global.Settings;

import javax.swing.*;

public class GameWindow extends JFrame {
    public GameWindow(Client client, GamePanel panel) {
        addKeyListener(client);
        setGameAttributes(panel);
    }

    private void addKeyListener(Client client) {
        addKeyListener(new KeyboardHandler(client));
    }

    private void setGameAttributes(GamePanel panel) {
        this.setTitle("ŞIPIDIK ÖLDÜRMELİ");
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Settings.GAME_WINDOW_WIDTH, Settings.GAME_WINDOW_HEIGHT + getBounds().y);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
