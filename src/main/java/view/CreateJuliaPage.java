package view;

import controller.ChaosGameDescription;
import model.Vector2D;

public class CreateJuliaPage extends CreateFractal{

        public CreateJuliaPage() {
            setPageTitle("Create Julia transformation");
            setWidth(500);
            setHeight(600);
            setChaosGameDescription(
                    new ChaosGameDescription(
                            new Vector2D(-1.6, -1),
                            new Vector2D(1.6, 1.1),
                            getNewTransformations()
            ));

            setNewJuliaTransformationMenu();
            showFractalOnActionCreate();
        }
}
