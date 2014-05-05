package serverSide.gamemechanics;

import clientSide.Client;
import packets.Packet;
import serverSide.server.Server;

import java.nio.channels.SelectionKey;
import java.util.List;

/**
 * Created by Eren Sezener
 */
public class GameLogic {
    private Server server;
    private World world;
    private List<Client> clients;

    public void receivePacket(Packet packet, SelectionKey key){

    }

    public boolean existsACollisionBetween(Entity e1, Entity e2){
        boolean collisionOnX = !this.thereIsCollisionOnX(e1, e2);
        boolean collisionOnY = !this.thereIsCollisionOnY(e1, e2);
        return collisionOnX && collisionOnY;
    }

    private boolean thereIsCollisionOnX(Entity e1, Entity e2) {
        return e1.physicalAttributes.right < e2.physicalAttributes.left || e1.physicalAttributes.left > e2.physicalAttributes.right;
    }

    private boolean thereIsCollisionOnY(Entity e1, Entity e2){
        return e1.physicalAttributes.bottom < e2.physicalAttributes.top || e1.physicalAttributes.top > e2.physicalAttributes.bottom;
    }
}
