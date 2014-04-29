package server;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Client {
	private SelectionKey socket;
	public Queue<ByteBuffer> packetQueue = new ConcurrentLinkedQueue<ByteBuffer>();
	
	public Client(SelectionKey key) {
		socket = key;
	}
}
