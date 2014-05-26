package packets;

import global.PacketType;

public class JoinGamePacket extends Packet {
    public JoinGamePacket() {
        super(PacketType.PACKET_JOIN_GAME);
    }

}
