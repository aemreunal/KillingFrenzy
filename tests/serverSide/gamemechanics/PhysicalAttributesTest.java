package serverSide.gameMechanics;

import org.junit.Test;

import static org.junit.Assert.*;

public class PhysicalAttributesTest {
    PhysicalAttributes attr = new PhysicalAttributes(10, 20,20, 20);

    @Test
    public void testUpdateVerticalPosition() throws Exception {
        attr.updateVerticalPosition(10.5f);
        assertEquals(30.5f, attr.top, 0.005f);
        assertEquals(50.5f, attr.bottom, 0.005f);
    }

    @Test
    public void testUpdateHorizontalPosition() throws Exception {
        attr.updateHorizontalPosition(10.5f);
        assertEquals(20.5f, attr.left, 0.005f);
        assertEquals(40.5f, attr.right, 0.005f);
    }
}
