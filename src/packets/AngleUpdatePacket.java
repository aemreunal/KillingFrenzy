package packets;

import global.PacketType;

public class AngleUpdatePacket extends Packet {
    public int entityID;

    public float angle;

    public AngleUpdatePacket(float angle) {
        super(PacketType.PACKET_ANGLE_UPDATE);
        this.angle = angle;
    }
}
