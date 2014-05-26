package serverSide.gamemechanics;

import global.Settings;

public class Vector2D {
    public float[] v = new float[2];
    private float speed;

    public Vector2D() {
        this(0);
    }

    /*
     * Creates a cartesian vector from polar coordinates
     */
    public Vector2D(float angle) {
        speed = Settings.BULLET_SPEED;
        v[0] = (float) (speed * Math.cos(angle));
        v[1] = (float) (speed * Math.sin(angle));

    }

    public void update(float x, float y) {
        v[0] = x;
        v[1] = y;
    }

    public float getMagnitude() {
        return (float) Math.sqrt(getSpeedX() * getSpeedX() + getSpeedY() * getSpeedY());
    }

    public void negateVector() {
        this.v[0] *= -1;
        this.v[1] *= -1;
    }

    public float getSpeedX() {
        return v[0];
    }

    public float getSpeedY() {
        return v[1];
    }
}
