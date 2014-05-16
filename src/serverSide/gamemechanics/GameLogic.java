package serverSide.gamemechanics;

import packets.DestroyEntityPacket;
import serverSide.client.Client;
import packets.Packet;
import serverSide.server.Server;

import java.nio.channels.SelectionKey;
import java.util.*;
import java.util.Map;

/**
 * Created by Eren Sezener
 */
public class GameLogic {
    private Server server;
    private List<Client> clients;

    public GameLogic(Server server){
        this.server = server;
    }

    public void receivePacket(Packet packet, SelectionKey key) {

    }

    public void update() {
        Collection<Entity> entities = World.getInstance().idToEntityMap.values();
        for (Entity e : entities) {

            /*UpdateEntityPacket packet = new UpdateEntityPacket();
            packet.ownerID = e.getId();
    		packet.attrib = e.physAttr;
    		server.broadcast(packet);*/

            e.update();
        }
        checkForAllCollisions(entities); //Don't mind the ugliness

        for (Entity e : entities) {
            server.broadcast(e.getUpdatePacket());
        }

        cleanDeadObjects();

    }

    public void checkForAllCollisions(Collection<Entity> entities) {
        for (Entity e1 : entities) {
            for (Entity e2 : entities) {
                if (!e1.equals(e2)) {
                    if (existsACollisionBetween(e1, e2)) {
                        System.out.println("Collision!");
                        System.out.println(e1.getClass());
                        System.out.println(e2.getClass());
                        handleCollision(e1, e2);
                    }
                }
            }

        }
    }

    private void cleanDeadObjects(){
        Iterator<java.util.Map.Entry<Integer, Entity>> iter = World.getInstance().idToEntityMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, Entity> entry = iter.next();
            if (!entry.getValue().isAlive()) {

                DestroyEntityPacket destroyEntityPacket = new DestroyEntityPacket();
                destroyEntityPacket.entityID = entry.getValue().getId();
                server.broadcast(destroyEntityPacket);
                iter.remove();

            }
        }
    }

    //There are only 3 types of collisions
    private void handleCollision(Entity entity1, Entity entity2) {
        if (isBulletPlayerCollision(entity1, entity2)) {
            handleBulletPlayerCollision(entity1, entity2);
            System.out.println("BULLET - PLAYER!");
        } else if (isBulletWallCollision(entity1, entity2)) {
            handleBulletWallCollision(entity1, entity2);
            System.out.println("BULLET - WALL!");
        } else if (isPlayerWallCollision(entity1, entity2)) {
            handlePlayerWallCollision(entity1, entity2);
            System.out.println("PLAYER - WALL!");
        } else{
            System.out.println("PLAYER - PLAYER!");
        }
    }

    private void handleBulletPlayerCollision(Entity entity1, Entity entity2) { //TODO handle deaths
        if (entity1 instanceof Bullet) {
            ((Bullet) entity1).damagePlayer((Player) entity2);
            System.out.println("SHOT!");
        } else {
            ((Bullet) entity2).damagePlayer((Player) entity1);
            System.out.println("SHOT!");
        }
    }

    private void handleBulletWallCollision(Entity entity1, Entity entity2) {
        if (entity1 instanceof Bullet) {
            entity1.die();
        } else {
            ((Bullet) entity2).damagePlayer((Player) entity1);
        }

    }


    private void handlePlayerWallCollision(Entity entity1, Entity entity2) {

    }

    private boolean isPlayerWallCollision(Entity entity1, Entity entity2) {
        return (entity1 instanceof Player && entity2 instanceof Wall) || (entity2 instanceof Player && entity1 instanceof Wall);
    }

    private boolean isBulletWallCollision(Entity entity1, Entity entity2) {
        return (entity1 instanceof Bullet && entity2 instanceof Wall) || (entity2 instanceof Bullet && entity1 instanceof Wall);
    }

    private boolean isBulletPlayerCollision(Entity entity1, Entity entity2) {
        return (entity1 instanceof Player && entity2 instanceof Bullet) || (entity2 instanceof Player && entity1 instanceof Bullet);
    }

    public boolean existsACollisionBetween(Entity e1, Entity e2) {

        boolean collisionOnX = !this.thereIsCollisionOnX(e1, e2);
        boolean collisionOnY = !this.thereIsCollisionOnY(e1, e2);
        return collisionOnX && collisionOnY;
    }

    private boolean thereIsCollisionOnX(Entity e1, Entity e2) {
        return e1.physicalAttributes.right < e2.physicalAttributes.left || e1.physicalAttributes.left > e2.physicalAttributes.right;
    }

    private boolean thereIsCollisionOnY(Entity e1, Entity e2) {
        return e1.physicalAttributes.bottom < e2.physicalAttributes.top || e1.physicalAttributes.top > e2.physicalAttributes.bottom;
    }
}
