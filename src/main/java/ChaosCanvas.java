import javafx.scene.transform.Affine;

public class ChaosCanvas {
    private int[][] canvasArray;
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
        int x0 = Integer.parseInt(Double.toString(point.getX0()));
        int x1= Integer.parseInt(Double.toString(point.getX1()));

        return canvasArray[x0][x1];
    }
    public void putPixel(Vector2D point){
        int x0 = Integer.parseInt(Double.toString(point.getX0()));
        int x1= Integer.parseInt(Double.toString(point.getX1()));

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
}
