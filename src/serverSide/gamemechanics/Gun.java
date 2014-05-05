package serverSide.gamemechanics;

/**
 * Created by Eren Sezener
 */
public class Gun {
    private float bulletSpeed;

    public void fire(float angle){
        World.addEntity(new Bullet(angle, bulletSpeed));
    }
}
