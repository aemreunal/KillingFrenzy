package serverSide.gamemechanics;

import serverSide.client.Client;

/**
 * Created by Eren Sezener
 */
public class Player extends Entity implements Collidable{
    private Gun gun;
    private float health;
    private float angle; //in radians
    private Client client;
    
    public Player(Client client) {
    	this.client = client;
    }

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
