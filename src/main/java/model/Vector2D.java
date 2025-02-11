package model;

/**
 * The type Vector 2 d.
 */
public class  Vector2D {
    private double x0;
    private double x1;

    /**
     * Instantiates a new Vector 2D.
     *
     * @param x0 the first element of the vector
     * @param x1 the second element of the vector
     */
//Constructor
    public Vector2D(double x0, double x1) {
        this.x0 = x0;
        this.x1 = x1;
    }

    /**
     * Gets x0 of a vector.
     *
     * @return the first element of the vector
     */
    public double getX0() {
        return x0;
    }

    /**
     * Gets x1.
     *
     * @return the second element of the vector
     */
    public double getX1() {
        return x1;
    }

    /**
     * Add two vectors together
     *
     * @param other the other vector to add
     * @return the sum of the two vectors
     */
    public Vector2D add(Vector2D other){
        x0 = x0 + other.x0;
        x1= x1 + other.x1;
        return new Vector2D(x0, x1);
    }

    /**
     * Subtract two vectors
     *
     * @param other the other vector to subtract
     * @return the difference of the two vectors
     */
//Subtract two vectors
    //@param other - the other vector to subtract
    //return - the difference of the two vectors
    public Vector2D subtract(Vector2D other){
        x0 = x0 - other.x0;
        x1= x1 - other.x1;
        return new Vector2D(x0, x1);
    }

}
