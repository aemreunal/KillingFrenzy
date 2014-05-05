package serverSide.gamemechanics;

import clientSide.attributes.world.Grid;


public class PhysicalAttributes {
    public float left;
    public float right;
    public float top;
    public float bottom;
    public float angle;

    public PhysicalAttributes(float xCoor, float yCoor, float height, float width){
        this.left = xCoor;
        this.top = yCoor;
        this.right = xCoor + width;
        this.bottom = yCoor + height;
    }

}
