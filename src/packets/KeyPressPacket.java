package packets;


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
