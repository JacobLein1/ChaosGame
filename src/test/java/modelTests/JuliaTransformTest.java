package modelTests;

import jdk.jfr.Description;
import model.Complex;
import model.JuliaTransform;
import model.Vector2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JuliaTransformTest {

    @Test
    @Description("Tests the transform method for a positive vector z")
    void testTransformVector(){
        JuliaTransform juliaTest = new JuliaTransform(new Complex(1, 3), -1);
        Vector2D testResult = juliaTest.Transform(new Vector2D(2, 4));
        Assertions.assertEquals(-1.09868411346781, testResult.getX0());
        Assertions.assertEquals(-0.4550898605622274, testResult.getX1());
    }

    @Test
    @Description("Tests the transform method for a negative vector z")
    void testTransformNegativeVector(){
        JuliaTransform juliaTest = new JuliaTransform(new Complex(1, 3), 1);
        Vector2D testResult = juliaTest.Transform(new Vector2D(-3, -1));
        Assertions.assertEquals(0.9101797211244548, testResult.getX0());
        Assertions.assertEquals(-2.19736822693562, testResult.getX1());
    }

    @Test
    @Description("Tests the transformation method for a high numbered vector z")
    void testTransformExtremeVector(){
        JuliaTransform juliaTest = new JuliaTransform(new Complex(2, 8), -1);
        Vector2D testResult = juliaTest.Transform(new Vector2D(10334, 45633));
        Assertions.assertEquals(-168.9855614271049, testResult.getX0());
        Assertions.assertEquals(-134.99674059337076, testResult.getX1());
    }
}
