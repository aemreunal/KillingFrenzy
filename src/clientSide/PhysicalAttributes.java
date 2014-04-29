package clientSide;

import clientSide.attributes.world.Grid;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

public class PhysicalAttributes {
    private int id;
    private Grid grid;
    private double xCoor;
    private double yCoor;
    private double width;
    private double height;
    private double angle;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Grid getGrid() {
        return grid;
    }
    
    public void setGrid(Grid grid) {
        this.grid = grid;
    }
    
    public double getxCoor() {
        return xCoor;
    }
    
    public void setxCoor(double xCoor) {
        this.xCoor = xCoor;
    }
    
    public double getyCoor() {
        return yCoor;
    }
    
    public void setyCoor(double yCoor) {
        this.yCoor = yCoor;
    }
    
    public double getWidth() {
        return width;
    }
    
    public void setWidth(double width) {
        this.width = width;
    }
    
    public double getHeight() {
        return height;
    }
    
    public void setHeight(double height) {
        this.height = height;
    }
    
    public double getAngle() {
        return angle;
    }
    
    public void setAngle(double angle) {
        this.angle = angle;
    }
}
