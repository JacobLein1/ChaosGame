package view;

import controller.ChaosGameDescription;
import model.Vector2D;

public class CreateBarnsleyPage extends CreateFractal {

    public CreateBarnsleyPage() {
        setPageTitle("Create Barnsley transformation");
        setWidth(500);
        setHeight(600);
        setChaosGameDescription(
                new ChaosGameDescription(
                        new Vector2D(-2.1820, 0),
                        new Vector2D(2.6558, 9.9983),
                        getNewTransformations())
        );

        setNewTransformationMenu();
    }
}
