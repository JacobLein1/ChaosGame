package view;

import controller.ChaosGameDescription;
import model.Vector2D;

/**
 * This class displays the page where the user can create a new Julia fractal.
 */
public class CreateJuliaPage extends CreateFractal{

    /**
     * Instantiates a new Create julia page.
     */
    public CreateJuliaPage() {
            setPageTitle("Create Julia transformation");
            setWidth(500);
            setHeight(600);
            setChaosGameDescription(
                    new ChaosGameDescription(
                            new Vector2D(-1.6, -1),
                            new Vector2D(1.6, 1),
                            getNewTransformations()
            ));

            setNewJuliaTransformationMenu(); //sets the top menu and the sidebar
            showFractalOnActionCreate(); //displays the fractal when show button is pressed
        }
}
