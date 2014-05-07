package serverSide.gamemechanics;

import packets.*;
import serverSide.client.Client;
import serverSide.server.Server;

import java.util.concurrent.CopyOnWriteArrayList;

public class Game {
    private Server server;
    public CopyOnWriteArrayList<Client> clients;
    private GameLogic gameLogic;

    public Game(Server server) {
        this.server = server;
        clients = new CopyOnWriteArrayList<Client>();
    }

    public void updateClients() {
        for (Client client : clients) {
            if (!client.packetQueue.isEmpty()) {
                Packet packet = client.packetQueue.poll();

                if (packet.getType() == PacketType.PACKET_KEYPRESS) {
                    KeyPressPacket pressPacket = (KeyPressPacket) packet;
                    client.keys[pressPacket.key] = true;
                    System.out.println("Key pressed : " + pressPacket.key);
                }

                if (packet.getType() == PacketType.PACKET_KEYRELEASE) {
                    KeyReleasePacket releasePacket = (KeyReleasePacket) packet;
                    client.keys[releasePacket.key] = false;
                    System.out.println("Key released : " + releasePacket.key);
                }

                if (packet.getType() == PacketType.PACKET_JOINGAME) {
                    Player player = new Player(client);
                    World.getInstance().addEntity(player);
                    CreateEntityPacket toSend = new CreateEntityPacket();
                    toSend.entityID = player.getId();
                    toSend.entityType = 0;
                    server.broadcast(toSend);
                }
            }

            if (client.keys[68]) {
                server.broadcast(new UpdateEntityPacket());
            }
        }
    }

    public void run() {
        while (true) {
            updateClients();

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
