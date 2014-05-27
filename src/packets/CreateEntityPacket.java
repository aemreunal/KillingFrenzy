package packets;

import global.EntityType;
import global.PacketType;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * Eren Sezener
 * Deniz Sokmen
 * Erdi Gultekin
 */

public class CreateEntityPacket extends Packet {
    public int entityID;
    public EntityType entityType; // bullet, character, wall etc.
    public float x;
    public float y;
    public float angle;
    public boolean isMine;

    public CreateEntityPacket() {
        super(PacketType.PACKET_CREATE_ENTITY);
        isMine = false;
    }

}
