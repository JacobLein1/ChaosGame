package modelTests;

import model.Complex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ComplexTest {
    @Test
    void testAddComplex() {
        Complex a = new Complex(1, 2);
        Complex b = new Complex(3, 4);

        Assertions.assertEquals(1, a.getX0());
        Assertions.assertEquals(2, a.getX1());
        Assertions.assertEquals(3, b.getX0());
        Assertions.assertEquals(4, b.getX1());
    }

    @Test
    void testSqrtNegativeImaginary(){
        Complex a = new Complex(0.1,-0.4);
        Complex result = a.sqrt();

        double realPart = result.getX0();
        double imaginaryPart = result.getX1();

        Assertions.assertEquals(0.5061178531536732, realPart);
        Assertions.assertEquals(-0.3951648786024424, imaginaryPart);

    }
    @Test
    void testSqrtHighNegReal(){
        Complex a = new Complex(-100,2);
        Complex result = a.sqrt();

        double realPart =  result.getX0();
        double imaginaryPart = result.getX1();
        Assertions.assertEquals(0.09999500087478004, realPart);
        Assertions.assertEquals(10.000499937513121, imaginaryPart);
    }
    @Test
    void testToStringPositiveIM(){
    Complex a = new Complex(1,2);
    String result = a.complexToString();
    Assertions.assertEquals("1.0+2.0i", result);
    }
    @Test
    void testToStringNegativeIM(){
        Complex a = new Complex(1,-2);
        String result = a.complexToString();

        Assertions.assertEquals("1.0-2.0i", result);
    }

    @Test
    void testSqrtPositiveImaginary(){
        Complex a = new Complex(0.2,1.0);

        Complex result = a.sqrt();

        double realPart = result.getX0();

        double imaginaryPart = result.getX1();

        Assertions.assertEquals(0.7809621958579548, realPart);

        Assertions.assertEquals(0.6402358560400054, imaginaryPart);
        }
    @Test
    void testSqrtNegativeReal(){
        Complex a = new Complex(-1,2);
        Complex result = a.sqrt();

        double realPart =  result.getX0();
        double imaginaryPart = result.getX1();

        Assertions.assertEquals(0.7861513777574233, realPart);
        Assertions.assertEquals(1.272019649514069, imaginaryPart);
    }
    @Test
    void testSqrtZeroImaginary(){
        Complex a = new Complex(0.0,0.0);
        Complex result = a.sqrt();

        double realPart = result.getX0();
        double imaginaryPart = result.getX1();

        Assertions.assertEquals(0.0, realPart);
        Assertions.assertEquals(0.0, imaginaryPart);
    }

}