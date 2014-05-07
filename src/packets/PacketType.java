package packets;

/* Packet ID (type)  - Packet Name
 * 0x00 - KeyPressPacket
 * 0x01 - KeyReleasePacket
 * 0x02 - CreateEntityPacket
 * 0x03 - UpdateEntityPacket
 * 0x04 - JoinGamePacket
 */

public enum PacketType {
    PACKET_KEYPRESS,
    PACKET_KEYRELEASE,
    PACKET_CREATEENTITY,
    PACKET_UPDATEENTITY,
    PACKET_JOINGAME
}
