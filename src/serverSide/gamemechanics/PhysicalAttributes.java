package serverSide.gamemechanics;

import clientSide.attributes.world.Grid;


public class PhysicalAttributes {
    public int id;
    public Grid grid;
    public float left;
    public float right;
    public float top;
    public float bottom;
    public float angle;

    public PhysicalAttributes(int id, float xCoor, float yCoor, float width, float height){
        this.id = id;
        this.left = xCoor;
        this.top = yCoor;
        this.right = xCoor + width;
        this.bottom = yCoor + height;
    }

}
