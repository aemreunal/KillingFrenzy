package packets;


public class KeyPressPacket extends Packet {
	public int key;

	public KeyPressPacket() {
		type=0;
	}

	public KeyPressPacket(int keyCode) {
		this();
		key = keyCode;
	}

}
