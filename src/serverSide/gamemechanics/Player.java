package serverSide.gamemechanics;

import clientSide.Settings;
import serverSide.client.Client;

import java.awt.event.KeyEvent;

import packets.UpdateEntityPacket;

/**
 * Created by Eren Sezener
 */
public class Player extends Entity implements Collidable {
    private int health;
    private float angle; //in radians
    private Client client;
    private boolean leftKeyPress, rightKeyPressed, upKeyPressed, downKeyPressed;

    public Player(Client client) {
        this.client = client;
        this.leftKeyPress = false;
        this.rightKeyPressed = false;
        this.upKeyPressed = false;
        this.downKeyPressed = false;
        this.health = Settings.PLAYER_MAX_HEALTH;
    }

    public void fireGun() {
        World.getInstance().addEntity(new Bullet(angle));
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

        if (upKeyPressed || leftKeyPress || downKeyPressed || rightKeyPressed) {
            UpdateEntityPacket updateEntity = new UpdateEntityPacket(physicalAttributes.left, physicalAttributes.top, physicalAttributes.angle, true);
            updateEntity.entityID = getId();
            client.getServer().broadcast(updateEntity);
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

        UpdateEntityPacket updateEntity = new UpdateEntityPacket(physicalAttributes.left, physicalAttributes.top, physicalAttributes.angle, false);
        updateEntity.entityID = getId();
        client.getServer().broadcast(updateEntity);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

}
