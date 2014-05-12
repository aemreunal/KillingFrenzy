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
import packets.packetHandlers.CreateEntityHandler;
import packets.packetHandlers.DestroyEntityHandler;
import packets.packetHandlers.PacketHandler;
import packets.packetHandlers.UpdateEntityHandler;
import packets.Packet;
import packets.PacketType;

import java.util.EnumMap;
import java.util.concurrent.TimeUnit;

public class GameMechanicsProcessor extends Thread implements Runnable {
    private Client client;
    private GamePanel panel;
    private EnumMap<PacketType, PacketHandler> packetHandlerMap;

    public GameMechanicsProcessor(Client client, GamePanel panel) {
        this.client = client;
        this.panel = panel;
        packetHandlerMap = new EnumMap<>(PacketType.class);

        registerPacketHandler(PacketType.PACKET_CREATEENTITY, new CreateEntityHandler());
        registerPacketHandler(PacketType.PACKET_UPDATEENTITY, new UpdateEntityHandler());
        registerPacketHandler(PacketType.PACKET_DESTROYENTITY, new DestroyEntityHandler());
    }

    public void registerPacketHandler(PacketType type, PacketHandler handler) {
        packetHandlerMap.put(type, handler);
    }

    @Override
    public void run() {
        while (true) {
            receiveUpdates();
            try {
                TimeUnit.MILLISECONDS.sleep(Settings.MECHANICS_PROC_SLEEP_MILLIS);
            } catch (InterruptedException e) {
                System.err.println("Game mechanics processor thread sleep is interrupted!");
                e.printStackTrace();
            }
        }
    }

    private void receiveUpdates() {
        while (!client.packetQueue.isEmpty()) {
            Packet packet = client.packetQueue.poll();
            packetHandlerMap.get(packet.getType()).handle(packet);
        }
    }
}
