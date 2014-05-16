package clientSide.packetHandlers;

import clientSide.attributes.Bullet;
import clientSide.attributes.Player;
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
            default:
                break;
        }

    }
}
