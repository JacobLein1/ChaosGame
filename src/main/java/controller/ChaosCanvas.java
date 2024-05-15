package controller;

import model.AffineTransform2D;
import model.Matrix2x2;
import model.Vector2D;
import java.lang.Math;

public class ChaosCanvas {
    private final int width;
    private final int height;
    private final int[][] canvasArray;
    private final Vector2D minCoords;
    private final Vector2D maxCoords;
    private final AffineTransform2D transformCoordsToIndices;

    public ChaosCanvas(int width, int height, Vector2D minCoords, Vector2D maxCoords){
        this.width = width;
        this.height = height;
        this.minCoords = minCoords;
        this.maxCoords = maxCoords;
        this.canvasArray = new int[width+1][height+1];

        double Ax1 = (this.width-1)/(minCoords.getX1()-maxCoords.getX1());
        double Ax2 = (this.height-1)/(maxCoords.getX0()-minCoords.getX0());
        double b1 = ((this.width-1)*maxCoords.getX1()) / (maxCoords.getX1()-minCoords.getX1());
        double b2 = ((this.height-1)*minCoords.getX0())/(minCoords.getX0()-maxCoords.getX0());
        Matrix2x2 matrixA = new Matrix2x2(0, Ax1, Ax2, 0);
        Vector2D b = new Vector2D(b1, b2);
        this.transformCoordsToIndices = new AffineTransform2D(matrixA, b);

    }
    public int getPixel(Vector2D point){
        point = transformCoordsToIndices.Transform(point);
        int x0 = (int) point.getX0();
        int x1= (int) point.getX1();

        return canvasArray[x0][x1];
    }
    public void putPixel(Vector2D point){
        point = transformCoordsToIndices.Transform(point);
        int x0 = (int) point.getX0();
        int x1= (int) point.getX1();
        canvasArray[x0][x1] = 1;
    }
    public int[][] getCanvasArray(){
        return canvasArray;
    }
    public void clearCanvas(){
        for (int i = 0; i < canvasArray.length; i++) {

            for (int j = 0; j < canvasArray[i].length; j++) {
                canvasArray[i][j] = 0;
            }
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < canvasArray.length; i++) {
            for (int j = 0; j < canvasArray[i].length; j++) {
                sb.append(canvasArray[i][j]).append(" "); // Legg til matriseelement og mellomrom
            }
            sb.append("\n"); // Legg til linjeskift etter hver rad
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
