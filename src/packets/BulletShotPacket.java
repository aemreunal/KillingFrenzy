package packets;
/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

import global.PacketType;

public class BulletShotPacket extends Packet {
    public float angle;

    public BulletShotPacket(float angle) {
        super(PacketType.PACKET_BULLET_SHOT);
        this.angle = angle;
    }
}
