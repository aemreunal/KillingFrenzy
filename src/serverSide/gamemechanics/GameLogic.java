package serverSide.gameMechanics;

import global.EntityType;
import packets.DestroyEntityPacket;
import serverSide.client.Client;
import serverSide.server.Server;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Eren Sezener
 */

public class GameLogic {
    private Server server;
    private List<Client> clients;

    public GameLogic(Server server) {
        this.server = server;
        GameMap map = new GameMap();
        this.addWalls(map.getWallXCoordinates(), map.getWallYCoordinates());

    }

    public void addWalls(Integer[] x, Integer[] y) {
        for (int i = 0; i < x.length; i++) {
            Wall wall = new Wall(x[i].intValue(), y[i].intValue());
            World.getInstance().addEntity(wall);
        }
    }

    public void update() {
        Collection<Entity> entities = World.getInstance().idToEntityMap.values();
        for (Entity e : entities) {
            e.update();
        }
        checkForAllCollisions(entities); //Don't mind the ugliness

        for (Entity e : entities) {
            if (!(e instanceof Wall)) {
                server.broadcast(e.getUpdatePacket());
            }
        }

        cleanDeadObjects();
    }

    public void checkForAllCollisions(Collection<Entity> entities) {
        for (Entity e1 : entities) {
            for (Entity e2 : entities) {
                if (!(e1.type == EntityType.ENTITY_WALL && e2.type == EntityType.ENTITY_WALL) && !e1.equals(e2)) {
                    if (existsACollisionBetween(e1, e2)) {
                        System.out.println(e1.getClass());
                        System.out.println(e2.getClass());
                        handleCollision(e1, e2);
                    }
                }
            }

        }
    }

    private void cleanDeadObjects() {
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
        } else if (isBulletWallCollision(entity1, entity2)) {
            handleBulletWallCollision(entity1, entity2);
        } else if (isPlayerWallCollision(entity1, entity2)) {
            handlePlayerWallCollision(entity1, entity2);
        }
    }

    private void handleBulletPlayerCollision(Entity entity1, Entity entity2) { //TODO handle deaths
        if (entity1 instanceof Bullet) {
            ((Bullet) entity1).damagePlayer((Player) entity2);
        } else {
            ((Bullet) entity2).damagePlayer((Player) entity1);
        }
    }

    private void handleBulletWallCollision(Entity entity1, Entity entity2) {
        if (entity1 instanceof Bullet) {
            entity1.die();
        } else {
            entity2.die();
        }

    }


    private void handlePlayerWallCollision(Entity entity1, Entity entity2) {
        if (entity1 instanceof Player) {
            ((Player) entity1).discardLastPositionChange();
        } else {
            ((Player) entity2).discardLastPositionChange();
        }
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
