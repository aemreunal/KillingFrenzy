package clientSide.attributes;

public class PhysicalAttributes {
    private float xCoor;
    private float yCoor;
    private float angle;

    public PhysicalAttributes() {
        this.xCoor = 0;
        this.yCoor = 0;
    }

    public PhysicalAttributes(float xCoor, float yCoor) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
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
}
