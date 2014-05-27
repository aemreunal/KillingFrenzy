package packets;

import global.PacketType;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * Eren Sezener
 * Deniz Sokmen
 * Erdi Gultekin
 */

public class KeyReleasePacket extends Packet {
    public int key;

    public KeyReleasePacket() {
        super(PacketType.PACKET_KEY_RELEASE);
    }

    public KeyReleasePacket(int keyCode) {
        this();
        key = keyCode;
    }

}
