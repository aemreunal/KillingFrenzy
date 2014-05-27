package clientSide.packetHandlers;

import clientSide.attributes.World;
import packets.DestroyEntityPacket;
import packets.Packet;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * Eren Sezener
 * Deniz Sokmen
 * Erdi Gultekin
 */

public class DestroyEntityHandler extends PacketHandler {

    @Override
    public void handle(Packet pk) {
        DestroyEntityPacket packet = (DestroyEntityPacket) pk;
        World.getInstance().getEntity(packet.entityID).setAlive(false);
        removeEntity(packet.entityID);
    }
}
