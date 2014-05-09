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
import clientSide.attributes.Entity;
import clientSide.attributes.world.World;
import packets.CreateEntityPacket;
import packets.Packet;
import packets.UpdateEntityPacket;

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
            while (!client.packetQueue.isEmpty()) {
                Packet packet = client.packetQueue.poll();

                switch (packet.getType()) {
                    case PACKET_CREATEENTITY:
                        createEntity((CreateEntityPacket) packet);
                        break;
                    case PACKET_UPDATEENTITY:
                        updateEntity((UpdateEntityPacket) packet);
                        break;
                    case PACKET_JOINGAME:
                        break;
                    case PACKET_KEYPRESS:
                        break;
                    case PACKET_KEYRELEASE:
                        break;
                    default:
                        break;
                }
            }

            try {
                TimeUnit.MILLISECONDS.sleep(Settings.MECHANICS_PROC_SLEEP_MILLIS);
            } catch (InterruptedException e) {
                System.err.println("Game mechanics processor thread sleep is interrupted!");
                e.printStackTrace();
            }
        }
    }

    private void createEntity(CreateEntityPacket packet) {
//        Player player = new Player(packet.x, packet.y, packet.angle);
//        World.getInstance().addEntity(player, packet.entityID);
    }

    private void updateEntity(UpdateEntityPacket packet) {
        Entity e = World.getInstance().getEntity(packet.entityID);
        e.update(packet);
    }
}
