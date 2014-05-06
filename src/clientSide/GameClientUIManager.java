package clientSide;

import clientSide.controllerHandlers.KeyboardHandler;
import clientSide.graphics.GraphicsProcessor;

import javax.swing.*;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

public class GameClientUIManager {
    private JFrame window;
    private GamePanel panel;
    private Client client;
    private GraphicsProcessor graphicsProcessor;

    public GameClientUIManager(Client client) {
        this.client = client;
        createComponents();
        addListeners();
        setWindowAttributes();
    }

    private void createComponents() {
        this.window = new JFrame("GO GO");
        this.panel = new GamePanel();
        this.graphicsProcessor = new GraphicsProcessor(client, panel);
        this.graphicsProcessor.start();
    }

    private void addListeners() {
        window.addKeyListener(new KeyboardHandler(client, graphicsProcessor));
    }

    private void setWindowAttributes() {
        window.getContentPane().add(panel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(Settings.GAME_WINDOW_WIDTH, Settings.GAME_WINDOW_HEIGHT + window.getBounds().y);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }
}
