package packets;


public class KeyReleasePacket extends Packet {
	public int key;

	public KeyReleasePacket() {
		type=1;
	}

}
