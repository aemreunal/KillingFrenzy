package clientSide.processors;

import clientSide.Client;
import clientSide.gui.GamePanel;
import global.Settings;

import java.util.concurrent.TimeUnit;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

public class GraphicsProcessor extends Thread implements Runnable {
    private Client client;
    private GamePanel panel;

    public GraphicsProcessor(Client client, GamePanel panel) {
        this.client = client;
        this.panel = panel;
    }

    @Override
    public void run() {
        while (true) {
            panel.repaint();
            try {
                TimeUnit.MILLISECONDS.sleep(Settings.GRAPHICS_PROC_SLEEP_MILLIS);
            } catch (InterruptedException e) {
                System.err.println("Graphics processor thread sleep is interrupted!");
                e.printStackTrace();
            }
        }
    }
}
