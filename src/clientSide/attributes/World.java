package clientSide.attributes;

import clientSide.graphics.WorldGround;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * Eren Sezener
 * Deniz Sokmen
 * Erdi Gultekin
 */

public class World {
    private static World world;
    private static Player thisPlayer;
    private Map<Integer, Entity> idToEntityMap;
    private WorldGround ground;

    private World() {
        idToEntityMap = new ConcurrentHashMap<>();
        ground = new WorldGround();
    }

    public static World getInstance() {
        if (world == null) {
            world = new World();
        }
        return world;
    }

    public static Player getThisPlayer() {
        return thisPlayer;
    }

    public static void setThisPlayer(Player thisPlayer) {
        World.thisPlayer = thisPlayer;
    }

    public synchronized void addEntity(Entity entity, int entityId) {
        entity.setId(entityId);
        idToEntityMap.put(entity.getId(), entity);
    }

    public Map<Integer, Entity> getEntityMap() {
        return idToEntityMap;
    }

    public synchronized Entity getEntity(int id) {
        return idToEntityMap.get(id);
    }

    public Collection<Entity> getEntities() {
        return idToEntityMap.values();
    }

    public WorldGround getGround() {
        return ground;
    }

    public synchronized void removeEntity(int entityID) {
        idToEntityMap.remove(entityID);
    }
}
