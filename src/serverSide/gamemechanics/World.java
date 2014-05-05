package serverSide.gamemechanics;

import java.util.HashMap;

/**
 * Created by Eren Sezener
 */
public class World {
    private HashMap<Integer, Entity> idToEntityMap;

    public World(){
        idToEntityMap = new HashMap<Integer, Entity>();
    }

    public void addEntity(Entity entity){
        idToEntityMap.put(entity.getId(), entity);
    }

    public Entity voidGetEntity(int id){
        return idToEntityMap.get(id);
    }

}
