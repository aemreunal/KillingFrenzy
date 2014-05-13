package serverSide.gamemechanics;

import global.EntityType;
import global.Settings;
import packets.UpdateEntityPacket;
import serverSide.client.Client;

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
        type = EntityType.ENTITY_PLAYER;
    }

    public void fireGun() {
        World.getInstance().addEntity(new Bullet(angle));
    }

    public void decreaseHealth(float damage) {
        health -= damage;

        if (health < 0) {
            this.die();

        }
    }

    @Override
    public void update() {
        if (upKeyPressed) {
            if (this.physicalAttributes.top > 0) {
                this.physicalAttributes.updateVerticalPosition(-1 * Settings.PLAYER_LOC_UPDATE_AMOUNT);
            }
        }
        if (leftKeyPress) {
            if (this.physicalAttributes.left > 0) {
                this.physicalAttributes.updateHorizontalPosition(-1 * Settings.PLAYER_LOC_UPDATE_AMOUNT);
            }
        }
        if (downKeyPressed) {
            if (this.physicalAttributes.bottom < Settings.GAME_WINDOW_HEIGHT) {
                this.physicalAttributes.updateVerticalPosition(Settings.PLAYER_LOC_UPDATE_AMOUNT);
            }
        }
        if (rightKeyPressed) {
            if (this.physicalAttributes.right < Settings.GAME_WINDOW_WIDTH) {
                this.physicalAttributes.updateHorizontalPosition(Settings.PLAYER_LOC_UPDATE_AMOUNT);
            }
        }

        if (upKeyPressed || leftKeyPress || downKeyPressed || rightKeyPressed) {
            UpdateEntityPacket updateEntity = new UpdateEntityPacket(physicalAttributes.left, physicalAttributes.top, physicalAttributes.angle, true);
            updateEntity.entityID = getId();
            client.getServer().broadcast(updateEntity);
        }
    }

    public void onKeyPressed(int keyCode) {
        if (keyCode == Settings.KEY_NORTH) {
            upKeyPressed = true;
        } else if (keyCode == Settings.KEY_WEST) {
            leftKeyPress = true;
        } else if (keyCode == Settings.KEY_SOUTH) {
            downKeyPressed = true;
        } else if (keyCode == Settings.KEY_EAST) {
            rightKeyPressed = true;
        }
    }

    public void onKeyReleased(int keyCode) {
        if (keyCode == Settings.KEY_NORTH) {
            upKeyPressed = false;
        } else if (keyCode == Settings.KEY_WEST) {
            leftKeyPress = false;
        } else if (keyCode == Settings.KEY_SOUTH) {
            downKeyPressed = false;
        } else if (keyCode == Settings.KEY_EAST) {
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
