package packets;

import global.PacketType;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * Eren Sezener
 * Deniz Sokmen
 * Erdi Gultekin
 */

public class KeyPressPacket extends Packet {
    public int key;

    public KeyPressPacket() {
        super(PacketType.PACKET_KEY_PRESS);
    }

    public KeyPressPacket(int keyCode) {
        this();
        key = keyCode;
    }

}
