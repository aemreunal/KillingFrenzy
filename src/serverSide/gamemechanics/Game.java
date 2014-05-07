package serverSide.gamemechanics;

import packets.*;
import serverSide.client.Client;
import serverSide.server.Server;

import java.awt.event.KeyEvent;
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
                    client.setPlayer(player);
                    player.physicalAttributes = new PhysicalAttributes((float) Math.random() * 100.0f, (float) Math.random() * 100.0f, 10.0f, 10.0f);
                    World.getInstance().addEntity(player);
                    CreateEntityPacket toSend = new CreateEntityPacket();
                    toSend.x = player.physicalAttributes.left;
                    toSend.y = player.physicalAttributes.top;
                    toSend.angle = 10.0f;
                    toSend.entityID = player.getId();
                    toSend.entityType = EntityType.ENTITY_PLAYER;
                    toSend.isMine = true;
                    client.sendPacket(toSend);
                    toSend.isMine = false;
                    server.broadcast(toSend, client);
                }
                
                
            }
            
            if (client.keys[KeyEvent.VK_A]) {
                if (client.player != null) {
                    client.player.physicalAttributes.left -= 0.5f;
                } 
            }
            
            if (client.keys[KeyEvent.VK_D]) {
                if (client.player != null) {
                    client.player.physicalAttributes.left += 0.5f;
                } 
            }
            
            if (client.keys[KeyEvent.VK_W]) {
                if (client.player != null) {
                    client.player.physicalAttributes.top -= 0.5f;
                } 
            }
            
            if (client.keys[KeyEvent.VK_S]) {
                if (client.player != null) {
                    client.player.physicalAttributes.top += 0.5f;
                } 
            }
            
            if (client.player != null) {
                UpdateEntityPacket updateEntity = new UpdateEntityPacket(client.player.physicalAttributes.left, client.player.physicalAttributes.top, client.player.physicalAttributes.angle);
                updateEntity.entityID = client.player.getId();
                server.broadcast(updateEntity);
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
