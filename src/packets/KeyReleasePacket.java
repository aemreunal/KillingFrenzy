package packets;


public class KeyReleasePacket extends Packet {
	public int key;

	public KeyReleasePacket() {
		type=1;
	}

	public KeyReleasePacket(int keyCode) {
		this();
		key = keyCode;
	}

}
