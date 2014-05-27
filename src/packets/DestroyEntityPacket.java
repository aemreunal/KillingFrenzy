package packets;

import global.PacketType;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * Eren Sezener
 * Deniz Sokmen
 * Erdi Gultekin
 */

public class DestroyEntityPacket extends Packet {
    public int entityID;

    public DestroyEntityPacket() {
        super(PacketType.PACKET_DESTROY_ENTITY);
    }
}
