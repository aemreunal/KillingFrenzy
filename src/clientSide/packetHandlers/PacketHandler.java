package clientSide.packetHandlers;

import packets.Packet;

public abstract class PacketHandler {

    public abstract void handle(Packet pk);
}