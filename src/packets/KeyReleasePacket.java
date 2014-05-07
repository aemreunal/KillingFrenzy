package packets;


public class KeyReleasePacket extends Packet {
    public int key;

    public KeyReleasePacket() {
        super(PacketType.PACKET_KEYRELEASE);
    }

    public KeyReleasePacket(int keyCode) {
        this();
        key = keyCode;
    }

}
