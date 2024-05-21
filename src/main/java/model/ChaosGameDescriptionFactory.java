package model;


import controller.ChaosGameDescription;

import java.util.ArrayList;
import java.util.List;

public class ChaosGameDescriptionFactory {

    private static Vector2D minCoords;
    private static Vector2D maxCoords;
    //static Complex complexNumberC = new Complex(-0.74543, 0.11301);
    //static JuliaTransform juliaTransform = new JuliaTransform(complexNumberC,-1);

    public ChaosGameDescriptionFactory(){}; //Constructor
    public static ChaosGameDescription get(String transformationType) throws IllegalArgumentException{

        return switch(transformationType){
            case "Affine2D" -> affineTransform();
            case "Barnsley" -> barnsley();
            case "Julia" -> julia();
            default -> throw new IllegalArgumentException("Invalid transformation type");
        };

    }

    public static ChaosGameDescription affineTransform(){
        minCoords = new Vector2D(0, 0);
        maxCoords = new Vector2D(1, 1);
        AffineTransform2D affineTransform2D = new AffineTransform2D(new Matrix2x2(0.5,0,0,0.5), new Vector2D(0,0));
        AffineTransform2D affineTransform2D2 = new AffineTransform2D(new Matrix2x2(0.5,0,0,0.5), new Vector2D(0.25, 0.5));
        AffineTransform2D affineTransform2D3 = new AffineTransform2D(new Matrix2x2(0.5,0,0,0.5), new Vector2D(0.5, 0));
        List<Transform2D> affineTransformations = new ArrayList<>();

        affineTransformations.add(affineTransform2D);
        affineTransformations.add(affineTransform2D2);
        affineTransformations.add(affineTransform2D3);

        return new ChaosGameDescription(minCoords, maxCoords, affineTransformations);
    }
    public static ChaosGameDescription barnsley(){
        minCoords = new Vector2D(-2.65, 0);
        maxCoords = new Vector2D(2.65, 10);
        AffineTransform2D affineTransform2D1 = new AffineTransform2D(new Matrix2x2(0,0,0,0.16), new Vector2D(0,0));
        AffineTransform2D affineTransform2D2 = new AffineTransform2D(new Matrix2x2(0.85,0.04,-0.04,0.85), new Vector2D(0,1.6));
        AffineTransform2D affineTransform2D3 = new AffineTransform2D(new Matrix2x2(0.2,-0.26,0.23,0.22), new Vector2D(0,1.6));
        AffineTransform2D affineTransform2D4 = new AffineTransform2D(new Matrix2x2(-0.15,0.28,0.26,0.24), new Vector2D(0,0.44));
        List<Transform2D> barnsleyTransformations = new ArrayList<>();
        barnsleyTransformations.add(affineTransform2D1);
        barnsleyTransformations.add(affineTransform2D2);
        barnsleyTransformations.add(affineTransform2D3);
        barnsleyTransformations.add(affineTransform2D4);

        return new ChaosGameDescription(minCoords, maxCoords, barnsleyTransformations);
    }

    public static ChaosGameDescription julia(){
        minCoords = new Vector2D(-1.6, -1);
        maxCoords = new Vector2D(1.6, 1);

        Complex complexNumberC = new Complex(-0.74543, 0.11301);
        JuliaTransform juliaTransformPositive = new JuliaTransform(complexNumberC,1);
        JuliaTransform juliaTransformNegative = new JuliaTransform(complexNumberC,-1);
        List<Transform2D> juliaTransformations = new ArrayList<>();
        juliaTransformations.add(juliaTransformNegative);
        juliaTransformations.add(juliaTransformPositive);

        return new ChaosGameDescription(minCoords, maxCoords, juliaTransformations);
    }
}
