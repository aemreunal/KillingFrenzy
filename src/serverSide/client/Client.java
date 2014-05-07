package serverSide.client;

import packets.Packet;
import serverSide.gamemechanics.Player;
import serverSide.server.Server;

import java.nio.channels.SelectionKey;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Client {
    private Server server;
    private SelectionKey socket;
    public Queue<Packet> packetQueue;
    public Player player;

    public boolean keys[];

    public Client(SelectionKey key, Server server) {
        this.server = server;
        socket = key;
        packetQueue = new ConcurrentLinkedQueue<Packet>();
        keys = new boolean[256];
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void sendPacket(Packet pk) {
        server.sendPacket(socket, pk);
    }
}
