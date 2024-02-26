import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2DTest {
    @Test
    void testCreateVector() {
        Vector2D a = new Vector2D(1, 2);
        Vector2D b = new Vector2D(3, 4);

        assertEquals(a.getX0(),1);
        assertEquals(a.getX1(),2);
        assertEquals(b.getX0(),3);
        assertEquals(b.getX1(),4);
    }
    @Test
    void testSubtractVector() {
        Vector2D a = new Vector2D(1, -2);
        Vector2D b = new Vector2D(3, 4);
        Vector2D result = a.subtract(b);

        assertEquals(result.getX0(),-2);
        assertEquals(result.getX1(),-6);
    }
    @Test
    void testAddVector() {
        Vector2D a = new Vector2D(1, 2);
        Vector2D b = new Vector2D(-3, 4);
        Vector2D result = a.add(b);

        assertEquals(result.getX0(),-2);
        assertEquals(result.getX1(),6);
    }

}