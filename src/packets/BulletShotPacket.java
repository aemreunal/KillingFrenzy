package packets;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

import global.PacketType;

public class BulletShotPacket extends Packet {
    public float xCoor;
    public float yCoor;
    public float angle;

    public BulletShotPacket(float[] tipOfGun) {
        super(PacketType.PACKET_BULLET_SHOT);
        this.xCoor = tipOfGun[0];
        this.yCoor = tipOfGun[1];
        this.angle = tipOfGun[2];
    }
}
