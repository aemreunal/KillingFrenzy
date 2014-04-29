package serverSide.gamemechanics;

import java.lang.Math;

public class Vector2D {

    public float [] v = new float[2];

    public Vector2D() {
        this(0, 0);
    }

    public Vector2D( float x, float y ) {
        v[0] = x;
        v[1] = y;
    }

    public void update(float x, float y) {
        v[0] = x;
        v[1] = y;
    }

    public float getMagnitude() {
        return (float)Math.sqrt( getX()* getX() + getY()* getY() );
    }

    public void negateVector() {
        this.v[0] *= -1;
        this.v[1] *= -1;
    }

    public static Vector2D add(Vector2D a, Vector2D b) {
        return new Vector2D( a.getX()+b.getX(), a.getY()+b.getY() );
    }

    public static Vector2D subtract(Vector2D a, Vector2D b) {
        return new Vector2D( a.getX()-b.getX(), a.getY()-b.getY() );
    }

    public float getX() {
        return v[0];
    }
    public float getY() {
        return v[1];
    }
}