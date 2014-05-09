package clientSide.attributes;

import packets.UpdateEntityPacket;

import java.awt.*;

/**
 * Created by Eren Sezener
 */
public abstract class Entity {
    protected int id;
    protected PhysicalAttributes physAttr;
    protected boolean isAlive;

    public Entity() {
        this.physAttr = new PhysicalAttributes();
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void update(UpdateEntityPacket updatePacket) {
        physAttr.update(updatePacket.x, updatePacket.y, updatePacket.angle, updatePacket.isMoving);
    }

    public abstract void paint(Graphics g);

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
}
