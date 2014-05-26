package clientSide.packetHandlers;

import packets.DestroyEntityPacket;
import packets.Packet;
import clientSide.attributes.World;

public class DestroyEntityHandler extends PacketHandler {

    @Override
    public void handle(Packet pk) {
        DestroyEntityPacket packet = (DestroyEntityPacket) pk;
        World.getInstance().getEntity(packet.entityID).setAlive(false);
        removeEntity(packet.entityID);
    }
}
