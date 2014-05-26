package clientSide.packetHandlers;

import clientSide.attributes.Entity;
import packets.Packet;
import packets.UpdateEntityPacket;

public class UpdateEntityHandler extends PacketHandler {

    @Override
    public void handle(Packet pk) {
        UpdateEntityPacket packet = (UpdateEntityPacket) pk;
        Entity entityToUpdate = getEntity(packet.entityID);
        if (entityToUpdate != null) {
            entityToUpdate.update(packet);
        }
    }

}
