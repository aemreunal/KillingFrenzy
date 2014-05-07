package serverSide.gamemechanics;

/**
 * Created by Eren Sezener
 */
public class Bullet extends Entity {
    private static final int WIDTH = 2;
    private static final int HEIGHT = 2;

    private Vector2D velocity;
    private float damage;

    public Bullet() {
        super(HEIGHT, WIDTH);
        this.velocity = new Vector2D();
    }

    public Bullet(float angle, float speed) {
        this.velocity = new Vector2D(angle, speed);
    }

    public float getXVelocity() {
        return velocity.getX();
    }

    public float getYVelocity() {
        return velocity.getY();
    }

}
