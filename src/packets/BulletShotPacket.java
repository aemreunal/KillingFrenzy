package packets;

import global.PacketType;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * Eren Sezener
 * Deniz Sokmen
 * Erdi Gultekin
 */

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
