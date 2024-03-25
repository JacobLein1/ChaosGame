import controller.ChaosCanvas;
import controller.ChaosGame;
import controller.ChaosGameDescription;
import model.AffineTransform2D;
import model.Matrix2x2;
import model.Transform2D;
import model.Vector2D;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Vector2D minCoords = new Vector2D(0, 0);
        Vector2D maxCoords = new Vector2D(100, 100);
        Matrix2x2 matrix = new Matrix2x2(0, 0, 0, 0);
        Transform2D affineTransform2D = new AffineTransform2D(matrix, maxCoords);
        ArrayList<Transform2D> transforms = new ArrayList<>();
        transforms.add(affineTransform2D);

        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(minCoords, maxCoords, transforms);
        ChaosGame chaosGame = new ChaosGame(chaosGameDescription, 100, 100);
        chaosGame.runSteps(10000);

        ChaosCanvas canvas = chaosGame.getCanvas();
        for (int i = 0; i < chaosGame.getCanvas().getCanvasArray().length; i++) {
            String result = "";
            for (int j = 0; j < chaosGame.getCanvas().getCanvasArray()[i].length; j++) {
                if (chaosGame.getCanvas().getCanvasArray()[i][j] == 1) {
                    result = result + "*";
                } else {
                    result = result + " ";
                }
            }
            System.out.println(result);

        }
        //System.out.println(result.toString());
    }


}