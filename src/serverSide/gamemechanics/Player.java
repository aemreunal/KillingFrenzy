package serverSide.gamemechanics;

/**
 * Created by Eren Sezener
 */
public class Player implements Collidable{
    private Gun gun;
    private float health;

    public float getHealth() {
        return health;
    }

    public void setGun(Gun gun) {
        this.gun = gun;
    }
}
