package serverSide.gamemechanics;

import packets.UpdateEntityPacket;
import global.EntityType;
import global.Settings;

/**
 * Created by Eren Sezener
 */
public class Player extends Entity implements Collidable {
    private int health;
    private boolean leftKeyPress, rightKeyPressed, upKeyPressed, downKeyPressed;
    private int score;
    private float prev_X, prev_Y;

    public Player() {
        this.leftKeyPress = false;
        this.rightKeyPressed = false;
        this.upKeyPressed = false;
        this.downKeyPressed = false;
        this.score = 0;
        this.health = Settings.PLAYER_MAX_HEALTH;
        type = EntityType.ENTITY_PLAYER;
        this.physicalAttributes = new PhysicalAttributes(Settings.RESPAWN_POSITION_X, Settings.RESPAWN_POSITION_Y, Settings.PLAYER_SIZE, Settings.PLAYER_SIZE);
    }

    public Bullet fireGun(int ownerID, float angle, float x, float y) {
        Bullet bullet = new Bullet(ownerID, angle, x, y);
        World.getInstance().addEntity(bullet);
        return bullet;
    }

    public void decreaseHealth(float damage, int shooterId) {
        health -= damage;

        if (health < 0) {
            ((Player) World.getInstance().idToEntityMap.get(shooterId)).changeScore(Settings.KILLING_BONUS);
            this.respawnPlayer();
        }
    }

    @Override
    public void update() {

        savePreviousPosition();
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
    }

    private void savePreviousPosition() {
        this.prev_X = this.physicalAttributes.left;
        this.prev_Y = this.physicalAttributes.top;
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
    }

    public boolean isMoving() {
        return upKeyPressed || leftKeyPress || downKeyPressed || rightKeyPressed;
    }

    public int getHealth() {
        return health;
    }

    public UpdateEntityPacket createUpdatePacket() {
        UpdateEntityPacket packet = new UpdateEntityPacket(physicalAttributes.left, physicalAttributes.top, physicalAttributes.angle, isMoving());
        packet.health = health;
        packet.score = score;
        return packet;
    }

    public void respawnPlayer() {
        this.setAlive(true);
        this.health = Settings.PLAYER_MAX_HEALTH;
        this.physicalAttributes = new PhysicalAttributes(Settings.RESPAWN_POSITION_X, Settings.RESPAWN_POSITION_Y, Settings.PLAYER_SIZE, Settings.PLAYER_SIZE);
        this.changeScore(Settings.DYING_PENALTY);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void changeScore(int score) {
        this.score += score;
    }

    public void discardLastPositionChange() {
        this.physicalAttributes.left = prev_X;
        this.physicalAttributes.top = prev_Y;
        this.physicalAttributes.right = prev_X + Settings.PLAYER_SIZE;
        this.physicalAttributes.bottom = prev_Y + Settings.PLAYER_SIZE;
    }
}
