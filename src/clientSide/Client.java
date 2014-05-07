package clientSide;

import packets.KeyPressPacket;
import packets.Packet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;


public class Client implements Runnable {
    private static Client thisClient;
    private JFrame menuWindow;
    private JPanel p1;

    public static void main(String[] args) {
        thisClient = new Client();
        new Thread(thisClient).start();
    }

    private final int PACKET_HEADER_BYTES = 2;
    public static final short PORT = 17000;


    private final AtomicReference<GameState> state = new AtomicReference<>(GameState.STOPPED);
    public SocketChannel socketChannel;
    public static InetAddress IP;
    public ByteBuffer receiveBuffer;
    public Queue<Packet> packetQueue;

    public Client() {
        packetQueue = new ConcurrentLinkedQueue<>();
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        menuWindow = new JFrame();
        menuWindow.setTitle("Killing Frenzy");
        menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setLayout(new FlowLayout());
        p1 = new JPanel();
        p1.setPreferredSize(new Dimension(500, 500));
        p1.setBackground(Color.GRAY);
        p1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5, true));

        JButton okButton = new JButton("JOIN THE MADNESS");
        p1.add(okButton);
        menuWindow.add(p1);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendPacket(new KeyPressPacket());
                new GameClientUIManager(thisClient);
            }
        });

        menuWindow.setSize(500, 500);
        menuWindow.setVisible(true);
        menuWindow.pack();
    }

    @Override
    public void run() {
        state.set(GameState.RUNNING);
        try {
            createSocket();
            while (state.get() == GameState.RUNNING) {
                for (ByteBuffer message : readIncomingMessage(socketChannel)) {
                    packetQueue.add(Packet.fromByteArray(message.array()));
                    //dispatchMessage(message)
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public GameState getState() {
        return state.get();
    }


    private List<ByteBuffer> readIncomingMessage(SocketChannel sock) throws IOException {
        if (sock.read(receiveBuffer) == -1)
            throw new IOException("Receive error");
        receiveBuffer.flip();

        List<ByteBuffer> receivedPackets = new ArrayList<>();
        ByteBuffer msg = readMessage(receiveBuffer);
        while (msg != null) {
            receivedPackets.add(msg);
            msg = readMessage(receiveBuffer);
        }

        return receivedPackets;
    }


    private ByteBuffer readMessage(ByteBuffer readBuffer) {
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
            byte[] outBuffer = new byte[len + PACKET_HEADER_BYTES];
            System.arraycopy(lengthBytes, 0, outBuffer, 0, PACKET_HEADER_BYTES);
            System.arraycopy(buffer, 0, outBuffer, PACKET_HEADER_BYTES, len);
            socketChannel.write(ByteBuffer.wrap(outBuffer));
            return true;
        } catch (Exception e) {
            stop();
            return false;
        }
    }

    public boolean stop() {
        return state.compareAndSet(GameState.RUNNING, GameState.STOPPING);
    }

    private void createSocket() throws IOException {
        IP = InetAddress.getByName("localhost");
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(true);
        socketChannel.connect(new InetSocketAddress(IP, PORT));
        socketChannel.socket().setTcpNoDelay(true);
        receiveBuffer = ByteBuffer.allocate(1000);
    }

}
