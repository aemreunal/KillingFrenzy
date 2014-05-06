package serverSide.client;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import packets.Packet;
import serverSide.server.Server;

public class Client {
	private Server server;
	private SelectionKey socket;
	public Queue<Packet> packetQueue;
	
	public boolean keys[];
	
	public Client(SelectionKey key, Server server) {
		socket = key;
		packetQueue = new ConcurrentLinkedQueue<Packet>();
		keys = new boolean[256];
	}
	
	public void sendPacket(Packet pk) {
		server.sendPacket(socket, pk);
	}
}
