package model;

/**
 * The type Affine transform 2D.
 */
public class AffineTransform2D implements Transform2D {
    private Matrix2x2 matrix;
    private Vector2D vector;

    /**
     * Instantiates a new Affine transform 2D.
     *
     * @param matrix the matrix
     * @param vector the vector
     */
    public AffineTransform2D(Matrix2x2 matrix, Vector2D vector){
        this.matrix = matrix;
        this.vector = vector;
    }

    /**
     * Affine transformation Ax+b, where A is the field matrix and b is the field vector and x is the parameter
     *
     * @param point The vector x
     * @return      Returns a vector
     */
    @Override
    public Vector2D Transform(Vector2D point) {
        return matrix.multiply(point).add(vector);
    }

    /**
     * Matrix and vector as string
     *
     * @return Returns matrix and vector as string
     */
    @Override
    public String toString(){
        return matrix.geta00() + ", " + matrix.geta01() + ", " + matrix.geta10()+ ", " + matrix.geta11() + ", " + vector.getX0() + ", " + vector.getX1();
    }
}
