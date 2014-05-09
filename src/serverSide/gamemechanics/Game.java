package serverSide.gamemechanics;

import packets.Packet;
import packets.PacketType;
import packets.UpdateEntityPacket;
import serverSide.client.Client;
import serverSide.packethandlers.JoinGameHandler;
import serverSide.packethandlers.KeyPressHandler;
import serverSide.packethandlers.KeyReleaseHandler;
import serverSide.packethandlers.PacketHandler;
import serverSide.server.Server;

import java.util.EnumMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game {
    private Server server;
    public CopyOnWriteArrayList<Client> clients;
    private GameLogic gameLogic;
    private EnumMap<PacketType, PacketHandler> packetHandlerMap;

    public Game(Server server) {
        this.server = server;
        gameLogic = new GameLogic();
        clients = new CopyOnWriteArrayList<Client>();
        packetHandlerMap = new EnumMap<PacketType, PacketHandler>(PacketType.class);

        registerPacketHandler(PacketType.PACKET_KEYPRESS, new KeyPressHandler());
        registerPacketHandler(PacketType.PACKET_KEYRELEASE, new KeyReleaseHandler());
        registerPacketHandler(PacketType.PACKET_JOINGAME, new JoinGameHandler(server));
    }

    public void registerPacketHandler(PacketType type, PacketHandler handler) {
        packetHandlerMap.put(type, handler);
    }

    public void updateClients() {
        for (Client client : clients) {
            if (!client.packetQueue.isEmpty()) {
                Packet packet = client.packetQueue.poll();
                packetHandlerMap.get(packet.getType()).handle(client, packet);
            }

            /*if (client.player != null) {
                UpdateEntityPacket updateEntity = new UpdateEntityPacket(client.player.physicalAttributes.left, client.player.physicalAttributes.top, client.player.physicalAttributes.angle, false);
                updateEntity.entityID = client.player.getId();
                server.broadcast(updateEntity);
            }*/

        }
    }

    public void run() {
        while (true) {
            updateClients();



            gameLogic.update();
            // UPDATE LOGIC
            // BROADCAST RESULTS
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
