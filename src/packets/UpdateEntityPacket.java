package packets;

import serverSide.gamemechanics.PhysicalAttributes;


public class UpdateEntityPacket extends Packet {
    public int entityID;

	/*
     * position
	 * rotation
	 * etc.
	 *
	 */

    public PhysicalAttributes attrib;

    public UpdateEntityPacket() {
        super((byte) 3);
    }

}
