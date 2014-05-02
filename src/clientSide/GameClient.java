package clientSide;

import handlers.KeyboardHandler;

import javax.swing.JFrame;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

@SuppressWarnings("serial")
public class GameClient extends JFrame {
    private GamePanel panel;
    
    public GameClient() {
        setFrameAttributes();
        addPanel();
        addListeners();
        setVisible(true);
    }
    
    private void addListeners() {
        addKeyListener(new KeyboardHandler(panel));
    }
    
    private void setFrameAttributes() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void addPanel() {
        panel = new GamePanel();
        add(panel);
        setSize(panel.getSize());
    }
}