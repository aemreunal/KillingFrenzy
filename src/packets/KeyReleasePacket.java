package packets;


import global.PacketType;

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
