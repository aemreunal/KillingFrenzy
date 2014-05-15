package serverSide.gamemechanics;

/**
 * Created by Eren Sezener
 */
public class Wall extends Entity implements Collidable {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    
    public Wall(int x, int y, boolean isVertical, int size) {
    	if(isVertical){
    		this.physicalAttributes = new PhysicalAttributes(x, y, size*HEIGHT, WIDTH);
    	}else{
    		this.physicalAttributes = new PhysicalAttributes(x, y, HEIGHT,size*WIDTH);
    	}
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
