package clientSide.graphics;

import clientSide.Client;
import clientSide.GamePanel;
import clientSide.Settings;
import clientSide.attributes.player.Direction;

import java.util.concurrent.TimeUnit;

import packets.Packet;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

public class GraphicsProcessor extends Thread implements Runnable {
    private boolean movingNorth = false;
    private boolean movingEast = false;
    private boolean movingSouth = false;
    private boolean movingWest = false;

    private Client client;
    private GamePanel panel;

    public GraphicsProcessor(Client client, GamePanel panel) {
        this.client = client;
        this.panel = panel;
        FriendlyPlayer.init();
        WorldGround.init();
    }

    @Override
    public void run() {
        while (true /*client.getState() == GameState.RUNNING*/) {
        	while (!client.packetQueue.isEmpty()) {
        		Packet packet = client.packetQueue.poll();
        		
        		if (packet.type == 3)
        			panel.move(Direction.NORTH);
        	}
            
        	/*if (movingNorth) {
                panel.move(Direction.NORTH);
            }

            if (movingWest) {
                panel.move(Direction.WEST);
            }

            if (movingSouth) {
                panel.move(Direction.SOUTH);
            }

            if (movingEast) {
                panel.move(Direction.EAST);
            }*/

            panel.repaint();

            try {
                TimeUnit.MILLISECONDS.sleep(Settings.GRAPHICS_PROC_SLEEP_MILLIS);
            } catch (InterruptedException e) {
                System.err.println("Graphics processor thread sleep is interrupted!");
                e.printStackTrace();
            }
        }
    }

    public synchronized void setMovingNorth(boolean movingNorth) {
        this.movingNorth = movingNorth;
    }

    public synchronized void setMovingEast(boolean movingEast) {
        this.movingEast = movingEast;
    }

    public synchronized void setMovingSouth(boolean movingSouth) {
        this.movingSouth = movingSouth;
    }

    public synchronized void setMovingWest(boolean movingWest) {
        this.movingWest = movingWest;
    }
}
