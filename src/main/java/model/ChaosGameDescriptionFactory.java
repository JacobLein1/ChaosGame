package model;


import controller.ChaosGameDescription;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Chaos game description factory.
 */
public class ChaosGameDescriptionFactory {

    private static Vector2D minCoords;
    private static Vector2D maxCoords;

    /**
     * Instantiates a new Chaos game description factory.
     */
    public ChaosGameDescriptionFactory(){}; //Constructor

    /**
     * Factory method for creating a chaos game description based on the transformation type.
     * Valid transformation types are "Affine2D", "Barnsley" and "Julia"
     *
     * @param transformationType the transformation type
     * @return the chaos game description
     * @throws IllegalArgumentException the illegal argument exception
     */
    public static ChaosGameDescription get(String transformationType) throws IllegalArgumentException{

        return switch(transformationType){
            case "Affine2D" -> affineTransform();
            case "Barnsley" -> barnsley();
            case "Julia" -> julia();
            default -> throw new IllegalArgumentException("Invalid transformation type");
        };

    }

    /**
     * Affine transform chaos game description.
     *
     * @return the chaos game description of an affine transformation
     */
    public static ChaosGameDescription affineTransform(){
        //Set min- and max-coordinates
        minCoords = new Vector2D(0, 0);
        maxCoords = new Vector2D(1, 1);
        //Create affine transformations
        AffineTransform2D affineTransform2D = new AffineTransform2D(new Matrix2x2(0.5,0,0,0.5), new Vector2D(0,0));
        AffineTransform2D affineTransform2D2 = new AffineTransform2D(new Matrix2x2(0.5,0,0,0.5), new Vector2D(0.25, 0.5));
        AffineTransform2D affineTransform2D3 = new AffineTransform2D(new Matrix2x2(0.5,0,0,0.5), new Vector2D(0.5, 0));
        List<Transform2D> affineTransformations = new ArrayList<>();

        //Add affine transformations to list
        affineTransformations.add(affineTransform2D);
        affineTransformations.add(affineTransform2D2);
        affineTransformations.add(affineTransform2D3);

        return new ChaosGameDescription(minCoords, maxCoords, affineTransformations);
    }

    /**
     * Barnsley chaos game description.
     *
     * @return the chaos game description of a barnsley fern
     */
    public static ChaosGameDescription barnsley(){
        //Set min- and max-coordinates
        minCoords = new Vector2D(-2.65, 0);
        maxCoords = new Vector2D(2.65, 10);
        //Create barnsley transformations
        AffineTransform2D affineTransform2D1 = new AffineTransform2D(new Matrix2x2(0,0,0,0.16), new Vector2D(0,0));
        AffineTransform2D affineTransform2D2 = new AffineTransform2D(new Matrix2x2(0.85,0.04,-0.04,0.85), new Vector2D(0,1.6));
        AffineTransform2D affineTransform2D3 = new AffineTransform2D(new Matrix2x2(0.2,-0.26,0.23,0.22), new Vector2D(0,1.6));
        AffineTransform2D affineTransform2D4 = new AffineTransform2D(new Matrix2x2(-0.15,0.28,0.26,0.24), new Vector2D(0,0.44));
        List<Transform2D> barnsleyTransformations = new ArrayList<>();
        // Add barnsley transformations to list
        barnsleyTransformations.add(affineTransform2D1);
        barnsleyTransformations.add(affineTransform2D2);
        barnsleyTransformations.add(affineTransform2D3);
        barnsleyTransformations.add(affineTransform2D4);

        return new ChaosGameDescription(minCoords, maxCoords, barnsleyTransformations);
    }

    /**
     * Julia chaos game description.
     *
     * @return the chaos game description of a julia set
     */
    public static ChaosGameDescription julia(){
        //Set min- and max-coordinates
        minCoords = new Vector2D(-1.6, -1);
        maxCoords = new Vector2D(1.6, 1);

        //Create julia transformations with complex point and both signs
        Complex complexNumberC = new Complex(-0.74543, 0.11301);
        JuliaTransform juliaTransformPositive = new JuliaTransform(complexNumberC,1);
        JuliaTransform juliaTransformNegative = new JuliaTransform(complexNumberC,-1);
        List<Transform2D> juliaTransformations = new ArrayList<>();
        juliaTransformations.add(juliaTransformNegative);
        juliaTransformations.add(juliaTransformPositive);

        return new ChaosGameDescription(minCoords, maxCoords, juliaTransformations);
    }
}
