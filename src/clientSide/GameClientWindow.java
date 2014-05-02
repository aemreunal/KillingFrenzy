package clientSide;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import clientSide.attributes.Settings;
import clientSide.controllerHandlers.KeyboardHandler;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

@SuppressWarnings("serial")
public class GameClientWindow extends JFrame implements Runnable {
    private GamePanel panel;
    private boolean gameIsRunning = false;
    
    public GameClientWindow() {
        setFrameAttributes();
        addPanel();
        addListeners();
        setVisible(true);
    }
    
    public boolean isGameRunning() {
        return gameIsRunning;
    }
    
    public void setGameIsRunning(boolean gameIsRunning) {
        this.gameIsRunning = gameIsRunning;
    }
    
    @Override
    public void run() {
        // Timer timer = new Timer(100, new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // // TODO Auto-generated method stub
        //
        // }
        // });
        
        while (gameIsRunning) {
            panel.repaint();
            
            // A wrapper for Thread.sleep(millis)
            try {
                TimeUnit.MILLISECONDS.sleep((long) (1000.0 / Settings.REFRESH_RATE));
            } catch (InterruptedException e1) {
                System.err.println("Game loop interrupted!");
                e1.printStackTrace();
                System.exit(-1);
            }
        }
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