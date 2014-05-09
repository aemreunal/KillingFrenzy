package serverSide.gamemechanics;

/**
 * Created by Eren Sezener
 */
public abstract class Entity {
    private int id;
    private boolean isAlive;

    public PhysicalAttributes physicalAttributes;

    public Entity() {
        this(0, 0);
    }

    public Entity(float height, float width) {
        this(0, 0, height, width);
    }

    public Entity(float x, float y, float height, float width) {
        this(0, x, y, height, width);
    }

    public Entity(int id, float x, float y, float height, float width) {
        this.physicalAttributes = new PhysicalAttributes(x, y, height, width);
        this.id = 0;
        this.isAlive = true;
    }

    public abstract void update();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void die(){
        isAlive = false;
    }

    public boolean isAlive(){
        return this.isAlive;
    }
}
