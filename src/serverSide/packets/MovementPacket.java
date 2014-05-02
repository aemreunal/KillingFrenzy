package serverSide.packets;

import serverSide.server.Packet;

public class MovementPacket extends Packet {

	//position update packet of an object.
	public int id;
	public float x;
	public float y;
	
	
	public MovementPacket() {
		type=1;
	}
	
}
