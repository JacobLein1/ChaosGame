package controller;

import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ChaosGameFileHandler {
    public ChaosGameFileHandler(){}; //Constructor
    public ChaosGame readTransformationFile(String path) throws Exception{
        ChaosGame chaosGame = null;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line = "";
            String transformationType = "";
            List<Transform2D> transformList = new ArrayList<>();
            List<String> parts = new ArrayList<>();
            while( ( line = bufferedReader.readLine()) != null){
                parts.add(line.trim());
            }

            if(validateTransformationFile(parts)) {

                if (parts.get(0).equals("Affine2D")) {
                    transformationType = "Affine2D";
                    for(int i = 3; i < parts.size(); i++){
                        String[] AffineLine = parts.get(i).split(",");

                        AffineTransform2D affineTransform2D = setAffineTransformation(AffineLine);
                        transformList.add(affineTransform2D);
                    }
                    String[] matrixLine = parts.get(3).split(",");
                    Matrix2x2 matrixA = new Matrix2x2(Double.parseDouble(matrixLine[0].trim()), Double.parseDouble(matrixLine[1].trim()), Double.parseDouble(matrixLine[2].trim()), Double.parseDouble(matrixLine[3].trim()));
                    Vector2D vectorB = new Vector2D(Double.parseDouble(parts.get(4).trim()), Double.parseDouble(parts.get(5).trim()));

                    AffineTransform2D affineTransform2D = new AffineTransform2D(matrixA, vectorB);
                    transformList.add(affineTransform2D);
                }
                else if (parts.get(0).equals("Julia")) {
                    transformationType = "Julia";
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

                } else {
                    throw new IllegalArgumentException("Invalid transformation type");
                }
            }
                String[] minCoordsLine = parts.get(1).split(",");
                String[] maxCoordsLine = parts.get(2).split(",");
                Vector2D minCoords = new Vector2D(Double.parseDouble(minCoordsLine[0].trim()), Double.parseDouble(minCoordsLine[1].trim()));
                Vector2D maxCoords = new Vector2D(Double.parseDouble(maxCoordsLine[0].trim()), Double.parseDouble(maxCoordsLine[1].trim()));
                ChaosGameDescription chaosGameDescription = new ChaosGameDescription(minCoords, maxCoords, transformList);
                chaosGame = new ChaosGame(chaosGameDescription, (int) maxCoords.getX0(), (int) maxCoords.getX1());
                bufferedReader.close();

        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }

        return chaosGame;
    }
    public boolean validateTransformationFile(List<String> parts) {
        boolean result = true;
        for (int i = 0; i < parts.size(); i++) {
          if(parts.get(i).isEmpty()){
              result = false;
          }
        }
        return result;
    }

    public AffineTransform2D setAffineTransformation(String[] AffineLine) {
        return new AffineTransform2D(new Matrix2x2(Double.parseDouble(AffineLine[0]), Double.parseDouble(AffineLine[1]), Double.parseDouble(AffineLine[2]), Double.parseDouble(AffineLine[3])), new Vector2D(Double.parseDouble(AffineLine[4]), Double.parseDouble(AffineLine[5])));
    }


}

