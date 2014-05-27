package serverSide.gamemechanics;

import global.EntityType;
import global.Settings;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * Eren Sezener
 * Deniz Sokmen
 * Erdi Gultekin
 */

public class Wall extends Entity implements Collidable {

    public Wall(int x, int y) {
        type = EntityType.ENTITY_WALL;
        this.physicalAttributes = new PhysicalAttributes(x, y, Settings.WALL_BLOCK_SIZE, Settings.WALL_BLOCK_SIZE);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }
}
