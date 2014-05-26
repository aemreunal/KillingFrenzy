package serverSide.gamemechanics;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import packets.Packet;
import serverSide.client.Client;
import serverSide.packethandlers.*;
import serverSide.server.Server;
import global.PacketType;

public class Game {
    public CopyOnWriteArrayList<Client> clients;
    private GameLogic gameLogic;
    private EnumMap<PacketType, PacketHandler> packetHandlerMap;

    public Game(Server server) {
        gameLogic = new GameLogic(server);
        clients = new CopyOnWriteArrayList<>();
        initPacketHandlers(server);
    }

    public synchronized void disconnectClient(Client client) {
        client.player.setAlive(false);
        clients.remove(client);
    }

    private void initPacketHandlers(Server server) {
        packetHandlerMap = new EnumMap<>(PacketType.class);

        packetHandlerMap.put(PacketType.PACKET_KEY_PRESS, new KeyPressHandler());
        packetHandlerMap.put(PacketType.PACKET_KEY_RELEASE, new KeyReleaseHandler());
        packetHandlerMap.put(PacketType.PACKET_JOIN_GAME, new JoinGameHandler(server));
        packetHandlerMap.put(PacketType.PACKET_ANGLE_UPDATE, new AngleUpdateHandler());
        packetHandlerMap.put(PacketType.PACKET_BULLET_SHOT, new BulletShotHandler());
    }

    public void run() {
        while (true) {
            updateClients();
            gameLogic.update();
            // BROADCAST RESULTS
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateClients() {
        Iterator<Client> clientIterator = clients.iterator();
        while (clientIterator.hasNext()) {
            Client client = clientIterator.next();
            if (!client.getSocket().isValid()) {
                clientIterator.remove();
            }

            if (!client.packetQueue.isEmpty()) {
                Packet packet = client.packetQueue.poll();
                packetHandlerMap.get(packet.getType()).handle(client, packet);
            }
        }
    }

}
