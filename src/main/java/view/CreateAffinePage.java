package view;

import controller.ChaosGameDescription;
import model.Vector2D;


public class CreateAffinePage extends CreateFractal {

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

        setNewTransformationMenu();
        showFractalOnActionCreate();
    }
}
