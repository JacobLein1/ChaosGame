package view;

import controller.ChaosGameDescriptionFactory;

/**
 * The type Barnsley page.
 */
public class BarnsleyPage extends Fractal{

    /**
     * Instantiates a new Barnsley page.
     */
    public BarnsleyPage() {
        setPageTitle("Barnsley Fern");
        setWidth(500);
        setHeight(600);
        setChaosGameDescription(ChaosGameDescriptionFactory.get("Barnsley"));

        setMenu();
        showFractalOnAction();
        setBarnsleyCoords();
    }
}
