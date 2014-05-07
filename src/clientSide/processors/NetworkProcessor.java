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
import clientSide.attributes.player.Player;
import clientSide.attributes.world.World;
import packets.CreateEntityPacket;
import packets.Packet;
import packets.PacketType;
import packets.UpdateEntityPacket;

import java.util.concurrent.TimeUnit;

public class NetworkProcessor extends Thread implements Runnable  {
    private Client client;
    private GamePanel panel;

    public NetworkProcessor(Client client, GamePanel panel) {
        this.client = client;
        this.panel = panel;
    }

    @Override
    public void run() {
        while (true) {
            while (!client.packetQueue.isEmpty()) {
                Packet packet = client.packetQueue.poll();

                if (packet.getType() == PacketType.PACKET_CREATEENTITY) {
                    CreateEntityPacket createPacket = (CreateEntityPacket) packet;
                    Player player = new Player(createPacket.x, createPacket.y, createPacket.angle);
                    World.getInstance().addEntity(player, createPacket.entityID);
                }
                if (packet.getType() == PacketType.PACKET_UPDATEENTITY) {
                    UpdateEntityPacket updatePacket = (UpdateEntityPacket) packet;
                    Entity e = World.getInstance().getEntity(updatePacket.entityID);
                    e.update(updatePacket);
                }
            }

            try {
                TimeUnit.MILLISECONDS.sleep(Settings.NETWORK_PROC_SLEEP_MILLIS);
            } catch (InterruptedException e) {
                System.err.println("Network processor thread sleep is interrupted!");
                e.printStackTrace();
            }
        }
    }

}
