package serverSide.client;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import packets.Packet;

public class Client {
	private SelectionKey socket;
	public Queue<Packet> packetQueue;
	
	public Client(SelectionKey key) {
		socket = key;
		packetQueue = new ConcurrentLinkedQueue<Packet>();
	}
}
