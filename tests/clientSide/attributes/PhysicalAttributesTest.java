package clientSide.attributes;

/*
 * This code belongs to:
 * Ahmet Emre Unal
 * S001974
 * emre.unal@ozu.edu.tr
 */

import clientSide.packetHandlers.UpdateEntityHandler;
import global.Settings;
import org.junit.Test;
import packets.UpdateEntityPacket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PhysicalAttributesTest {

    @Test
    public void testUpdate() {
        PhysicalAttributes physAttr = new PhysicalAttributes();

        float newX = 10;
        float newY = 10;
        float newAngle = 10;
        boolean isMoving = true;

        physAttr.update(newX, newY, newAngle, isMoving);

        assertEquals(newX, physAttr.getxCoor(), Settings.TEST_ERROR_ALLOWANCE);
        assertEquals(newY, physAttr.getyCoor(), Settings.TEST_ERROR_ALLOWANCE);
        assertEquals(newAngle, physAttr.getAngle(), Settings.TEST_ERROR_ALLOWANCE);
        assertEquals(isMoving, physAttr.isMoving());
    }

    @Test
    public void testOwnPlayerUpdatePackageHandler() {
        World world = World.getInstance();
        assertNull(world.getEntity((int) (Math.random() * 10)));

        Player player = Player.createPlayer(true);
        world.addEntity(player, 0);
        assertEquals(world.getEntity(0), player);

        float oldX = 10;
        float oldY = 10;
        float oldAngle = 0.2f;
        boolean oldMoving = true;

        player.getPhysAttr().update(oldX, oldY, oldAngle, oldMoving);

        UpdateEntityHandler updateHandler = new UpdateEntityHandler();

        float newX = 10;
        float newY = 10;
        float newAngle = 0.2f;
        boolean newMoving = true;

        UpdateEntityPacket updatePacket = new UpdateEntityPacket(newX, newY, newAngle, newMoving);
        updatePacket.entityID = 0;
        updateHandler.handle(updatePacket);

        assertEquals(newX, player.getPhysAttr().getxCoor(), Settings.TEST_ERROR_ALLOWANCE);
        assertEquals(newY, player.getPhysAttr().getyCoor(), Settings.TEST_ERROR_ALLOWANCE);
        assertEquals(oldAngle, player.getPhysAttr().getAngle(), Settings.TEST_ERROR_ALLOWANCE);
        assertEquals(newMoving, player.getPhysAttr().isMoving());
    }

    @Test
    public void testOtherPlayerUpdatePackageHandler() {
        World world = World.getInstance();
        assertNull(world.getEntity((int) (Math.random() * 10)));

        Player.createPlayer(true); // Create own player

        Player otherPlayer = Player.createPlayer(false);
        world.addEntity(otherPlayer, 1);
        assertEquals(world.getEntity(1), otherPlayer);

        float oldX = 10;
        float oldY = 10;
        float oldAngle = 0.2f;
        boolean oldMoving = true;

        otherPlayer.getPhysAttr().update(oldX, oldY, oldAngle, oldMoving);

        UpdateEntityHandler updateHandler = new UpdateEntityHandler();

        float newX = 10;
        float newY = 10;
        float newAngle = 0.2f;
        boolean newMoving = true;

        UpdateEntityPacket updatePacket = new UpdateEntityPacket(newX, newY, newAngle, newMoving);
        updatePacket.entityID = 0;
        updateHandler.handle(updatePacket);

        assertEquals(newX, otherPlayer.getPhysAttr().getxCoor(), Settings.TEST_ERROR_ALLOWANCE);
        assertEquals(newY, otherPlayer.getPhysAttr().getyCoor(), Settings.TEST_ERROR_ALLOWANCE);
        assertEquals(newAngle, otherPlayer.getPhysAttr().getAngle(), Settings.TEST_ERROR_ALLOWANCE);
        assertEquals(newMoving, otherPlayer.getPhysAttr().isMoving());
    }
}
