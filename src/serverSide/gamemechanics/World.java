package serverSide.gamemechanics;

import java.util.TreeMap;

/**
 * Created by Eren Sezener
 */
public class World {
    public static TreeMap<Integer, Entity> idToEntityMap;
    private static int nextAvailableId; //Id of new entitites
    private static World world;

    public static World getInstance() {
        if (world == null) {
            world = new World();
        }

        return world;
    }

    private World() {
        idToEntityMap = new TreeMap<Integer, Entity>();
        nextAvailableId = 0;
    }

    public static void addEntity(Entity entity) {
        entity.setId(nextAvailableId++);
        idToEntityMap.put(entity.getId(), entity);
    }

    public static void removeEntity(Entity entity){
        idToEntityMap.remove(entity.getId());
    }

    public static Entity getEntity(int id) {
        return idToEntityMap.get(id);
    }

}
