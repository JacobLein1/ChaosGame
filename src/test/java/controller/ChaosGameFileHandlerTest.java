package controller;

import model.*;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChaosGameFileHandlerTest {
    ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(100, 100);
    Matrix2x2 matrix = new Matrix2x2(0, 0, 0, 0);
    Transform2D affineTransform2D = new AffineTransform2D(matrix, maxCoords);
    ArrayList<Transform2D> transforms = new ArrayList<>();
    ChaosGameDescription chaosGameDescriptionAffine = new ChaosGameDescription(minCoords, maxCoords, transforms);
    ChaosGame chaosGame = new ChaosGame(chaosGameDescriptionAffine, 100, 100);

    JuliaTransform juliaTransform = new JuliaTransform(new Complex(-0.74543, 0.11301), 1);

    @BeforeEach
    void setup(){
        Path pathToFile = Path.of("src/main/java/resources/transformationTest.txt");
        try {
            if (!Files.exists(pathToFile)) {
                Files.createFile(pathToFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @AfterEach
    void tearDown(){
        try{
            Files.deleteIfExists(Path.of("src/main/java/resources/transformationTest.txt"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void testWriteAffineFile() throws IOException {
        transforms.add(affineTransform2D);
        chaosGameFileHandler.writeToFile(chaosGameDescriptionAffine, "src/main/java/resources/transformationTest.txt");
        List<String> lines = Files.readAllLines(Path.of("src/main/java/resources/transformationTest.txt"));
        assertEquals("Affine2D                 #Type of transform", lines.get(0));
        assertEquals("0.0, 0.0                 #Lower left", lines.get(1));
        assertEquals("100.0, 100.0             #Upper right", lines.get(2));
        assertEquals("0.0, 0.0, 0.0, 0.0, 100.0, 100.0 #1st transform (a00, a01, a10, a11, b0, b1)", lines.get(3));
    }
    @Test
    public void testWriteAffineFileMultipleTransforms() throws IOException {
        AffineTransform2D affineTransform2D2 = new AffineTransform2D(new Matrix2x2(0.1, 0.1, 0.1, 0.1), new Vector2D(0.1, 0.1));
        AffineTransform2D affineTransform2D3 = new AffineTransform2D(new Matrix2x2(0.2, 0.2, 0.2, 0.2), new Vector2D(0.2, 0.2));
        AffineTransform2D affineTransform2D4 = new AffineTransform2D(new Matrix2x2(0.3, 0.3, 0.3, 0.3), new Vector2D(0.3, 0.3));
        AffineTransform2D affineTransform2D5 = new AffineTransform2D(new Matrix2x2(0.4, 0.4, 0.4, 0.4), new Vector2D(0.4, 0.4));
        transforms.add(affineTransform2D);
        transforms.add(affineTransform2D2);
        transforms.add(affineTransform2D3);
        transforms.add(affineTransform2D4);
        transforms.add(affineTransform2D5);

        chaosGameFileHandler.writeToFile(chaosGameDescriptionAffine, "src/main/java/resources/transformationTest.txt");
        List<String> lines = Files.readAllLines(Path.of("src/main/java/resources/transformationTest.txt"));
        assertEquals("Affine2D                 #Type of transform", lines.get(0));
        assertEquals("0.0, 0.0                 #Lower left", lines.get(1));
        assertEquals("100.0, 100.0             #Upper right", lines.get(2));
        assertEquals("0.0, 0.0, 0.0, 0.0, 100.0, 100.0 #1st transform (a00, a01, a10, a11, b0, b1)", lines.get(3));
        assertEquals("0.1, 0.1, 0.1, 0.1, 0.1, 0.1 #2nd transform (a00, a01, a10, a11, b0, b1)", lines.get(4));
        assertEquals("0.2, 0.2, 0.2, 0.2, 0.2, 0.2 #3rd transform (a00, a01, a10, a11, b0, b1)", lines.get(5));
        assertEquals("0.3, 0.3, 0.3, 0.3, 0.3, 0.3 #4th transform (a00, a01, a10, a11, b0, b1)", lines.get(6));
        assertEquals("0.4, 0.4, 0.4, 0.4, 0.4, 0.4 #5th transform (a00, a01, a10, a11, b0, b1)", lines.get(7));
    }
    @Test
    void testCleanString(){
        String testString = "Affine2D                 #Type of transform";
        assertEquals("Affine2D", chaosGameFileHandler.cleanString(testString));
    }
    @Test
    void testCleanString2(){
        String testString = "0.0, 0.0, 0.0, 0.0, 100.0, 100.0 #1st transform (a00, a01, a10, a11, b0, b1)";
        assertEquals("0.0, 0.0, 0.0, 0.0, 100.0, 100.0", chaosGameFileHandler.cleanString(testString));
    }
    @Test
    public void testReadAffineFile() throws Exception {
        transforms.add(affineTransform2D);
        chaosGameFileHandler.writeToFile(chaosGameDescriptionAffine, "src/main/java/resources/transformationTest.txt");
        chaosGame = chaosGameFileHandler.readTransformationFile("src/main/java/resources/transformationTest.txt");
        assertEquals(chaosGameDescriptionAffine.getMinCoords(),minCoords);
        assertEquals(chaosGameDescriptionAffine.getMaxCoords(), maxCoords);
        assertEquals(chaosGameDescriptionAffine.getTransforms().size(), transforms.size());
    }
    @Test
    public void testWriteJulia() throws Exception {
        transforms.add(juliaTransform);
        ChaosGameDescription chaosGameDescriptionJulia = new ChaosGameDescription(new Vector2D(-1.6,-1), new Vector2D(1.6,1), transforms);
        chaosGameFileHandler.writeToFile(chaosGameDescriptionJulia, "src/main/java/resources/transformationTest.txt");
        List<String> lines = Files.readAllLines(Path.of("src/main/java/resources/transformationTest.txt"));
        assertEquals("Julia                    #Type of transform", lines.get(0));
        assertEquals("-1.6, -1.0               #Lower left", lines.get(1));
        assertEquals("1.6, 1.0                 #Upper right", lines.get(2));
        assertEquals("-0.74543, 0.11301        #Real and imaginary parts of the constant c", lines.get(3));
    }
    @Test
    public void testReadJulia() throws Exception {
        transforms.add(juliaTransform);
        ChaosGameDescription chaosGameDescriptionJulia = new ChaosGameDescription(new Vector2D(-1.6,-1), new Vector2D(1.6,1), transforms);
        chaosGameFileHandler.writeToFile(chaosGameDescriptionJulia, "src/main/java/resources/transformationTest.txt");
        chaosGame = chaosGameFileHandler.readTransformationFile("src/main/java/resources/transformationTest.txt");
        assertEquals(chaosGame.getCanvas().getMinCoords().getX0(), -1.6);
        assertEquals(chaosGame.getCanvas().getMinCoords().getX1(), -1);
        assertEquals(chaosGame.getCanvas().getMaxCoords().getX0(), 1.6);
        assertEquals(chaosGame.getCanvas().getMaxCoords().getX1(), 1);
        assertEquals(chaosGameDescriptionJulia.getTransforms().size(), transforms.size());
    }
}