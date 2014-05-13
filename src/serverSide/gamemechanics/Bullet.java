package serverSide.gamemechanics;

import global.Settings;

/**
 * Created by Eren Sezener
 */
public class Bullet extends Entity {
    private Vector2D velocity;
    private int ownerID; // TODO Bullets must have owners to assign scores!

    public Bullet() {
        super(Settings.BULLET_HEIGHT, Settings.BULLET_WIDTH);
        this.velocity = new Vector2D();
    }

    public Bullet(float angle) {
        this.velocity = new Vector2D(angle);
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
}
