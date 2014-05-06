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

@SuppressWarnings("serial")
public class GameClientUIManager {
    private JFrame window;
    private GamePanel panel;
    private GraphicsProcessor graphicsProcessor;

    public GameClientUIManager(Client client) {
        this.window = new JFrame();
        this.panel = new GamePanel();
        window.add(panel);
        this.graphicsProcessor = new GraphicsProcessor(client, panel);
        graphicsProcessor.start();
        setWindowAttributes();
        addListeners();
        window.setSize(panel.getSize());
        window.setVisible(true);
    }

    private void addListeners() {
        window.addKeyListener(new KeyboardHandler(graphicsProcessor));
    }

    private void setWindowAttributes() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
