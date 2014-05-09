package clientSide.attributes;

import packets.UpdateEntityPacket;

import java.awt.*;
import java.awt.image.BufferedImage;

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
        if (updatePacket.entityID != World.getThisPlayer().getId()) {
            physAttr.update(updatePacket.x, updatePacket.y, updatePacket.angle, updatePacket.isMoving);
        } else {
            physAttr.update(updatePacket.x, updatePacket.y, updatePacket.isMoving);
        }
    }

    public abstract void paint(Graphics g);

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public BufferedImage rotate(BufferedImage img, double angle, float width, float height) {
        double sin = Math.abs(Math.sin(angle));
        double cos = Math.abs(Math.cos(angle));
        int newWidth = (int) Math.floor(width * cos + height * sin);
        int newHeight = (int) Math.floor(height * cos + width * sin);

        BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = rotatedImage.createGraphics();
        g.rotate(angle, width / 2, height / 2);
        g.drawRenderedImage(img, null);
        g.dispose();
        return rotatedImage;
    }
}
