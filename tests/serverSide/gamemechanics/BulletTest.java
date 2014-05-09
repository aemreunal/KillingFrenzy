package serverSide.gamemechanics;

import clientSide.Settings;
import org.junit.Test;

import static org.junit.Assert.*;

public class BulletTest {
    Bullet bullet = new Bullet();

    @Test
    public void testVelocity() throws Exception {
        float velocity = (float) Math.sqrt(Math.pow(bullet.getSpeedX(),2) * Math.pow(bullet.getSpeedY(),2));
        assertTrue(velocity < Settings.BULLET_SPEED + Settings.EPSILON && velocity <
                Settings.BULLET_SPEED - Settings.EPSILON);
    }

    @Test
    public void testDamagePlayer() throws Exception {
        bullet.damagePlayer(new Player(null));
        assertFalse(bullet.isAlive());
    }

    @Test
    public void testUpdate() throws Exception {
        //TODO
    }
}
