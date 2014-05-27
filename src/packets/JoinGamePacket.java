package packets;

import global.PacketType;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * Eren Sezener
 * Deniz Sokmen
 * Erdi Gultekin
 */

public class JoinGamePacket extends Packet {
    public JoinGamePacket() {
        super(PacketType.PACKET_JOIN_GAME);
    }

}
