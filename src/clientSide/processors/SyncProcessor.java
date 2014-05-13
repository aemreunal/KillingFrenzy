package clientSide.processors;
/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

import clientSide.Client;
import global.Settings;
import clientSide.attributes.Player;
import clientSide.attributes.World;
import packets.AngleUpdatePacket;

import java.util.concurrent.TimeUnit;

public class SyncProcessor extends Thread implements Runnable {
    private Client client;
    private float lastAngle;

    public SyncProcessor(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (true) {
            sendUpdates();
            try {
                TimeUnit.MILLISECONDS.sleep(Settings.SYNC_PROC_SLEEP_MILLIS);
            } catch (InterruptedException e) {
                System.err.println("Sync processor thread sleep is interrupted!");
                e.printStackTrace();
            }
        }
    }

    private void sendUpdates() {
        sendPlayerAngleUpdate();
    }

    private void sendPlayerAngleUpdate() {
        Player thisPlayer = World.getThisPlayer();
        if (thisPlayer != null) {
            float angle = thisPlayer.getAngle();
            if (lastAngle != angle) {
                client.sendPacket(new AngleUpdatePacket(thisPlayer.getAngle()));
                lastAngle = angle;
            }
        }
    }
}
