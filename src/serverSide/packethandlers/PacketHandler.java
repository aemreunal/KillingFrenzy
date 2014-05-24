package serverSide.packetHandlers;

import packets.Packet;
import serverSide.client.Client;

public abstract class PacketHandler {

    public abstract void handle(Client client, Packet pk);
}
