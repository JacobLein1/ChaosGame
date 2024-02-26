import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Matrix2x2Test {
    @Test
    void testAddMatrix() {
        Matrix2x2 a = new Matrix2x2(1, 2, 3, 4);

        Assertions.assertEquals(a.geta00(),1);
        Assertions.assertEquals(a.geta01(),2);
        Assertions.assertEquals(a.geta10(),3);
        Assertions.assertEquals(a.geta11(),4);
    }
    @Test
    void testMultiplyMatrix() {
        Matrix2x2 a = new Matrix2x2(1, 2, 3, 4);
        Vector2D b = new Vector2D(1, 2);
        Vector2D result = a.multiply(b);

        Assertions.assertEquals(result.getX0(),5);
        Assertions.assertEquals(result.getX1(),11);
    }
    @Test
    void testMultiplyMatrixNegative(){
        Matrix2x2 a = new Matrix2x2(-1, -2, -3, -4);
        Vector2D b = new Vector2D(1, 2);
        Vector2D result = a.multiply(b);

        Assertions.assertEquals(result.getX0(),-5);
        Assertions.assertEquals(result.getX1(),-11);
    }
}