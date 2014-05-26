package global;

/* Packet ID (type)  - Packet Name
 * 0x00 - KeyPressPacket
 * 0x01 - KeyReleasePacket
 * 0x02 - CreateEntityPacket
 * 0x03 - UpdateEntityPacket
 * 0x04 - JoinGamePacket
 */

public enum PacketType {
    PACKET_KEY_PRESS, PACKET_KEY_RELEASE, PACKET_CREATE_ENTITY, PACKET_UPDATE_ENTITY, PACKET_JOIN_GAME, PACKET_DESTROY_ENTITY, PACKET_ANGLE_UPDATE, PACKET_BULLET_SHOT
}
