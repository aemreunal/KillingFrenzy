package src;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;


public class Server implements Runnable {



	public static void main(String[] args) {
		Server server = new Server();
		new Thread(server).start();
	}
	
	protected Server() {
		clientList = new LinkedList<SelectionKey>();
		readBuffers = new ConcurrentHashMap<SelectionKey, ByteBuffer>();
		clientMap = new ConcurrentHashMap<SelectionKey, Client>();
	}

	private enum State {
		STOPPED, STOPPING, RUNNING
	}

	public static final short PORT = 17000;
	
	private final AtomicReference<State> state = new AtomicReference<State>(State.STOPPED);
	protected ServerSocketChannel serverSocket;
	protected Selector keySelector;
	private LinkedList<SelectionKey> clientList;
	public ConcurrentHashMap<SelectionKey, Client> clientMap;
	private ConcurrentHashMap<SelectionKey, ByteBuffer> readBuffers;
	
	@Override
	public void run() {
		try {
			setupSockets();
			state.set(State.STOPPED);
			while (state.get() == State.RUNNING) {
				runServer();
			}
			closeSockets();
		} 
		catch (Exception e) {
			System.out.println("Server terminated: " + e.getMessage());
		} 
	}

	private void runServer() throws Exception {
		keySelector.select(0);
		for (Iterator<SelectionKey> i = keySelector.selectedKeys().iterator(); i.hasNext();) {
			SelectionKey key = i.next();
			i.remove();

			if (key.isAcceptable()) {
				acceptConnection();
			}
			if (key.isReadable()) {
				receivePackets(key);
			}
		}
		keySelector.selectedKeys().clear();
	}

	private void receivePackets(SelectionKey key) {
		
	}
	
	
	private void acceptConnection() throws IOException, SocketException, ClosedChannelException {
		SocketChannel client = serverSocket.accept();
		client.configureBlocking(false);
		client.socket().setTcpNoDelay(true);
		SelectionKey newkey = client.register(keySelector, SelectionKey.OP_READ);
		readBuffers.put(newkey, ByteBuffer.allocate(20000));
		clientMap.put(newkey, new Client(newkey));
	}
	
	protected void resetKey(SelectionKey key) {
		key.cancel();
		clientMap.remove(key);
		readBuffers.remove(key);
	}

	private void setupSockets() throws IOException, ClosedChannelException {
		keySelector = Selector.open();
		serverSocket = ServerSocketChannel.open();
		serverSocket.socket().bind(new InetSocketAddress(PORT));
		serverSocket.configureBlocking(false);
		serverSocket.register(keySelector, SelectionKey.OP_ACCEPT);
		System.out.println("Server has started");
	}

	private void closeSockets() throws IOException {
		keySelector.close();
		serverSocket.socket().close();
		serverSocket.close();
		System.out.println("Server has been closed.");
	}
}
