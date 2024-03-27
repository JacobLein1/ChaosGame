package controllerTests;

import controller.ChaosGame;
import controller.ChaosGameDescription;
import model.AffineTransform2D;
import model.Matrix2x2;
import model.Transform2D;
import model.Vector2D;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class ChaosGameTest {

    private static final Vector2D minCoords = new Vector2D(0, 0);
    private static final Vector2D maxCoords = new Vector2D(99, 99);
    private static final Matrix2x2 matrixA = new Matrix2x2(0.5, 0, 0, 0.5);
    private static final Vector2D vectorB = new Vector2D(99, 0);
    private static final List<Transform2D> transform2DList = List.of(new AffineTransform2D(matrixA, vectorB));
    private static final ChaosGameDescription chaosGameDescription = new ChaosGameDescription(minCoords, maxCoords, transform2DList);
    ChaosGame chaosGame = new ChaosGame(chaosGameDescription, 50, 100);
    @Test
    void testCreateChaosGame() {
        chaosGame = new ChaosGame(chaosGameDescription, 50, 100);
        assertNotNull(chaosGame);
    }

}