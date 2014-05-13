package packets;


// Server sends this packet to client(s), commanding them to
// create entity with ID specified in the packet, the ID is essential and
// ALL ENTITIES IN THE CLIENT must be created through this packet
// a client can't create an entity with "random" id


public class CreateEntityPacket extends Packet {
    public int entityID;
    public EntityType entityType; // bullet, character, wall etc.
    public float x;
    public float y;
    public float angle;
    public boolean isMine;

    public CreateEntityPacket() {
        super(PacketType.PACKET_CREATE_ENTITY);
        isMine = false;
    }

}
