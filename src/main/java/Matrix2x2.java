public class Matrix2x2 {
    private double a00;
    private double a01;
    private double a10;
    private double a11;

    //Constructor
    Matrix2x2(double a00, double a01, double a10, double a11){
        this.a00 = a00;
        this.a01 = a01;
        this.a10 = a10;
        this.a11 = a11;
    }
    //method for multiplying a matrix by a vector
    //@param other - the vector to multiply by
    //return - the product of the matrix and the vector (a vector)
    public Vector2D multiply(Vector2D other){
        double ax0 = a00*other.getX0() + a01*other.getX1();
        double ax1 = a10*other.getX0() + a11*other.getX1();
        return new Vector2D(ax0, ax1);
    }

    public double geta00(){
        return a00;
    }
    public double geta01(){
        return a01;
    }
    public double geta10(){
        return a10;
    }
    public double geta11(){
        return a11;
    }

}
