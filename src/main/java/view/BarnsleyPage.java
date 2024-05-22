package view;

import model.ChaosGameDescriptionFactory;

/**
 * This class displays the page for a Barnsley fern.
 */
public class BarnsleyPage extends Fractal{

    /**
     * Instantiates a new Barnsley page.
     */
    public BarnsleyPage() {
        setPageTitle("Barnsley Fern");
        setWidth(500);
        setHeight(600);
        setChaosGameDescription(ChaosGameDescriptionFactory.get("Barnsley")); //gets the ChaosGameDescription from factory

        setMenu(); //displays top menu
        showFractalOnAction(); //displays the fractal when show button is pressed
        setBarnsleyCoords(); //sets the coordinates to the standard Barnsley fern values
    }
}
