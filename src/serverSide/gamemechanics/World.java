package serverSide.gameMechanics;

import java.util.TreeMap;

/**
 * Created by Eren Sezener
 */
public class World {
    private static World world;
    public TreeMap<Integer, Entity> idToEntityMap;
    private int nextAvailableId; //Id of new entitites

    private World() {
        idToEntityMap = new TreeMap<Integer, Entity>();
        nextAvailableId = 0;
    }

    public static World getInstance() {
        if (world == null) {
            world = new World();
        }

        return world;
    }

    public void addEntity(Entity entity) {
        entity.setId(nextAvailableId++);
        idToEntityMap.put(entity.getId(), entity);
    }

    public void removeEntity(Entity entity) {
        idToEntityMap.remove(entity.getId());
    }

    public Entity getEntity(int id) {
        return idToEntityMap.get(id);
    }

}
