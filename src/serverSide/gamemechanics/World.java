package serverSide.gamemechanics;

import java.util.TreeMap;

/**
 * Created by Eren Sezener
 */
public class World {
    private TreeMap<Integer, Entity> idToEntityMap;
    private int nextAvailableId; //Id of new entitites
    private static World world;
    
    public static World getInstance() {
    	if (world == null) {
    		world = new World();
    	}
    	
    	return world;
    }
    
    private World(){
        idToEntityMap = new TreeMap<Integer, Entity>();
        nextAvailableId = 0;
    }

    public void addEntity(Entity entity){
        entity.setId(nextAvailableId++);
        idToEntityMap.put(entity.getId(), entity);
    }
    

    public Entity voidGetEntity(int id){
        return idToEntityMap.get(id);
    }

}
