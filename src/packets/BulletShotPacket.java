package packets;
/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

public class BulletShotPacket extends Packet {
    public int entityID;
    public float x;
    public float y;
    public float angle;

    public BulletShotPacket(int entityID, float x, float y, float angle) {
        super(PacketType.PACKET_BULLET_SHOT);
        this.entityID = entityID;
        this.x = x;
        this.y = y;
        this.angle = angle;
    }
}
