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
import clientSide.packethandlers.CreateEntityHandler;
import clientSide.packethandlers.DestroyEntityHandler;
import clientSide.packethandlers.PacketHandler;
import clientSide.packethandlers.UpdateEntityHandler;
import packets.CreateEntityPacket;
import packets.Packet;
import packets.PacketType;
import packets.UpdateEntityPacket;

import java.util.EnumMap;
import java.util.concurrent.TimeUnit;

public class GameMechanicsProcessor extends Thread implements Runnable {
    private Client client;
    private GamePanel panel;
    private EnumMap<PacketType, PacketHandler> packetHandlerMap;

    public GameMechanicsProcessor(Client client, GamePanel panel) {
        this.client = client;
        this.panel = panel;
        packetHandlerMap = new EnumMap<PacketType, PacketHandler>(PacketType.class);

        registerPacketHandler(PacketType.PACKET_CREATEENTITY, new CreateEntityHandler(this));
        registerPacketHandler(PacketType.PACKET_UPDATEENTITY, new UpdateEntityHandler(this));
        registerPacketHandler(PacketType.PACKET_DESTROYENTITY, new DestroyEntityHandler());
    }

    public void registerPacketHandler(PacketType type, PacketHandler handler) {
        packetHandlerMap.put(type, handler);
    }

    @Override
    public void run() {
        while (true) {
            while (!client.packetQueue.isEmpty()) {
                Packet packet = client.packetQueue.poll();
                packetHandlerMap.get(packet.getType()).handle(packet);
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

    public void updateEntity(UpdateEntityPacket packet) {
        Entity e = World.getInstance().getEntity(packet.entityID);
        if (e != null)
            e.update(packet);
    }
}
