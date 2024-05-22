package view;

import model.ChaosGameDescriptionFactory;


/**
 * This class displays the page for a Julia fractal.
 */
public class JuliaPage extends Fractal {

    /**
     * Instantiates a new Julia page.
     */
    public JuliaPage() {
        setPageTitle("Julia");
        setWidth(500);
        setHeight(600);
        setChaosGameDescription(ChaosGameDescriptionFactory.get("Julia")); //gets the ChaosGameDescription from factory

        setMenu(); //displays top menu
        showFractalOnAction(); //displays the fractal when show button is pressed
        setJuliaCoord(); //sets the coordinates to the standard Julia values
    }
}
