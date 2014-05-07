package clientSide.processors;
/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

import clientSide.Client;
import clientSide.GamePanel;
import clientSide.Settings;

import java.util.concurrent.TimeUnit;

public class GameMechanicsProcessor extends Thread implements Runnable {
    private Client client;
    private GamePanel panel;

    public GameMechanicsProcessor(Client client, GamePanel panel) {
        this.client = client;
        this.panel = panel;
    }

    @Override
    public void run() {
        while (true) {


            try {
                TimeUnit.MILLISECONDS.sleep(Settings.MECHANICS_PROC_SLEEP_MILLIS);
            } catch (InterruptedException e) {
                System.err.println("Game mechanics processor thread sleep is interrupted!");
                e.printStackTrace();
            }
        }
    }
}
