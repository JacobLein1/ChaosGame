package controller;

import model.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChaosGameFileHandler {
    public ChaosGameFileHandler(){} //Constructor

    /**
     * Clean string method. Removes all characters after # in a string.
     *
     * @param s the string wanting to be cleaned
     * @return the string
     */
    public static String cleanString(String s) {
        int unwantedIndex = s.indexOf("#");

        if (unwantedIndex == -1) {
            return s.strip();
        } else {
            return s.substring(0, unwantedIndex).strip();
        }
    }
    public ChaosGameDescription readTransformationFile(String path) throws Exception{
        ChaosGameDescription chaosGameDescription = null;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line = "";
            List<Transform2D> transformList = new ArrayList<>();
            List<String> parts = new ArrayList<>();

            //Reads the file and adds each line to the list parts, skipping all characters after # (comments)
            while( ( line = bufferedReader.readLine()) != null){
                int index = line.indexOf("#");
                if (index != -1) {
                    line = line.substring(0, index);
                }
                parts.add(line.trim());
            }

            //Clause for Affine transformation
            if(validateTransformationFile(parts)) {
                if (cleanString(parts.get(0)).equals("Affine2D")) {

                    for(int i = 3; i < parts.size(); i++){
                        String[] affineLine = parts.get(i).split(",");
                        AffineTransform2D affineTransform2D = setAffineTransformation(affineLine);
                        transformList.add(affineTransform2D);
                    }

                    String[] matrixLine = parts.get(3).split(",");
                    Matrix2x2 matrixA = new Matrix2x2(Double.parseDouble(matrixLine[0].trim()), Double.parseDouble(matrixLine[1].trim()), Double.parseDouble(matrixLine[2].trim()), Double.parseDouble(matrixLine[3].trim()));
                    Vector2D vectorB = new Vector2D(Double.parseDouble(matrixLine[4].trim()), Double.parseDouble(matrixLine[5].trim()));

                    AffineTransform2D affineTransform2D = new AffineTransform2D(matrixA, vectorB);
                    transformList.add(affineTransform2D);
                }

                //Clause for Julia transformation
                else if (cleanString(parts.get(0)).equals("Julia")) {
                    String[] complexNumberLine = parts.get(2).split(",");

                    double complexNumberLineReal = Double.parseDouble(complexNumberLine[0].trim());
                    double complexNumberLineImaginary = Double.parseDouble(complexNumberLine[1].trim());


                    Complex complexNumberC = new Complex(complexNumberLineReal, complexNumberLineImaginary);
                    //Check if real part of complex number is negative or positive
                    int sign = 0;
                    if (complexNumberLineReal < 0) {
                        sign = -1;
                    }
                    if (complexNumberLineReal > 0) {
                        sign = 1;
                    }
                    JuliaTransform juliaTransform = new JuliaTransform(complexNumberC, sign);
                    transformList.add(juliaTransform);
                }

                else {
                    throw new IllegalArgumentException("Invalid transformation type");
                }
            } else {
                throw new IllegalArgumentException("Invalid transformation file format, check the file and try again.");
            }
                String[] minCoordsLine = parts.get(1).split(",");
                String[] maxCoordsLine = parts.get(2).split(",");
                Vector2D minCoords = new Vector2D(Double.parseDouble(minCoordsLine[0].trim()), Double.parseDouble(minCoordsLine[1].trim()));
                Vector2D maxCoords = new Vector2D(Double.parseDouble(maxCoordsLine[0].trim()), Double.parseDouble(maxCoordsLine[1].trim()));
                chaosGameDescription = new ChaosGameDescription(minCoords, maxCoords, transformList);

        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
        return chaosGameDescription;
    }
    public boolean validateTransformationFile(List<String> parts) {
        boolean result = true;
        List<String> validTypes = Arrays.asList("Affine2D", "Julia", "Barnsley");
        if (!validTypes.contains(cleanString(parts.get(0)))) {
            return false;
        }
        if (cleanString(parts.get(0)).equals("Affine2D")) {
            if (parts.size() < 4) {
                return false;
            }
            for (int i = 3; i < parts.size(); i++) {
                String[] affineLine = parts.get(i).split(",");
                if (affineLine.length != 6) {
                    return false;
                }
                for (int j = 0; j < affineLine.length; j++) {
                    try {
                        Double.parseDouble(affineLine[j].trim());
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }
            }
        }
        if (cleanString(parts.get(0)).equals("Julia")) {
            if (parts.size() != 4) {
                return false;
            }
            String[] complexNumberLine = parts.get(2).split(",");
            if (complexNumberLine.length != 2) {
                return false;
            }
            for (int i = 0; i < complexNumberLine.length; i++) {
                try {
                    Double.parseDouble(complexNumberLine[i].trim());
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
        return result;
    }

    /**
     * Sets affine transformation.
     *
     * @param affineLine the affine line
     * @return the affine transformation
     */
    public AffineTransform2D setAffineTransformation(String[] affineLine) {
        return new AffineTransform2D(new Matrix2x2(Double.parseDouble(affineLine[0]), Double.parseDouble(affineLine[1]), Double.parseDouble(affineLine[2]), Double.parseDouble(affineLine[3])), new Vector2D(Double.parseDouble(affineLine[4]), Double.parseDouble(affineLine[5])));
    }


    /**
     * Write Chaosgame description to file.
     *
     * @param chaosGameDescription the chaos game description
     * @param path                 the path
     * @throws IOException the io exception
     */
    public void writeToFile(ChaosGameDescription chaosGameDescription, String path) throws IOException {
        Path filePath = Path.of(path);
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        List<Transform2D> transform2DList = chaosGameDescription.getTransforms();

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath)) {
            if (transform2DList.get(0) instanceof AffineTransform2D) {

                String affineLine = "Affine2D";
                affineLine = insertTextAtSpecificIndex(affineLine, "#Type of transform", 25);
                bufferedWriter.write(affineLine);
                bufferedWriter.newLine();

                String minCoordsLine = chaosGameDescription.getMinCoords().getX0() + ", " + chaosGameDescription.getMinCoords().getX1();
                minCoordsLine = insertTextAtSpecificIndex(minCoordsLine, "#Lower left", 25);
                bufferedWriter.write(minCoordsLine);
                bufferedWriter.newLine();

                String maxCoordsLine = chaosGameDescription.getMaxCoords().getX0() + ", " + chaosGameDescription.getMaxCoords().getX1();
                maxCoordsLine = insertTextAtSpecificIndex(maxCoordsLine, "#Upper right", 25);
                bufferedWriter.write(maxCoordsLine);

                //Loop through the list of transformations and write them to the file
                for (int i = 0; i < transform2DList.size(); i++) {
                    bufferedWriter.newLine();
                    AffineTransform2D currentTransformation = (AffineTransform2D) transform2DList.get(i);
                    String currentTransformationLine = currentTransformation.toString();

                    //Switch ensures correct numbering of transformations according to grammar
                    switch (i) {
                        case 0:
                            currentTransformationLine = insertTextAtSpecificIndex(currentTransformationLine, "#1st transform (a00, a01, a10, a11, b0, b1)", 25);
                            break;
                        case 1:
                            currentTransformationLine = insertTextAtSpecificIndex(currentTransformationLine, "#2nd transform (a00, a01, a10, a11, b0, b1)", 25);
                            break;
                        case 2:
                            currentTransformationLine = insertTextAtSpecificIndex(currentTransformationLine, "#3rd transform (a00, a01, a10, a11, b0, b1)", 25);
                            break;
                        default:
                            int currentnumber = i+1;
                            currentTransformationLine = insertTextAtSpecificIndex(currentTransformationLine, "#"+currentnumber+"th transform (a00, a01, a10, a11, b0, b1)", 25);
                            break;
                    }
                    bufferedWriter.write(currentTransformationLine);

                }
            }
            if (transform2DList.get(0) instanceof JuliaTransform) {
                String firstLine = "Julia";
                firstLine = insertTextAtSpecificIndex(firstLine, "#Type of transform", 25);
                bufferedWriter.write(firstLine);
                bufferedWriter.newLine();

                String minCoordsLine = chaosGameDescription.getMinCoords().getX0() + ", " + chaosGameDescription.getMinCoords().getX1();
                minCoordsLine = insertTextAtSpecificIndex(minCoordsLine, "#Lower left", 25);
                bufferedWriter.write(minCoordsLine);
                bufferedWriter.newLine();

                String maxCoordsLine = chaosGameDescription.getMaxCoords().getX0() + ", " + chaosGameDescription.getMaxCoords().getX1();
                maxCoordsLine = insertTextAtSpecificIndex(maxCoordsLine, "#Upper right", 25);
                bufferedWriter.write(maxCoordsLine);
                bufferedWriter.newLine();


                String juliaLine = ((JuliaTransform) transform2DList.get(0)).getPoint().getX0() + ", " + ((JuliaTransform) transform2DList.get(0)).getPoint().getX1();
                juliaLine = insertTextAtSpecificIndex(juliaLine, "#Real and imaginary parts of the constant c", 25);
                bufferedWriter.write(juliaLine);
            }
        }
        catch (IOException e) {
            throw new IOException("Error writing to file");
        }

    }

    /**
     * Insert text at specific index string. Adds " " to the original text until the index is reached, then inserts the text to insert.
     *
     * @param originalText the original text
     * @param textToInsert the text to insert
     * @param index        the index
     * @return the string
     */
    public String insertTextAtSpecificIndex(String originalText, String textToInsert, int index){
        if (index <= 0 ) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        StringBuilder result = new StringBuilder(originalText);

        if (originalText.length() > index){
            result.append(" " + textToInsert);

        } else {
            while (result.length() < index) {
                result.append(" ");
            }
            result.insert(index, textToInsert);
        }
        return result.toString();
    }

}

