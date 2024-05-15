package model;

import model.Complex;

import java.lang.Math;

public class JuliaTransform implements Transform2D {
    private Complex point;
    private int sign;

    public JuliaTransform(Complex point, int sign){
        this.point = point;
        this.sign = sign;
    }


    /**
     * Julia transformation j*sqrt(z-c) where j represents the field sign, which is either 1 or -1,
     * z is the vector parameter and c is the field point
     *
     * @param point The vector c
     * @return      Returns a vector
     */
    @Override
    public Vector2D Transform(Vector2D point) {
        Vector2D vector = point.subtract(this.point);
        Complex radicand = new Complex(vector.getX0(), vector.getX1());
        return new Vector2D(radicand.sqrt().getX0()*Math.signum(sign), radicand.sqrt().getX1()*Math.signum(sign));
    }
    public Complex getPoint(){
        return point;
    }
}
