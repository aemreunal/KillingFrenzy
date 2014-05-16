package serverSide.gamemechanics;

import global.EntityType;
import global.Settings;

/**
 * Created by Eren Sezener
 */
public class Wall extends Entity implements Collidable {

    public Wall(int x, int y) {
        this.physicalAttributes = new PhysicalAttributes(x, y, Settings.WALL_BLOCK_SIZE, Settings.WALL_BLOCK_SIZE);
        type = EntityType.ENTITY_WALL;
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}
