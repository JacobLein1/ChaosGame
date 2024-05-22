package model;

import model.AffineTransform2D;
import model.Matrix2x2;
import model.Vector2D;

import java.util.Arrays;

/**
 * The type Chaos canvas.
 */
public class ChaosCanvas {
    private final int width;
    private final int height;
    private final int[][] canvasArray;
    private final Vector2D minCoords;
    private final Vector2D maxCoords;
    private final AffineTransform2D transformCoordsToIndices;

    /**
     * Instantiates a new Chaos canvas.
     *
     * @param width     the width
     * @param height    the height
     * @param minCoords the min coords
     * @param maxCoords the max coords
     */
    public ChaosCanvas(int width, int height, Vector2D minCoords, Vector2D maxCoords){
        this.width = width;
        this.height = height;
        this.minCoords = minCoords;
        this.maxCoords = maxCoords;
        this.canvasArray = new int[width][height];

        double Ax1 = (this.width -1)/(minCoords.getX1()-maxCoords.getX1());
        double Ax2 = (this.height -1)/(maxCoords.getX0()-minCoords.getX0());
        double b1 = ((this.width -1)*maxCoords.getX1()) / (maxCoords.getX1()-minCoords.getX1());
        double b2 = ((this.height -1)*minCoords.getX0())/(minCoords.getX0()-maxCoords.getX0());
        Matrix2x2 matrixA = new Matrix2x2(0, Ax1, Ax2, 0);
        Vector2D b = new Vector2D(b1, b2);
        this.transformCoordsToIndices = new AffineTransform2D(matrixA, b);

    }

    /**
     * Get pixel int.
     *
     * @param point the point
     * @return the value of the pixel at the given point
     */
    public int getPixel(Vector2D point){
        point = transformCoordsToIndices.Transform(point);
        int x0 = (int) point.getX0();
        int x1= (int) point.getX1();

        return canvasArray[x0][x1];
    }

    /**
     * Sets value of given point to 1 if it is within the canvas
     *
     * @param point the point
     */
    public void putPixel(Vector2D point){
        point = transformCoordsToIndices.Transform(point);
        if (point.getX0() >= 0 && point.getX0() < width && point.getX1() >= 0 && point.getX1() < height) {
            int x0 = (int) point.getX0();
            int x1 = (int) point.getX1();
            canvasArray[x0][x1] = 1;
        }
    }

    /**
     * Put pixel count increments the value of the pixel at the given point by 1 if it is within the canvas
     *
     * @param point the point
     */
    public void putPixelCount(Vector2D point){
        point = transformCoordsToIndices.Transform(point);
        if (point.getX0() >= 0 && point.getX0() < width && point.getX1() >= 0 && point.getX1() < height) {
            int x0 = (int) point.getX0();
            int x1 = (int) point.getX1();
            canvasArray[x0][x1] += 1;
        }
    }

    public int[][] getCanvasArray(){
        return canvasArray;
    }

    /**
     * Sets the value of all pixels in the canvas to 0
     */
    public void clearCanvas(){
        for (int[] ints : canvasArray) {
            Arrays.fill(ints, 0);
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] ints : canvasArray) {
            for (int anInt : ints) {
                sb.append(anInt).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public Vector2D getMinCoords() {
        return minCoords;
    }

    public Vector2D getMaxCoords() {
        return maxCoords;
    }
}
