package clientSide.attributes;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * Eren Sezener
 * Deniz Sokmen
 * Erdi Gultekin
 */

public class PhysicalAttributes {
    private float xCoor;
    private float yCoor;
    private float angle;
    private boolean isMoving;

    public PhysicalAttributes() {
        this(0, 0, 0);
    }

    public PhysicalAttributes(float xCoor, float yCoor, float angle) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        this.angle = angle;
    }

    public void update(float xCoor, float yCoor, boolean isMoving) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        this.isMoving = isMoving;
    }

    public void update(float xCoor, float yCoor, float angle) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        this.angle = angle;
    }

    public void update(float xCoor, float yCoor, float angle, boolean isMoving) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        this.angle = angle;
        this.isMoving = isMoving;
    }

    public float getxCoor() {
        return xCoor;
    }

    public void setxCoor(float xCoor) {
        this.xCoor = xCoor;
    }

    public float getyCoor() {
        return yCoor;
    }

    public void setyCoor(float yCoor) {
        this.yCoor = yCoor;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }
}
