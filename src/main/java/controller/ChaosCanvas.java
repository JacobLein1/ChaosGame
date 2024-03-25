package controller;

import model.AffineTransform2D;
import model.Vector2D;

public class ChaosCanvas {
    private int width;
    private int height;
    private int[][] canvasArray;
    private final Vector2D minCoords;
    private final Vector2D maxCoords;
    private AffineTransform2D transformCoordsToIndices;

    public ChaosCanvas(int width, int height, Vector2D minCoords, Vector2D maxCoords){
        this.width = width;
        this.height = height;
        this.minCoords = minCoords;
        this.maxCoords = maxCoords;
        this.canvasArray = new int[width][height];
    }
    public int getPixel(Vector2D point){
        int x0 = (int) point.getX0();
        int x1= (int) point.getX1();

        return canvasArray[x0][x1];
    }
    public void putPixel(Vector2D point){
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
