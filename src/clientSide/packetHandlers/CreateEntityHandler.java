package clientSide.packetHandlers;

import clientSide.attributes.Bullet;
import clientSide.attributes.Entity;
import clientSide.attributes.Player;
import clientSide.attributes.World;
import packets.CreateEntityPacket;
import packets.Packet;

public class CreateEntityHandler extends PacketHandler {

    @Override
    public void handle(Packet pk) {
        CreateEntityPacket packet = (CreateEntityPacket) pk;

        switch (packet.entityType) {
            case ENTITY_PLAYER:
                Player player = Player.createPlayer(packet.isMine, packet.x, packet.y, packet.angle);
                addEntity(packet.entityID, player);
                break;
            case ENTITY_BULLET:
                Bullet bullet = new Bullet(packet.x, packet.y, packet.angle);
                addEntity(packet.entityID, bullet);
                break;
            case ENTITY_WALL:
                break;
            case ENTITY_BOX:
                break;
            default:
                break;
        }

    }

    private void addEntity(int entityID, Entity entity) {
        synchronized (World.getInstance().getEntityMap()) {
            World.getInstance().addEntity(entity, entityID);
        }
    }
}
