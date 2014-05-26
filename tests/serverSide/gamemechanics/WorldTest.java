package serverSide.gamemechanics;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class WorldTest {
    World world = World.getInstance();

    @Test
    public void testGetInstance() throws Exception {
        //Can only create one instance
        World world2 = World.getInstance();
        assertSame(world, world2);
    }

    @Test
    public void testAddEntity() throws Exception {
        world.addEntity(new Bullet());
        assertEquals(world.idToEntityMap.size(), 1);
    }

    @Test
    public void testRemoveEntity() throws Exception {
        Bullet bullet = new Bullet();
        world.addEntity(bullet);
        assertEquals(world.idToEntityMap.size(), 1);
        world.removeEntity(bullet);
        assertEquals(world.idToEntityMap.size(), 0);
    }

    @Test
    public void testGetEntity() throws Exception {
        Bullet bullet = new Bullet();
        world.addEntity(bullet);
        assertSame(bullet, world.getEntity(bullet.getId()));
    }
}
