package clientSide.packets;

import clientSide.Packet;


public class KeyPressPacket extends Packet {
	public int keyID;
	
	public KeyPressPacket() {
		type=2;
	}
	
}
