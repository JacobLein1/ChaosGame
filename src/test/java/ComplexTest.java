import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplexTest {
    @Test
    void testAddComplex() {
        Complex a = new Complex(1, 2);
        Complex b = new Complex(3, 4);

        Assertions.assertEquals(a.getX0(),1);
        Assertions.assertEquals(a.getX1(),2);
        Assertions.assertEquals(b.getX0(),3);
        Assertions.assertEquals(b.getX1(),4);
    }
    @Test
    void testSqrtNegativeImaginary(){
        Complex a = new Complex(0.1,-0.4);
        Complex result = a.sqrt(0.1,-0.4);

        String realPart = String.format("%.5f", result.getX0());
        String imaginaryPart = String.format("%.5f", result.getX1());

        Assertions.assertEquals(realPart,"0.50612");
        Assertions.assertEquals(imaginaryPart,"-0.39516");

    }
    @Test
    void testSqrtHighNegReal(){
        Complex a = new Complex(-100,2);
        Complex result = a.sqrt(a.getX0(), a.getX1());

        String realPart = String.format("%.5f", result.getX0());
        String imaginaryPart = String.format("%.5f", result.getX1());
        Assertions.assertEquals(realPart,"0.10000");
        Assertions.assertEquals(imaginaryPart,"10.00050");


    }
    @Test
    void testToStringPositiveIM(){
    Complex a = new Complex(1,2);
    String result = a.complexToString();
    Assertions.assertEquals(result,"1.0+2.0i");
    }
    @Test
    void testToStringNegativeIM(){
        Complex a = new Complex(1,-2);
        String result = a.complexToString();

        Assertions.assertEquals(result,"1.0-2.0i");
    }

    @Test
    void testSqrtPositiveImaginary(){
        Complex a = new Complex(0.2,1.0);

        Complex result = a.sqrt(0.2,1.0);

        String realPart = String.format("%.5f", result.getX0());

        String imaginaryPart = String.format("%.5f", result.getX1());

        Assertions.assertEquals(realPart,"0.78096");

        Assertions.assertEquals(imaginaryPart,"0.64024");
        }
    @Test
    void testSqrtNegativeReal(){
        Complex a = new Complex(-1,2);
        Complex result = a.sqrt(a.getX0(),a.getX1());

        String realPart = String.format("%.5f", result.getX0());
        String imaginaryPart = String.format("%.5f", result.getX1());

        Assertions.assertEquals(realPart,"0.78615");
        Assertions.assertEquals(imaginaryPart,"1.27202");
    }
    @Test
    void testSqrtZeroImaginary(){
        Complex a = new Complex(0.0,0.0);
        Complex result = a.sqrt(0.0,0.0);

        String realPart = String.format("%.5f", result.getX0());
        String imaginaryPart = String.format("%.5f", result.getX1());

        Assertions.assertEquals(realPart,"0.00000");
        Assertions.assertEquals(imaginaryPart,"0.00000");
    }

}