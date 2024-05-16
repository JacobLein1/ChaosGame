package view;

import controller.ChaosGameDescriptionFactory;

/**
 * The type Affine 2 d page.
 */
public class Affine2DPage extends Fractal {
    /**
     * Instantiates a new Affine 2 d page.
     */
    public Affine2DPage() {
        setPageTitle("Affine2D Sierpinski");
        setWidth(500);
        setHeight(600);
        setChaosGameDescription(ChaosGameDescriptionFactory.get("Affine2D"));

        setMenu();
    }
}
