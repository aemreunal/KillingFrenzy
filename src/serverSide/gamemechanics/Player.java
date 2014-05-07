package serverSide.gamemechanics;

import serverSide.client.Client;

/**
 * Created by Eren Sezener
 */
public class Player extends Entity implements Collidable {
    private Gun gun;
    private float health;
    private float angle; //in radians
    private boolean isAlive;
    private Client client;

    public Player(Client client) {
<<<<<<< HEAD
        this.client = client;
=======
    	this.client = client;
        this.isAlive = true;
>>>>>>> FETCH_HEAD
    }

    public float getHealth() {
        return health;
    }

    public void setGun(Gun gun) {
        this.gun = gun;
    }

    public void fireGun() {
        gun.fire(angle);
    }

    public void decreaseHealth(float damage) {
        health -= damage;
        if (health < 0){
            die();
        }
    }

    private void die() {
        isAlive = false;
    }
}
