package serverSide.gamemechanics;

import clientSide.Client;
import packets.Packet;
import packets.UpdateEntityPacket;
import serverSide.server.Server;

import java.nio.channels.SelectionKey;
import java.util.List;

/**
 * Created by Eren Sezener
 */
public class GameLogic {
    private Server server;
    private List<Client> clients;

    public void receivePacket(Packet packet, SelectionKey key) {

    }

    public void update() {
        Entity[] entities = (Entity[]) World.getInstance().idToEntityMap.values().toArray();
    	for (Entity e : entities) {

    		UpdateEntityPacket packet = new UpdateEntityPacket();
    		packet.entityID = e.getId();
    		packet.attrib = e.physicalAttributes;
    		server.broadcast(packet);
    	}
    }

    public void checkForAllCollisions(Entity[] entities){
        for (int i = 0; i < entities.length; i++) {
            for (int j = 0; j < entities.length; j++){
                if(i != j){
                    if(existsACollisionBetween(entities[i], entities[j])){
                        handleCollision(entities[i], entities[j]);
                    }
                }
            }

        }
    }

    //There are only 3 types of collisions
    private void handleCollision(Entity entity1, Entity entity2) {
        if(isBulletPlayerCollision(entity1, entity2)){
            handleBulletPlayerCollision(entity1, entity2);
        } else if(isBulletWallCollision(entity1, entity2)){
            handleBulletWallCollision(entity1, entity2);
        } else if(isPlayerWallCollision(entity1, entity2)){
            handlePlayerWallCollision(entity1, entity2);
        }
    }

    private void handleBulletPlayerCollision(Entity entity1, Entity entity2) { //TODO handle deaths
        if(entity1 instanceof Bullet){
            ((Bullet) entity1).damagePlayer((Player) entity2);
        } else {
            ((Bullet) entity2).damagePlayer((Player) entity1);
        }
    }

    private void handleBulletWallCollision(Entity entity1, Entity entity2) {
        if(entity1 instanceof Bullet){
            destroyBullet(entity1);
        } else {
            ((Bullet) entity2).damagePlayer((Player) entity1);
        }

    }

    private void destroyBullet(Entity entity1) {
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

    public boolean existsACollisionBetween(Entity e1, Entity e2){

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
