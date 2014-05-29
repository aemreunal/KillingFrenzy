package clientSide;

import clientSide.gui.GamePanel;
import clientSide.gui.GameWindow;
import clientSide.gui.MenuWindow;
import clientSide.processors.GameMechanicsProcessor;
import clientSide.processors.GraphicsProcessor;
import clientSide.processors.SyncProcessor;
import global.GameState;
import global.Settings;
import packets.JoinGamePacket;
import packets.Packet;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.*;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * Eren Sezener
 * Deniz Sokmen
 * Erdi Gultekin
 */

public class Client extends Thread implements Runnable {
    public static InetAddress IP;
    private final AtomicReference<GameState> state = new AtomicReference<>(GameState.STOPPED);
    private SocketChannel socketChannel;
    private ByteBuffer receiveBuffer;
    private Queue<Packet> packetQueue;
    private JFrame menuWindow;
    private JFrame gameWindow;
    private GamePanel gamePanel;
    private GameMechanicsProcessor gameProcessor;
    private GraphicsProcessor graphicsProcessor;
    private SyncProcessor syncProcessor;
    private String ipAddr;

    private boolean allowOffline = true;

    public Client() {
        packetQueue = new ConcurrentLinkedQueue<>();
        showGameMenu();
    }

    public static void main(String[] args) {
        new Client();
    }

    private void showGameMenu() {
        // Create components
        menuWindow = new MenuWindow(this);
        menuWindow.setVisible(true);
    }

    public void createGame(String ipAddr) {
        this.ipAddr = ipAddr;
        start();
        sendPacket(new JoinGamePacket());
        createGameComponents();
    }

    private void createGameComponents() {
        this.gamePanel = new GamePanel(this);
        this.gameWindow = new GameWindow(this, gamePanel);
        this.graphicsProcessor = new GraphicsProcessor(this, gamePanel);
        this.graphicsProcessor.start();
        this.gameProcessor = new GameMechanicsProcessor(this, gamePanel);
        this.gameProcessor.start();
        this.syncProcessor = new SyncProcessor(this);
        this.syncProcessor.start();
    }

    @Override
    public void run() {
        state.set(GameState.RUNNING);
        createSocket();
        while (state.get() == GameState.RUNNING) {
            List<ByteBuffer> byteBufferList = readIncomingMessage(socketChannel);
            if (byteBufferList != null) {
                for (ByteBuffer message : byteBufferList) {
                    if (message != null) {
                        packetQueue.add(Packet.fromByteArray(message.array()));
                    }
                }
            }
        }
    }

    private void createSocket() {
        boolean errorOccurred = false;
        try {
            IP = InetAddress.getByName(ipAddr);
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(true);
            socketChannel.connect(new InetSocketAddress(IP, Settings.PORT));
            socketChannel.socket().setTcpNoDelay(true);
        } catch (SocketException e) {
            System.err.println("An error occurred when trying to interact with the socket!");
            errorOccurred = true;
        } catch (UnknownHostException e) {
            System.err.println("An error occurred when trying to get the IP address of the host!");
            errorOccurred = true;
        } catch (IOException e) {
            System.err.println("An error occurred when trying to open the socket!");
            errorOccurred = true;
        } finally {
            if (errorOccurred && !allowOffline) {
                System.exit(-1);
            }
        }
        receiveBuffer = ByteBuffer.allocate(1000);
    }

    private List<ByteBuffer> readIncomingMessage(SocketChannel sock) {
        try {
            if (sock.read(receiveBuffer) == -1) {
                throw new IOException("Receive error");
            }
            receiveBuffer.flip();
            List<ByteBuffer> receivedPackets = new ArrayList<>();
            ByteBuffer message = readMessage(receiveBuffer);
            while (message != null) {
                receivedPackets.add(message);
                message = readMessage(receiveBuffer);
            }
            return receivedPackets;
        } catch (IOException e) {
            return null;
        }
    }

    private ByteBuffer readMessage(ByteBuffer readBuffer) {
        int bytesToRead;
        if (readBuffer.remaining() > Settings.PACKET_HEADER_BYTES) {
            byte[] lengthBytes = new byte[Settings.PACKET_HEADER_BYTES];
            readBuffer.get(lengthBytes);
            bytesToRead = (int) (((long) (lengthBytes[0] & 0xff) << 8) + (long) (lengthBytes[1] & 0xff));

            if ((readBuffer.limit() - readBuffer.position()) < bytesToRead) {
                if (readBuffer.limit() == readBuffer.capacity()) {
                    int oldCapacity = readBuffer.capacity();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(bytesToRead + Settings.PACKET_HEADER_BYTES);
                    readBuffer.position(0);
                    byteBuffer.put(readBuffer);
                    readBuffer = byteBuffer;
                    readBuffer.position(oldCapacity);
                    readBuffer.limit(readBuffer.capacity());
                    receiveBuffer = readBuffer;
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

    public synchronized boolean sendPacket(Packet pk) {
        byte[] bufferByteArray = pk.toByteArray();
        int bufferLength = bufferByteArray.length;
        byte[] lengthBytes = new byte[] { (byte) ((bufferLength >>> 8) & 0xff), (byte) (bufferLength & 0xff) };
        try {
            byte[] outBuffer = new byte[bufferLength + Settings.PACKET_HEADER_BYTES];
            System.arraycopy(lengthBytes, 0, outBuffer, 0, Settings.PACKET_HEADER_BYTES);
            System.arraycopy(bufferByteArray, 0, outBuffer, Settings.PACKET_HEADER_BYTES, bufferLength);
            socketChannel.write(ByteBuffer.wrap(outBuffer));
            return true;
        } catch (Exception e) {
            stopGame();
            return false;
        }
    }

    public boolean stopGame() {
        return state.compareAndSet(GameState.RUNNING, GameState.STOPPING);
    }

    public Queue<Packet> getPacketQueue() {
        return packetQueue;
    }
}
