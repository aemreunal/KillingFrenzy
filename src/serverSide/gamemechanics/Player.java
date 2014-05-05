package serverSide.gamemechanics;

/**
 * Created by Eren Sezener
 */
public class Player extends Entity implements Collidable{
    private Gun gun;
    private float health;
    private float angle; //in radians

    public float getHealth() {
        return health;
    }

    public void setGun(Gun gun) {
        this.gun = gun;
    }

    public void fireGun(){
        gun.fire(angle);
    }
}
