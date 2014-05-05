package serverSide.gamemechanics;

public class Vector2D {

    public float[] v = new float[2];

    public Vector2D() {
        this(0, 0);
    }

    /*
    Creates a cartesian vector from polar coordinates
     */
    public Vector2D(float angle, float velocity) {
        v[0] = (float) (velocity * Math.cos(angle));
        v[1] = (float) (velocity * Math.sin(angle));

    }

    public static Vector2D add(Vector2D a, Vector2D b) {
        return new Vector2D(a.getX() + b.getX(), a.getY() + b.getY());
    }

    public static Vector2D subtract(Vector2D a, Vector2D b) {
        return new Vector2D(a.getX() - b.getX(), a.getY() - b.getY());
    }

    public void update(float x, float y) {
        v[0] = x;
        v[1] = y;
    }

    public float getMagnitude() {
        return (float) Math.sqrt(getX() * getX() + getY() * getY());
    }

    public void negateVector() {
        this.v[0] *= -1;
        this.v[1] *= -1;
    }

    public float getX() {
        return v[0];
    }

    public float getY() {
        return v[1];
    }
}
