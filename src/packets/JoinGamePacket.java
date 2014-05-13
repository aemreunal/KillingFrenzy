package packets;


public class JoinGamePacket extends Packet {
    public JoinGamePacket() {
        super(PacketType.PACKET_JOIN_GAME);
    }

}
