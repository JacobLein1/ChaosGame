package model;

import java.lang.Math;

/**
 * Represents complex number.
 */
public class Complex extends Vector2D {
    public Complex(double realPart,double imaginaryPart){
        super(realPart,imaginaryPart);
    }

    /**
     * Calculates the square root of a complex number.
     *
     * @return a complex number that is square root of the complex number.
     */
    public Complex sqrt(){

        double realPart = super.getX0();
        double imaginaryPart = super.getX1();
    double resultReal;
    double resultImaginary;

    resultReal = Math.sqrt(0.5 * (Math.sqrt( Math.pow (realPart,2) + Math.pow(imaginaryPart,2) ) + realPart));

    resultImaginary = Math.sqrt(0.5 * (Math.sqrt( Math.pow(realPart,2) + Math.pow(imaginaryPart,2)) -realPart));

    if (imaginaryPart < 0){
        resultImaginary = -resultImaginary;
    }

    return new Complex(resultReal, resultImaginary);
    }
    /*
    Convert a complex number to a string
     */
    public String complexToString(){
        if (this.getX1() < 0){
            return Double.toString(this.getX0()) + Double.toString(this.getX1())+ "i";
        }

        else {
            return this.getX0() + "+" + this.getX1()+ "i";
        }
    }


}


