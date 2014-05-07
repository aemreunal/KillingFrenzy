package clientSide.attributes;

public class PhysicalAttributes {
    private float leftBound;
    private float rightBound;
    private float topBound;
    private float bottomBound;
    private float angle;

    public PhysicalAttributes(float xCoor, float yCoor, float height, float width) {
        this.leftBound = xCoor;
        this.topBound = yCoor;
        this.rightBound = xCoor + width;
        this.bottomBound = yCoor + height;
    }

    public float getLeftBound() {
        return leftBound;
    }

    public void setLeftBound(float leftBound) {
        this.leftBound = leftBound;
    }

    public float getRightBound() {
        return rightBound;
    }

    public void setRightBound(float rightBound) {
        this.rightBound = rightBound;
    }

    public float getTopBound() {
        return topBound;
    }

    public void setTopBound(float topBound) {
        this.topBound = topBound;
    }

    public float getBottomBound() {
        return bottomBound;
    }

    public void setBottomBound(float bottomBound) {
        this.bottomBound = bottomBound;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
