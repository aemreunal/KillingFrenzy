package packets;

public class AngleUpdatePacket extends Packet {
    public int entityID;

    public float angle;

    public AngleUpdatePacket(float angle) {
        super(PacketType.PACKET_ANGLEUPDATE);
        this.angle = angle;
    }
}
