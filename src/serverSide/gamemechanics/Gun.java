package serverSide.gamemechanics;

/**
 * Created by Eren Sezener
 */
public class Gun {
    private float bulletSpeed;
    private float damage;
    private int numberOfBulletsLeft;
    private int rateOfFire; // N times per second

    public void fire(float angle){
        World.addEntity(new Bullet(angle, bulletSpeed));
    }
}
