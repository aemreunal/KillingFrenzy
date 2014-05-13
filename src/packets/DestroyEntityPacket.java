package packets;

import global.PacketType;

public class DestroyEntityPacket extends Packet {
    public int entityID;

    public DestroyEntityPacket() {
        super(PacketType.PACKET_DESTROY_ENTITY);
    }
}
