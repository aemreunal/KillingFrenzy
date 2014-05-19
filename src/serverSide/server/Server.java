package serverSide.server;

import packets.Packet;
import serverSide.client.Client;
import serverSide.gamemechanics.Game;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class Server implements Runnable {

    public static void main(String[] args) {
        Server server = new Server();
        Game game = new Game(server);
        server.setGame(game);
        new Thread(server).start();
        game.run();
    }

    public Server() {
        readBuffers = new ConcurrentHashMap<>();
        clientMap = new ConcurrentHashMap<>();
    }

    public enum State {
        STOPPED, STOPPING, RUNNING
    }

    public static final short PORT = 17001;
    private final int PACKET_HEADER_BYTES = 2;
    private static short DEFAULT_MESSAGE_SIZE = 20024;


    private final AtomicReference<State> state = new AtomicReference<>(State.STOPPED);


    protected ServerSocketChannel serverSocket;
    protected Selector keySelector;
    public ConcurrentHashMap<SelectionKey, Client> clientMap;
    private ConcurrentHashMap<SelectionKey, ByteBuffer> readBuffers;
    private Game game;

    public AtomicReference<State> getState() {
        return state;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        try {
            setupSockets();
            state.set(State.RUNNING);
            while (state.get() == State.RUNNING) {
                runServer();
            }
            closeSockets();
        } catch (Exception e) {
            System.out.println("Server terminated: " + e.getMessage() + " " + e.getClass());
        }
    }

    private void runServer() throws Exception {
        keySelector.select(0);
        for (Iterator<SelectionKey> i = keySelector.selectedKeys().iterator(); i.hasNext(); ) {
            SelectionKey key = i.next();

            try {
                i.remove();

                if (key.isAcceptable()) {
                    acceptConnection();
                }
                if (key.isReadable()) {
                    receivePackets(key);
                }

            } catch (IOException ex) {
                System.out.println("Client disconnected : " + key);

                resetKey(key);
            }
        }
        keySelector.selectedKeys().clear();
    }

    private void receivePackets(SelectionKey key) throws IOException {
        for (ByteBuffer message : readMessageFromSocket(key)) {
            clientMap.get(key).packetQueue.add(Packet.fromByteArray(message.array()));
        }
    }

    private List<ByteBuffer> readMessageFromSocket(SelectionKey key) throws IOException {
        ByteBuffer readBuffer = readBuffers.get(key);
        if (((ReadableByteChannel) key.channel()).read(readBuffer) == -1)
            throw new IOException("Read on closed key");

        readBuffer.flip();
        List<ByteBuffer> result = new ArrayList<>();
        ByteBuffer msg = readFullMessage(key, readBuffer);
        while (msg != null) {
            result.add(msg);
            msg = readFullMessage(key, readBuffer);
        }

        return result;
    }

    // Keep reading until a meaningful packet is read
    private ByteBuffer readFullMessage(SelectionKey key, ByteBuffer readBuffer) {
        int bytesToRead;
        if (readBuffer.remaining() > PACKET_HEADER_BYTES) {
            byte[] lengthBytes = new byte[PACKET_HEADER_BYTES];
            readBuffer.get(lengthBytes);
            bytesToRead = (int) (((long) (lengthBytes[0] & 0xff) << 8) + (long) (lengthBytes[1] & 0xff));
            if ((readBuffer.limit() - readBuffer.position()) < bytesToRead) {
                if (readBuffer.limit() == readBuffer.capacity()) {
                    int oldCapacity = readBuffer.capacity();
                    ByteBuffer tmp = ByteBuffer.allocate(bytesToRead + PACKET_HEADER_BYTES);
                    readBuffer.position(0);
                    tmp.put(readBuffer);
                    readBuffer = tmp;
                    readBuffer.position(oldCapacity);
                    readBuffer.limit(readBuffer.capacity());
                    readBuffers.put(key, readBuffer);
                    return null;
                } else {
                    readBuffer.position(readBuffer.limit());
                    readBuffer.limit(readBuffer.capacity());
                    return null;
                }
            }
        } else {
            readBuffer.position(readBuffer.limit());
            readBuffer.limit(readBuffer.capacity());
            return null;
        }
        byte[] resultMessage = new byte[bytesToRead];
        readBuffer.get(resultMessage, 0, bytesToRead);
        int remaining = readBuffer.remaining();
        readBuffer.limit(readBuffer.capacity());
        readBuffer.compact();
        readBuffer.position(0);
        readBuffer.limit(remaining);
        return ByteBuffer.wrap(resultMessage);
    }


    public synchronized void sendPacket(SelectionKey channelKey, Packet pk) {
        byte[] buffer = pk.toByteArray();
        short len = (short) buffer.length;
        byte[] lengthBytes = new byte[]{(byte) ((len >>> 8) & 0xff), (byte) (len & 0xff)};
        ByteBuffer writeBuffer = ByteBuffer.allocate(len + lengthBytes.length);
        writeBuffer.put(lengthBytes);
        writeBuffer.put(buffer);
        writeBuffer.flip();
        if (buffer != null && state.get() == State.RUNNING) {
            try {
                forceSendPacket(channelKey, writeBuffer);
            } catch (Exception e) {
                resetKey(channelKey);
            }
        }
    }

    private void forceSendPacket(SelectionKey channelKey, ByteBuffer writeBuffer) throws IOException, InterruptedException {
        int bytesWritten;
        SocketChannel channel = (SocketChannel) channelKey.channel();
        while (writeBuffer.remaining() > 0) {
            bytesWritten = channel.write(writeBuffer);
            if (bytesWritten == -1) {
                resetKey(channelKey);
            }

            if (bytesWritten == 0)
                Thread.sleep(5);
        }
    }

    public void broadcast(Packet pk) {
        for (Entry<SelectionKey, Client> o : clientMap.entrySet()) {
            sendPacket(o.getKey(), pk);
        }
    }

    public void broadcast(Packet pk, Client except) {
        for (Entry<SelectionKey, Client> o : clientMap.entrySet()) {
            if (o.getValue() != except)
                sendPacket(o.getKey(), pk);
        }
    }

    private void acceptConnection() throws IOException {
        SocketChannel clientSocket = serverSocket.accept();
        clientSocket.configureBlocking(false);
        clientSocket.socket().setTcpNoDelay(true);

        SelectionKey newkey = clientSocket.register(keySelector, SelectionKey.OP_READ);
        readBuffers.put(newkey, ByteBuffer.allocate(DEFAULT_MESSAGE_SIZE));
        Client client = new Client(newkey, this);
        clientMap.put(newkey, client);
        game.clients.add(client);
        System.out.println("New connection: " + newkey);
    }

    protected void resetKey(SelectionKey key) {
        key.cancel();
        Client client = clientMap.get(key);
        game.disconnectClient(client);
        clientMap.remove(key);
        readBuffers.remove(key);
    }

    private void setupSockets() throws IOException {
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
