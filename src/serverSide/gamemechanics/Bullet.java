package serverSide.gamemechanics;

import global.EntityType;
import global.Settings;

/**
 * Created by Eren Sezener
 */
public class Bullet extends Entity {
    private Vector2D velocity;
    private int ownerID;

    public Bullet() {
        super(Settings.BULLET_HEIGHT, Settings.BULLET_WIDTH);
        this.velocity = new Vector2D();
        type = EntityType.ENTITY_BULLET;
    }

    public Bullet(int ownerID, float angle, float x, float y) {
        super(Settings.BULLET_HEIGHT, Settings.BULLET_WIDTH);
        this.velocity = new Vector2D(angle);
        this.ownerID = ownerID;
        this.physicalAttributes.top = y;
        this.physicalAttributes.left = x;
        this.physicalAttributes.right = x + Settings.BULLET_WIDTH;
        this.physicalAttributes.bottom = y + Settings.BULLET_HEIGHT;
        type = EntityType.ENTITY_BULLET;
    }

    public float getSpeedX() {
        return velocity.getSpeedX();
    }

    public float getSpeedY() {
        return velocity.getSpeedY();
    }

    public void damagePlayer(Player player) {
        player.decreaseHealth(Settings.BULLET_DAMAGE);
        this.die(); //Destroy the bullet
    }

    @Override
    public void update() {
        super.physicalAttributes.updateVerticalPosition(getSpeedY());
        super.physicalAttributes.updateHorizontalPosition(getSpeedX());
    }

    public int getOwnerID() {
        return ownerID;
    }
}
