package modelTests;

import jdk.jfr.Description;
import model.AffineTransform2D;
import model.Matrix2x2;
import model.Vector2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AffineTransform2DTest {

    @Test
    @Description("Tests the transform method for a positive vector x")
    void testTransformPositiveVector(){
        AffineTransform2D affineTest = new AffineTransform2D(new Matrix2x2(2, 4, 1, 6), new Vector2D(4, 2));
        Vector2D testResult = affineTest.Transform(new Vector2D(3, 9));
        Assertions.assertEquals(46, testResult.getX0());
        Assertions.assertEquals(59, testResult.getX1());
    }

    @Test
    @Description("Tests the transform method for a negative vector x")
    void testTransformNegativeVector(){
        AffineTransform2D affineTest = new AffineTransform2D(new Matrix2x2(2, 4, 1, 6), new Vector2D(4, 2));
        Vector2D testResult = affineTest.Transform(new Vector2D(-10, -5));
        Assertions.assertEquals(-36, testResult.getX0());
        Assertions.assertEquals(-38, testResult.getX1());
    }

    @Test
    @Description("Tests the transform method for a high numbered vector x")
    void testTransformExtremeVector(){
        AffineTransform2D affineTest = new AffineTransform2D(new Matrix2x2(2, 4, 1, 6), new Vector2D(4, 2));
        Vector2D testResult = affineTest.Transform(new Vector2D(2010, -333));
        Assertions.assertEquals(2692, testResult.getX0());
        Assertions.assertEquals(14, testResult.getX1());
    }

}
