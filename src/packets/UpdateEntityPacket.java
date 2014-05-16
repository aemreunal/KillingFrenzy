package packets;

import global.PacketType;

public class UpdateEntityPacket extends Packet {
    public int entityID;

    public float x;
    public float y;
    public float angle;
    public float health;
    public boolean isMoving = false;

    public UpdateEntityPacket(float x, float y, float angle, boolean isMoving) {
        super(PacketType.PACKET_UPDATE_ENTITY);
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.isMoving = isMoving;
    }
}
