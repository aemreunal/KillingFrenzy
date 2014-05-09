package clientSide.packetHandlers;

import clientSide.attributes.Entity;
import clientSide.attributes.world.World;
import packets.Packet;
import packets.UpdateEntityPacket;

public class UpdateEntityHandler extends PacketHandler {

    @Override
    public void handle(Packet pk) {
        UpdateEntityPacket packet = (UpdateEntityPacket) pk;
        Entity e = World.getInstance().getEntity(packet.entityID);
        if (e != null) {
            e.update(packet);
        }
    }

}
