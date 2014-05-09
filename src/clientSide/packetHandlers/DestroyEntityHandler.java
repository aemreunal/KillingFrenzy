package clientSide.packetHandlers;

import clientSide.attributes.world.World;
import packets.DestroyEntityPacket;
import packets.Packet;

public class DestroyEntityHandler extends PacketHandler {

    @Override
    public void handle(Packet pk) {
        DestroyEntityPacket packet = (DestroyEntityPacket) pk;

        World.getInstance().getEntity(packet.entityID).setAlive(false);
    }
}