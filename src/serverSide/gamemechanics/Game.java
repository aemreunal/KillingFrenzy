package serverSide.gamemechanics;

import packets.Packet;
import packets.PacketType;
import packets.UpdateEntityPacket;
import serverSide.client.Client;
import serverSide.packethandlers.AngleUpdateHandler;
import serverSide.packethandlers.JoinGameHandler;
import serverSide.packethandlers.KeyPressHandler;
import serverSide.packethandlers.KeyReleaseHandler;
import serverSide.packethandlers.PacketHandler;
import serverSide.server.Server;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game {
    private Server server;
    public CopyOnWriteArrayList<Client> clients;
    private GameLogic gameLogic;
    private EnumMap<PacketType, PacketHandler> packetHandlerMap;

    public Game(Server server) {
        this.server = server;
        gameLogic = new GameLogic();
        clients = new CopyOnWriteArrayList<Client>();
        packetHandlerMap = new EnumMap<PacketType, PacketHandler>(PacketType.class);

        registerPacketHandler(PacketType.PACKET_KEYPRESS, new KeyPressHandler());
        registerPacketHandler(PacketType.PACKET_KEYRELEASE, new KeyReleaseHandler());
        registerPacketHandler(PacketType.PACKET_JOINGAME, new JoinGameHandler(server));
        registerPacketHandler(PacketType.PACKET_ANGLEUPDATE, new AngleUpdateHandler());
    }

    public void registerPacketHandler(PacketType type, PacketHandler handler) {
        packetHandlerMap.put(type, handler);
    }

    public void updateClients() {
        Iterator<Client> i = clients.iterator();
        while(i.hasNext()) {
            Client client = i.next();
            if (!client.getSocket().isValid()) {
                i.remove();
                
            }
            
            if (!client.packetQueue.isEmpty()) {
                Packet packet = client.packetQueue.poll();
                packetHandlerMap.get(packet.getType()).handle(client, packet);
            }
        }
    }

    public void run() {
        while (true) {
            updateClients();
            gameLogic.update();
            // BROADCAST RESULTS
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
