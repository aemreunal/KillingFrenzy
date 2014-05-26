package serverSide.gameMechanics;

import global.EntityType;
import packets.CreateEntityPacket;
import packets.UpdateEntityPacket;

/**
 * Created by Eren Sezener
 */
public abstract class Entity {
    public PhysicalAttributes physicalAttributes;
    protected EntityType type;
    private int id;
    private boolean isAlive;

    public Entity() {
        this(0, 0);
    }

    public Entity(float height, float width) {
        this.physicalAttributes = new PhysicalAttributes(0, 0, height, width);
        this.id = 0;
        this.isAlive = true;
    }

    public abstract void update();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void die() {
        isAlive = false;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public CreateEntityPacket getCreationPacket() {
        CreateEntityPacket toSend = new CreateEntityPacket();
        toSend.x = physicalAttributes.left;
        toSend.y = physicalAttributes.top;
        toSend.angle = physicalAttributes.angle;
        toSend.entityID = getId();
        toSend.entityType = type;
        return toSend;
    }

    public UpdateEntityPacket getUpdatePacket() {
        UpdateEntityPacket updatePacket = createUpdatePacket();
        updatePacket.entityID = getId();
        return updatePacket;
    }

    public UpdateEntityPacket createUpdatePacket() {
        return new UpdateEntityPacket(physicalAttributes.left, physicalAttributes.top, physicalAttributes.angle, isMoving());
    }

    public boolean isMoving() {
        return false;
    }

    public EntityType getType() {
        return type;
    }
}
