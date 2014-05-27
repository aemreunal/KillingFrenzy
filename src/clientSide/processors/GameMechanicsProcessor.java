package clientSide.processors;

import clientSide.Client;
import clientSide.gui.GamePanel;
import clientSide.packetHandlers.CreateEntityHandler;
import clientSide.packetHandlers.DestroyEntityHandler;
import clientSide.packetHandlers.PacketHandler;
import clientSide.packetHandlers.UpdateEntityHandler;
import global.PacketType;
import global.Settings;
import packets.Packet;

import java.util.EnumMap;
import java.util.concurrent.TimeUnit;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * Eren Sezener
 * Deniz Sokmen
 * Erdi Gultekin
 */

public class GameMechanicsProcessor extends Thread implements Runnable {
    private Client client;
    private GamePanel panel;
    private EnumMap<PacketType, PacketHandler> packetHandlerMap;

    public GameMechanicsProcessor(Client client, GamePanel panel) {
        this.client = client;
        this.panel = panel;
        packetHandlerMap = new EnumMap<>(PacketType.class);

        registerPacketHandler(PacketType.PACKET_CREATE_ENTITY, new CreateEntityHandler());
        registerPacketHandler(PacketType.PACKET_UPDATE_ENTITY, new UpdateEntityHandler());
        registerPacketHandler(PacketType.PACKET_DESTROY_ENTITY, new DestroyEntityHandler());
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
