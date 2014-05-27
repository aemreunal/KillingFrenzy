package clientSide.packetHandlers;

import clientSide.attributes.Entity;
import clientSide.attributes.World;
import packets.Packet;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * Eren Sezener
 * Deniz Sokmen
 * Erdi Gultekin
 */

public abstract class PacketHandler {

    public abstract void handle(Packet pk);

    protected synchronized void addEntity(int entityID, Entity entity) {
        World.getInstance().addEntity(entity, entityID);
    }

    protected synchronized void removeEntity(int entityID) {
        World.getInstance().removeEntity(entityID);
    }

    protected synchronized Entity getEntity(int entityID) {
        return World.getInstance().getEntity(entityID);
    }
}
