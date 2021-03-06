package clientSide.packetHandlers;

import clientSide.attributes.Bullet;
import clientSide.attributes.Player;
import clientSide.attributes.Wall;
import packets.CreateEntityPacket;
import packets.Packet;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * Eren Sezener
 * Deniz Sokmen
 * Erdi Gultekin
 */

public class CreateEntityHandler extends PacketHandler {

    @Override
    public void handle(Packet packet) {
        CreateEntityPacket entityPacket = (CreateEntityPacket) packet;
        switch (entityPacket.entityType) {
            case ENTITY_PLAYER:
                Player player = Player.createPlayer(entityPacket.isMine, entityPacket.x, entityPacket.y, entityPacket.angle);
                addEntity(entityPacket.entityID, player);
                break;
            case ENTITY_BULLET:
                Bullet bullet = new Bullet(entityPacket.x, entityPacket.y, entityPacket.angle);
                addEntity(entityPacket.entityID, bullet);
                break;
            case ENTITY_WALL:
                Wall wall = new Wall(entityPacket.x, entityPacket.y);
                addEntity(entityPacket.entityID, wall);
                break;
            default:
                break;
        }

    }
}
