package clientSide;

import clientSide.controllerHandlers.KeyboardHandler;
import clientSide.processors.GameMechanicsProcessor;
import clientSide.processors.GraphicsProcessor;
import packets.JoinGamePacket;
import packets.Packet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Client extends Thread implements Runnable {
    private static Client client;

    private JFrame menuWindow;
    private JPanel menuPanel;

    private JFrame gameWindow;
    private GamePanel gamePanel;

    private GameMechanicsProcessor gameProcessor;
    private GraphicsProcessor graphicsProcessor;

    private final AtomicReference<GameState> state = new AtomicReference<>(GameState.STOPPED);
    public SocketChannel socketChannel;
    public static InetAddress IP;
    public ByteBuffer receiveBuffer;
    public Queue<Packet> packetQueue;

    private boolean allowOffline = true;

    public static void main(String[] args) {
        client = new Client();
        client.start();
    }

    public Client() {
        packetQueue = new ConcurrentLinkedQueue<>();
        createGameMenu();
        showGameMenu();
    }

    private void createGameMenu() {
        // Create components
        menuWindow = new JFrame();
        menuPanel = new JPanel();
        createJoinButton();
        // Set attributes
        setMenuPanelAttributes();
        setMenuWindowAttributes();
    }

    private void showGameMenu() {
        menuWindow.setVisible(true);
    }

    private void createJoinButton() {
        JButton okButton = new JButton("JOIN THE MADNESS");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendPacket(new JoinGamePacket());
                createGame();
            }
        });

        menuPanel.add(okButton);
    }

    private void setMenuPanelAttributes() {
        menuPanel.setPreferredSize(new Dimension(500, 500));
        menuPanel.setBackground(Color.GRAY);
        menuPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5, true));
    }

    private void setMenuWindowAttributes() {
        menuWindow.setTitle("Killing Frenzy");
        menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuWindow.add(menuPanel);
        menuWindow.setSize(500, 500);
        menuWindow.pack();
    }

    private void createGame() {
        createGameComponents();
        addGameListeners();
        setGameAttributes();
    }

    private void createGameComponents() {
        this.gameWindow = new JFrame("GO GO");
        this.gamePanel = new GamePanel();
        this.graphicsProcessor = new GraphicsProcessor(client, gamePanel);
        this.graphicsProcessor.start();
        this.gameProcessor = new GameMechanicsProcessor(client, gamePanel);
        this.gameProcessor.start();
    }

    private void addGameListeners() {
        gameWindow.addKeyListener(new KeyboardHandler(client, graphicsProcessor));
    }

    private void setGameAttributes() {
        gameWindow.getContentPane().add(gamePanel);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setSize(Settings.GAME_WINDOW_WIDTH, Settings.GAME_WINDOW_HEIGHT + gameWindow.getBounds().y);
        gameWindow.setVisible(true);
        gameWindow.setLocationRelativeTo(null);
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
                        //dispatchMessage(message)
                    }
                }
            }
        }
    }

    private void createSocket() {
        try {
            IP = InetAddress.getByName("localhost");
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(true);
            socketChannel.connect(new InetSocketAddress(IP, Settings.PORT));
            socketChannel.socket().setTcpNoDelay(true);
        } catch (SocketException e) {
            System.err.println("An error occurred when trying to interact with the socket!");
//            e.printStackTrace();
            if (!allowOffline) {
                System.exit(-1);
            }
        } catch (UnknownHostException e) {
            System.err.println("An error occurred when trying to get the IP address of the host!");
//            e.printStackTrace();
            if (!allowOffline) {
                System.exit(-1);
            }
        } catch (IOException e) {
            System.err.println("An error occurred when trying to open the socket!");
//            e.printStackTrace();
            if (!allowOffline) {
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
            ByteBuffer msg = readMessage(receiveBuffer);
            while (msg != null) {
                receivedPackets.add(msg);
                msg = readMessage(receiveBuffer);
            }
            return receivedPackets;
        } catch (IOException e) {
//            System.err.println("An error occurred while trying to read incoming message!");
            return null;
//            e.printStackTrace();
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
                    ByteBuffer tmp = ByteBuffer.allocate(bytesToRead + Settings.PACKET_HEADER_BYTES);
                    readBuffer.position(0);
                    tmp.put(readBuffer);
                    readBuffer = tmp;
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
        byte[] buffer = pk.toByteArray();
        int len = buffer.length;
        byte[] lengthBytes = new byte[]{(byte) ((len >>> 8) & 0xff), (byte) (len & 0xff)};
        try {
            byte[] outBuffer = new byte[len + Settings.PACKET_HEADER_BYTES];
            System.arraycopy(lengthBytes, 0, outBuffer, 0, Settings.PACKET_HEADER_BYTES);
            System.arraycopy(buffer, 0, outBuffer, Settings.PACKET_HEADER_BYTES, len);
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
}
