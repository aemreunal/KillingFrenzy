package packets;


public class UpdateEntityPacket extends Packet {
	public int entityID;
	
	/*
	 * position 
	 * rotation
	 * etc.
	 * 
	 */

	public UpdateEntityPacket() {
		type=3;
	}

}
