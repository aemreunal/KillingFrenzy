package serverSide.gamemechanics;

/**
 * Created by Eren Sezener
 */
public class Bullet extends Entity {
    private static final int WIDTH = 2;
    private static final int HEIGHT = 2;
    private static float damage = 1;

    private Vector2D velocity;


    public Bullet() {
        super(HEIGHT, WIDTH);
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
        player.decreaseHealth(Bullet.damage);
        this.die(); //Destroy the bullet
    }

    @Override
    public void update() {
        super.physicalAttributes.updateVerticalPosition(getSpeedY());
        super.physicalAttributes.updateHorizontalPosition(getSpeedX());
    }
}
