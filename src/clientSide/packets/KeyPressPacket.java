package clientSide.packets;

public class KeyPressPacket extends Packet {
    public int keyID;
    
    public KeyPressPacket() {
        type = 2;
    }
    
}
