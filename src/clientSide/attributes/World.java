package clientSide.attributes;

import clientSide.graphics.WorldGround;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Eren Sezener
 */
public class World {
    private Map<Integer, Entity> idToEntityMap;
    private static World world;
    private WorldGround ground;
    private static Player thisPlayer;

    public static World getInstance() {
        if (world == null) {
            world = new World();
        }
        return world;
    }

    private World() {
        idToEntityMap = new ConcurrentHashMap<>();
        ground = new WorldGround();
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

    public static Player getThisPlayer() {
        return thisPlayer;
    }

    public static void setThisPlayer(Player thisPlayer) {
        World.thisPlayer = thisPlayer;
    }

    public synchronized void removeEntity(int entityID) {
        idToEntityMap.remove(entityID);
    }
}
