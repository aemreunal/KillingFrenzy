package clientSide.attributes;

public class PhysicalAttributes {
    private float xCoor;
    private float yCoor;
    private float angle;
    private boolean isMoving;

    public void setPosition(float xCoor, float yCoor, float angle) {
        isMoving = true;
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        this.angle = angle;
    }

    public float getxCoor() {
        return xCoor;
    }

    public void setxCoor(float xCoor) {
        isMoving = (this.xCoor != xCoor);
        this.xCoor = xCoor;
    }

    public float getyCoor() {
        return yCoor;
    }

    public void setyCoor(float yCoor) {
        isMoving = (this.yCoor != yCoor);
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
