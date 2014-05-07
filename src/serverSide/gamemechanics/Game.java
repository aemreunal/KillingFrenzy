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

                if (packet.getType() == 0) {
                    KeyPressPacket pressPacket = (KeyPressPacket) packet;
                    client.keys[pressPacket.key] = true;
                    System.out.println("Key pressed : " + pressPacket.key);
                }

                if (packet.getType() == 1) {
                    KeyReleasePacket releasePacket = (KeyReleasePacket) packet;
                    client.keys[releasePacket.key] = false;
                    System.out.println("Key released : " + releasePacket.key);
                }

                if (packet.getType() == 4) {
                    World.getInstance().addEntity(new Player(client));
                    CreateEntityPacket toSend = new CreateEntityPacket();
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
