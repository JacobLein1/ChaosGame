import java.util.ArrayList;
import java.util.List;

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
        chaosGame.runSteps(100);

        System.out.println(chaosGame.getCanvas());
    }

}