package view;

import model.ChaosGameDescriptionFactory;

/**
 * This class displays the page for a Sierpinski triangle.
 */
public class Affine2DPage extends Fractal {
    /**
     * Instantiates a new Affine 2 d page.
     */
    public Affine2DPage() {
        setPageTitle("Affine2D Sierpinski");
        setWidth(500);
        setHeight(600);
        setChaosGameDescription(ChaosGameDescriptionFactory.get("Affine2D")); //gets the ChaosGameDescription from factory

        setMenu(); //displays top menu
        showFractalOnAction(); //displays the fractal when show button is pressed
        setAffineCoords(); //sets the coordinates to the standard Sierpinski triangle values
    }
}
