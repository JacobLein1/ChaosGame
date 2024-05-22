package controller;

import model.*;
import org.junit.jupiter.api.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The Class Chaos game file handler test.
 */
class ChaosGameFileHandlerTest {
    /**
     * The Chaos game file handler.
     */
    ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
    /**
     * The Min coords.
     */
    Vector2D minCoords = new Vector2D(0, 0);
    /**
     * The Max coords.
     */
    Vector2D maxCoords = new Vector2D(100, 100);
    /**
     * The Matrix.
     */
    Matrix2x2 matrix = new Matrix2x2(0, 0, 0, 0);
    /**
     * The Affine transform 2 d.
     */
    Transform2D affineTransform2D = new AffineTransform2D(matrix, maxCoords);
    /**
     * The Transforms.
     */
    ArrayList<Transform2D> transforms = new ArrayList<>();
    /**
     * The Chaos game description affine.
     */
    ChaosGameDescription chaosGameDescriptionAffine = new ChaosGameDescription(minCoords, maxCoords, transforms);
    /**
     * The Chaos game.
     */
    ChaosGame chaosGame = new ChaosGame(chaosGameDescriptionAffine, 100, 100);

    /**
     * The Julia transform.
     */
    JuliaTransform juliaTransform = new JuliaTransform(new Complex(-0.74543, 0.11301), 1);

    /**
     * Setup. Create a file to write to
     */
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

    /**
     * Tear down. Delete the file after each test
     */
    @AfterEach
    void tearDown(){
        try{
            Files.deleteIfExists(Path.of("src/main/java/resources/transformationTest.txt"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Test write affine file.
     *
     * @throws IOException the io exception
     */

    /*@Test
    public void testWriteAffineFile() throws IOException {
        transforms.add(affineTransform2D);
        chaosGameFileHandler.writeToFile(chaosGameDescriptionAffine, "src/main/java/resources/transformationTest.txt");
        List<String> lines = Files.readAllLines(Path.of("src/main/java/resources/transformationTest.txt"));
        assertEquals("Affine2D                 #Type of transform", lines.get(0));
        assertEquals("0.0, 0.0                 #Lower left", lines.get(1));
        assertEquals("100.0, 100.0             #Upper right", lines.get(2));
        assertEquals("0.0, 0.0, 0.0, 0.0, 100.0, 100.0 #1st transform (a00, a01, a10, a11, b0, b1)", lines.get(3));
    }

    /**
     * Test write affine file multiple transforms.
     *
     * @throws IOException the io exception
     */
    /*@Test
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
    }*/

    /**
     * Test clean string.
     */
    @Test
    void testCleanString(){
        String testString = "Affine2D                 #Type of transform";
        assertEquals("Affine2D", chaosGameFileHandler.cleanString(testString));
    }

    /**
     * Test clean string 2.
     */
    @Test
    void testCleanString2(){
        String testString = "0.0, 0.0, 0.0, 0.0, 100.0, 100.0 #1st transform (a00, a01, a10, a11, b0, b1)";
        assertEquals("0.0, 0.0, 0.0, 0.0, 100.0, 100.0", chaosGameFileHandler.cleanString(testString));
    }

    /**
     * Test read affine file.
     *
     * @throws Exception if the file is not found
     */
    /*@Test
    public void testReadAffineFile() throws Exception {
        transforms.add(affineTransform2D);
        chaosGameFileHandler.writeToFile(chaosGameDescriptionAffine, "src/main/java/resources/transformationTest.txt");
        chaosGame = new ChaosGame(chaosGameFileHandler.readTransformationFile("src/main/java/resources/transformationTest.txt"),100,100);
        assertEquals(chaosGameDescriptionAffine.getMinCoords(),minCoords);
        assertEquals(chaosGameDescriptionAffine.getMaxCoords(), maxCoords);
        assertEquals(chaosGameDescriptionAffine.getTransforms().size(), transforms.size());
    }*/

    /**
     * Test write julia.
     *
     * @throws Exception if the file is not found
     */
    /*@Test
    public void testWriteJulia() throws Exception {
        transforms.add(juliaTransform);
        ChaosGameDescription chaosGameDescriptionJulia = new ChaosGameDescription(new Vector2D(-1.6,-1), new Vector2D(1.6,1), transforms);
        chaosGameFileHandler.writeToFile(chaosGameDescriptionJulia, "src/main/java/resources/transformationTest.txt");
        List<String> lines = Files.readAllLines(Path.of("src/main/java/resources/transformationTest.txt"));
        assertEquals("Julia                    #Type of transform", lines.get(0));
        assertEquals("-1.6, -1.0               #Lower left", lines.get(1));
        assertEquals("1.6, 1.0                 #Upper right", lines.get(2));
        assertEquals("-0.74543, 0.11301        #Real and imaginary parts of the constant c", lines.get(3));
    }*/

    /**
     * Test read julia.
     *
     * @throws Exception if the file is not found
     */
    /*@Test
    public void testReadJulia() throws Exception {
        transforms.add(juliaTransform);
        ChaosGameDescription chaosGameDescriptionJulia = new ChaosGameDescription(new Vector2D(-1.6,-1), new Vector2D(1.6,1), transforms);
        chaosGameFileHandler.writeToFile(chaosGameDescriptionJulia, "src/main/java/resources/transformationTest.txt");
        chaosGame = new ChaosGame(chaosGameFileHandler.readTransformationFile("src/main/java/resources/transformationTest.txt"),100,100);
        assertEquals(chaosGame.getCanvas().getMinCoords().getX0(), -1.6);
        assertEquals(chaosGame.getCanvas().getMinCoords().getX1(), -1);
        assertEquals(chaosGame.getCanvas().getMaxCoords().getX0(), 1.6);
        assertEquals(chaosGame.getCanvas().getMaxCoords().getX1(), 1);
        assertEquals(chaosGameDescriptionJulia.getTransforms().size(), transforms.size());
    }*/

    /**
     * Test read julia with file.
     *
     * @throws Exception if the file is not found
     */
    /*@Test
    public void testReadJuliaWithFile() throws Exception {
        BufferedWriter bufferedWriter = new BufferedWriter(Files.newBufferedWriter(Path.of("src/main/java/resources/transformationTest.txt")));
        bufferedWriter.write("Julia                    #Type of transform\n");
        bufferedWriter.write("-1.6, -1.0               #Lower left\n");
        bufferedWriter.write("1.6, 1.0                 #Upper right\n");
        bufferedWriter.write("-0.74543, 0.11301        #Real and imaginary parts of the constant c\n");
        bufferedWriter.close();

        chaosGame = new ChaosGame(chaosGameFileHandler.readTransformationFile("src/main/java/resources/transformationTest.txt"),100,100);
        assertEquals(chaosGame.getCanvas().getMinCoords().getX0(), -1.6);
        assertEquals(chaosGame.getCanvas().getMinCoords().getX1(), -1);
        assertEquals(chaosGame.getCanvas().getMaxCoords().getX0(), 1.6);
        assertEquals(chaosGame.getCanvas().getMaxCoords().getX1(), 1);
        assertEquals(chaosGame.getCanvas().getCanvasArray().length, 100);
        assertEquals(chaosGame.getCanvas().getCanvasArray()[0].length, 100);
    }*/

    /**
     * Test julia with file negative.
     *
     * @throws IOException the io exception if the file is not found
     */
    /*@Test
    public void testJuliaWithFileNegative() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(Files.newBufferedWriter(Path.of("src/main/java/resources/transformationTest.txt")));
        bufferedWriter.write("Should give an error\n");
        bufferedWriter.close();
        Exception exception = assertThrows(Exception.class, () -> {
            chaosGameFileHandler.readTransformationFile("src/main/java/resources/transformationTest.txt");
        });
        assertEquals("java.lang.IllegalArgumentException: Invalid transformation file format, check the file and try again.", exception.getMessage());
    }*/

    /**
     * Tests the julia parts with explicit values before string is cleaned
     */
    @Test
    public void testJuliaPartsExplicitFalse(){
        List<String> juliaParts = List.of("Julia               # Type of transform",
                "-1.6, -1            # Lower left",
                "1.6, 1              # Upper right",
                "-.74543, .11301     # Real and imaginary parts of the constant c");
        assertFalse(chaosGameFileHandler.validateTransformationFile(juliaParts));
    }
    @Test
    public void testJuliaPartsCleaned(){
        List<String> juliaParts = List.of("Julia", "-1.6, -1.0", "1.6, 1.0", "-0.74543, 0.11301");
        assertTrue(chaosGameFileHandler.validateTransformationFile(juliaParts));

    }

    /**
     * Test valid julia parts.
     */
    @Test
    public void testValidJuliaParts(){
        List<String> juliaParts = List.of("Julia", "0.0, 0.0", "0.0, 0.0", "100.0, 100.0");
        assertTrue(chaosGameFileHandler.validateTransformationFile(juliaParts));

    }

    /**
     * Test julia parts too short.
     */
    @Test
    public void testJuliaPartsTooShort(){
        List<String> juliaPartsShort = List.of("Julia", "0.0, 0.0", "0.0, 0.0");
        assertFalse(chaosGameFileHandler.validateTransformationFile(juliaPartsShort));
    }

    /**
     * Test julia parts too long.
     */
    @Test
    public void testJuliaPartsTooLong(){
        List<String> juliaPartsLong = List.of("Julia", "0.0, 0.0", "0.0, 0.0", "100.0, 100.0", "0.0, 0.0");
        assertFalse(chaosGameFileHandler.validateTransformationFile(juliaPartsLong));
    }

    /**
     * Test julia parts invalid format.
     */
    @Test
    public void testJuliaPartsInvalidFormat(){
        List<String> juliaPartsInvalidFormat = List.of("Julia", "0.0, 0.0", "0.0,0.0,0.0", "100.0, 100.0");
        assertFalse(chaosGameFileHandler.validateTransformationFile(juliaPartsInvalidFormat));
    }

    /**
     * Test valid affine parts.
     */
    @Test
    public void testValidAffinePartsBeforeClean(){
        List<String> affineParts = List.of("Affine2D                # Type of transform",
                "0, 0                    # Lower left",
                "1, 1                    # Upper right",
                ".5, 0, 0, .5, 0, 0      # 1st transform (a00, a01, a10, a11, b0, b1)",
                ".5, 0, 0, .5, .25, .5   # 2nd transform",
                ".5, 0, 0, .5, .5, 0     # 3rd transform");
        assertFalse(chaosGameFileHandler.validateTransformationFile(affineParts));
    }

    @Test
    public void testAffinePartsInvalidFormat(){
        List<String> affineParts = List.of("Affine2D", "0.0", "1, 1", ".5, 0, 0, .5, 0, 0", ".5, 0, 0, .5, .25, .5", ".5, 0, 0, .5, .5, 0");
        assertTrue(chaosGameFileHandler.validateTransformationFile(affineParts));
    }
    @Test
    public void testAffinePartTooShort(){
        List<String> affineParts = List.of("Affine2D", "0.0", "1, 1");
        assertFalse(chaosGameFileHandler.validateTransformationFile(affineParts));
    }
    @Test
    public void testAffinePartElementTooShort() {
        List<String> affineParts = List.of("Affine2D", "0.0", "1, 1", ".5, 0, 0, .5, 0, 0", ".5, 0, 0, .5, .25, .5", ".5, 0, 0, .5, .5");
        assertFalse(chaosGameFileHandler.validateTransformationFile(affineParts));
    }
    @Test
    public void testInvalidAffineLineValues() {
        List<String> affineParts = List.of("Affine2D", "0.0", "0.00", "0.0, 0.0, not_a_number, 0.0, 0.0, 0.0"); // Non-numeric value in the affine line
        assertFalse(chaosGameFileHandler.validateTransformationFile(affineParts));
    }
    @Test
    public void testNonAffine2DString() {
        List<String> notAffineParts = List.of("NonAffine2D", "some", "other", "0.0, 0.0, 0.0, 0.0, 0.0, 0.0");
        assertFalse(chaosGameFileHandler.validateTransformationFile(notAffineParts));
    }


}