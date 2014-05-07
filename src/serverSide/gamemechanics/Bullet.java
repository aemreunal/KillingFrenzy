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

    public Bullet(float angle, float speed) {
        this.velocity = new Vector2D(angle, speed);
    }

    public float getXVelocity() {
        return velocity.getX();
    }

    public float getYVelocity() {
        return velocity.getY();
    }

    public static void damagePerson(Player player){
        player.decreaseHealth(Bullet.damage);
    }
}
