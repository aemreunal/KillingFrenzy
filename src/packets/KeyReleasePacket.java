package packets;


public class KeyReleasePacket extends Packet {
    public int key;

    public KeyReleasePacket() {
        super((byte) 1);
    }

    public KeyReleasePacket(int keyCode) {
        this();
        key = keyCode;
    }

}
