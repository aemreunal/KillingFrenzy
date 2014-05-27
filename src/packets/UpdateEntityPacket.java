package packets;

import global.PacketType;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * Eren Sezener
 * Deniz Sokmen
 * Erdi Gultekin
 */

public class UpdateEntityPacket extends Packet {
    public int entityID;

    public float x;
    public float y;
    public float angle;
    public float health;
    public int score;
    public boolean isMoving = false;

    public UpdateEntityPacket(float x, float y, float angle, boolean isMoving) {
        super(PacketType.PACKET_UPDATE_ENTITY);
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.isMoving = isMoving;
    }
}
