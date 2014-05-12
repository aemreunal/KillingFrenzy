package clientSide.packetHandlers;

import clientSide.attributes.Entity;
import clientSide.attributes.World;
import packets.Packet;
import packets.UpdateEntityPacket;

public class UpdateEntityHandler extends PacketHandler {

    @Override
    public void handle(Packet pk) {
        UpdateEntityPacket packet = (UpdateEntityPacket) pk;
        Entity entityToUpdate = World.getInstance().getEntity(packet.entityID);
        if (entityToUpdate != null) {
            entityToUpdate.update(packet);
        }
    }

}
