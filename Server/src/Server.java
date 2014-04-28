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
import java.util.concurrent.atomic.AtomicReference;

public class Server implements Runnable {

	

	public static void main(String[] args) {

		System.out.println("deneme");
	}

	private enum State {
		STOPPED, STOPPING, RUNNING
	}
	
	public static final short PORT = 17000;
	private final AtomicReference<State> state = new AtomicReference<State>(State.STOPPED);

	protected ServerSocketChannel serverSocket;
	protected Selector keySelector;
	private LinkedList<SelectionKey> clientList;

	@Override
	public void run() {
		try {
			setupSockets();
			
			while (state.get() == State.RUNNING) {
				runServer();
			}
			
		} catch (Exception e) {
			System.out.println("Server terminated: " + e.getMessage());
		} finally {
			closeSockets();
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
				// READ PACKET ETC.
			}
		}
		
	}

	private void acceptConnection() throws IOException, SocketException,
			ClosedChannelException {
		SocketChannel client = serverSocket.accept();
		client.configureBlocking(false);
		client.socket().setTcpNoDelay(true);
		SelectionKey newkey = client.register(keySelector, SelectionKey.OP_READ);
		
		synchronized (clientList) {
			clientList.add(newkey);
		}
	}

	private void setupSockets() throws IOException, ClosedChannelException {
		keySelector = Selector.open();
		serverSocket = ServerSocketChannel.open();
		serverSocket.socket().bind(new InetSocketAddress(PORT));
		serverSocket.configureBlocking(false);
		serverSocket.register(keySelector, SelectionKey.OP_ACCEPT);
	}

	private void closeSockets() {
		try {
			keySelector.close();
			serverSocket.socket().close();
			serverSocket.close();
		} catch (Exception ex) {
			System.out.println("Error closing sockets: "+ ex.getMessage());
		}
	}
}
