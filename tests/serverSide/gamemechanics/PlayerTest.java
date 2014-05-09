package serverSide.gamemechanics;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    Player player = new Player(null);
    World world = World.getInstance();

    @Test
    public void testFireGun() throws Exception {
        player.fireGun();
        assertEquals(world.idToEntityMap.size(), 1);
    }

    @Test
    public void testDecreaseHealth() throws Exception {
        player.decreaseHealth(20);
        assertEquals(player.getHealth(), 80);
    }
}
