package clientSide.attributes;

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

    public PhysicalAttributes getPhysicalAttributes() {
        return physicalAttributes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract void paint(Graphics g);
}
