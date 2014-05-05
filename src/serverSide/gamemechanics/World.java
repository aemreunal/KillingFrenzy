package serverSide.gamemechanics;

import java.util.TreeMap;

/**
 * Created by Eren Sezener
 */
public class World {
    private static TreeMap<Integer, Entity> idToEntityMap;
    private static int nextAvailableId; //Id of new entitites

    public World(){
        idToEntityMap = new TreeMap<Integer, Entity>();
        nextAvailableId = 0;
    }

    public static void addEntity(Entity entity){
        entity.setId(nextAvailableId++);
        idToEntityMap.put(entity.getId(), entity);
    }

    public static Entity voidGetEntity(int id){
        return idToEntityMap.get(id);
    }

}
