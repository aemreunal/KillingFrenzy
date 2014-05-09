package clientSide.attributes;

public class PhysicalAttributes {
    private float xCoor;
    private float yCoor;
    private float angle;

    private boolean movingNorth = false;
    private boolean movingEast = false;
    private boolean movingSouth = false;
    private boolean movingWest = false;

    public void setPosition(float xCoor, float yCoor, float angle) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        this.angle = angle;
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
        return movingEast || movingNorth || movingSouth || movingWest;
    }
}
