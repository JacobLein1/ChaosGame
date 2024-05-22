package view;

import controller.ChaosGameDescription;
import model.Vector2D;


/**
 * This class displays the page where the user can create a new Affine fractal.
 */
public class CreateAffinePage extends CreateFractal {

    /**
     * Instantiates a new Create affine page.
     */
    public CreateAffinePage() {
        setPageTitle("Create Affine transformation");
        setWidth(500);
        setHeight(600);
        setChaosGameDescription(
                new ChaosGameDescription(
                        new Vector2D(0,0),
                        new Vector2D(1,1),
                        getNewTransformations())
        );

        setNewTransformationMenu(); //sets the top menu and the sidebar
        showFractalOnActionCreate(); //displays the fractal when show button is pressed
    }
}
