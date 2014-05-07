package packets;

import serverSide.gamemechanics.PhysicalAttributes;


public class UpdateEntityPacket extends Packet {
    public int entityID;
    
    public float x;
    public float y;
    public float angle;

	/*
     * position
	 * rotation
	 * etc.
	 *
	 */

    //public PhysicalAttributes attrib;

    public UpdateEntityPacket(float x, float y, float angle) {
        super(PacketType.PACKET_UPDATEENTITY);
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

}
