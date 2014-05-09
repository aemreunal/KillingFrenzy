package clientSide.attributes;

import packets.UpdateEntityPacket;

import java.awt.*;

/**
 * Created by Eren Sezener
 */
public abstract class Entity {
    protected int id;
    protected PhysicalAttributes physicalAttributes;

    public Entity() {
        this.physicalAttributes = new PhysicalAttributes();
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void update(UpdateEntityPacket updatePacket) {
        physicalAttributes.setPosition(updatePacket.x, updatePacket.y, updatePacket.angle);
    }

    public abstract void paint(Graphics g);
}
