package serverSide.client;

import packets.Packet;
import serverSide.gamemechanics.Player;
import serverSide.server.Server;

import java.nio.channels.SelectionKey;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Client {
    public Queue<Packet> packetQueue;
    public Player player;
    public boolean keys[];
    private Server server;
    private SelectionKey socket;

    public Client(SelectionKey key, Server server) {
        this.server = server;
        socket = key;
        packetQueue = new ConcurrentLinkedQueue<Packet>();
        keys = new boolean[256];
    }

    public SelectionKey getSocket() {
        return socket;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void sendPacket(Packet pk) {
        server.sendPacket(socket, pk);
    }

    public Server getServer() {
        return server;
    }
}
