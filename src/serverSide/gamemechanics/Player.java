package serverSide.gamemechanics;

import clientSide.Settings;
import serverSide.client.Client;

import java.awt.event.KeyEvent;

/**
 * Created by Eren Sezener
 */
public class Player extends Entity implements Collidable {
    private Gun gun;
    private float health;
    private float angle; //in radians
    private Client client;
    private boolean leftKeyPress, rightKeyPressed, upKeyPressed, downKeyPressed;

    public Player(Client client) {
        this.client = client;
        this.leftKeyPress = false;
        this.rightKeyPressed = false;
        this.upKeyPressed = false;
        this.downKeyPressed = false;
    }

    public float getHealth() { //TODO might be redundant
        return health;
    }

    public void setGun(Gun gun) { //TODO might be redundant
        this.gun = gun;
    }

    public void fireGun() {
        gun.fire(angle);
    }

    public void decreaseHealth(float damage) {
        health -= damage;

        if (health < 0){
            this.die();

        }
    }

    @Override
    public void update() {
        if (upKeyPressed) {
            this.physicalAttributes.updateVerticalPosition(-1 * Settings.PLAYER_LOC_UPDATE_AMOUNT);
        }
        if (leftKeyPress) {
            this.physicalAttributes.updateHorizontalPosition(-1 * Settings.PLAYER_LOC_UPDATE_AMOUNT);
        }
        if (downKeyPressed) {
            this.physicalAttributes.updateVerticalPosition(Settings.PLAYER_LOC_UPDATE_AMOUNT);
        }
        if (rightKeyPressed) {
            this.physicalAttributes.updateHorizontalPosition(Settings.PLAYER_LOC_UPDATE_AMOUNT);
        }
    }

    public void onKeyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_W) {
            upKeyPressed = true;
        } else if (keyCode == KeyEvent.VK_A) {
            leftKeyPress = true;
        } else if (keyCode == KeyEvent.VK_S) {
            downKeyPressed = true;
        } else if (keyCode == KeyEvent.VK_D) {
            rightKeyPressed = true;
        }
    }

    public void onKeyReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_W) {
            upKeyPressed = false;
        } else if (keyCode == KeyEvent.VK_A) {
            leftKeyPress = false;
        } else if (keyCode == KeyEvent.VK_S) {
            downKeyPressed = false;
        } else if (keyCode == KeyEvent.VK_D) {
            rightKeyPressed = false;
        }
    }
}
