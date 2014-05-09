package packets;

public class UpdateEntityPacket extends Packet {
    public int entityID;

    public float x;
    public float y;
    public float angle;

    public boolean movingNorth = false;
    public boolean movingEast = false;
    public boolean movingSouth = false;
    public boolean movingWest = false;

    public UpdateEntityPacket(float x, float y, float angle) {
        super(PacketType.PACKET_UPDATEENTITY);
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public boolean isMoving() {
        return movingEast || movingNorth || movingSouth || movingWest;
    }
}
