package packets;

public class DestroyEntityPacket extends Packet {
    public int entityID;

    public DestroyEntityPacket() {
        super(PacketType.PACKET_DESTROYENTITY);
    }
}
