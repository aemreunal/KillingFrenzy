package serverSide.gamemechanics;

import clientSide.Client;
import packets.Packet;
import serverSide.server.Server;

import java.nio.channels.SelectionKey;
import java.util.List;

/**
 * Created by Eren Sezener
 */
public class GameLogic {
    private Server server;
    private World world;
    private List<Client> clients;

    public void receivePacket(Packet packet, SelectionKey key){

    }
}
