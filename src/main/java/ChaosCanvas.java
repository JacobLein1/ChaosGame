import javafx.scene.transform.Affine;

public class ChaosCanvas {
    private int width;
    private int height;
    private Vector2D minCoords;
    private Vector2D maxCoords;
    private AffineTransform2D transformCoordsToIndices;

    public ChaosCanvas(int width, int height, Vector2D minCoords, Vector2D maxCoords){
        this.width = width;
        this.height = height;
        this.minCoords = minCoords;
        this.maxCoords = maxCoords;

    }
    public int getPixel(Vector2D point){
        return 0;
    }
    public void putPixel(Vector2D point){
        System.out.println("put pixel");
    }
    public int[][] getCanvasArray(){
        return new int[0][0];
    }
    public void clearCanvas(){
        System.out.println("clear canvas");
    }
}
