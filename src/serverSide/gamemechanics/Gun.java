package serverSide.gamemechanics;

/**
 * Created by Eren Sezener
 */
public class Gun {
    public void fire(float angle) {
        World.getInstance().addEntity(new Bullet(angle));
    }
}
