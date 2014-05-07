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
                    for (Entity e : World.getInstance().idToEntityMap.values()) {
                        CreateEntityPacket toSend = new CreateEntityPacket();
                        toSend.x = e.physicalAttributes.left;
                        toSend.y = e.physicalAttributes.top;
                        toSend.angle = 10.0f;
                        toSend.entityID = e.getId();
                        toSend.entityType = EntityType.ENTITY_PLAYER;
                        client.sendPacket(toSend);
                    }
                    
                    Player player = new Player(client);
                    player.physicalAttributes = new PhysicalAttributes((float) Math.random() * 100.0f, (float) Math.random() * 100.0f, 10.0f, 10.0f);
                    World.getInstance().addEntity(player);
                    CreateEntityPacket toSend = new CreateEntityPacket();
                    toSend.x = player.physicalAttributes.left;
                    toSend.y = player.physicalAttributes.top;
                    toSend.angle = 10.0f;
                    toSend.entityID = player.getId();
                    toSend.entityType = EntityType.ENTITY_PLAYER;
                    server.broadcast(toSend);
                }
            }

            /*if (client.keys[68]) {
                server.broadcast(new UpdateEntityPacket());
            }*/
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
