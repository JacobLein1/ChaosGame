package view;

import controller.ChaosGameDescription;
import model.Vector2D;

/**
 * This class displays the page where the user can create a new Barnsley fractal
 */
public class CreateBarnsleyPage extends CreateFractal {

    /**
     * Instantiates a new Create barnsley page.
     */
    public CreateBarnsleyPage() {
        setPageTitle("Create Barnsley transformation");
        setWidth(500);
        setHeight(600);

        setChaosGameDescription(
                new ChaosGameDescription(
                        new Vector2D(-2.65, 0),
                        new Vector2D(2.65, 10),
                        getNewTransformations())
        );
        setNewTransformationMenu(); //sets the top menu and the sidebar
        showFractalOnActionCreate(); //displays the fractal when show button is pressed
    }
}
